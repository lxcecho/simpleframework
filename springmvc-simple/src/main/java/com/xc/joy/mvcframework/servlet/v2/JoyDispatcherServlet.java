package com.xc.joy.mvcframework.servlet.v2;

import com.xc.joy.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author lxcecho
 * @since 2020/12/31
 *
 * 在V1 版本上进了优化，采用了常用的设计模式（工厂模式、单例模式、委派模式、策略模式），
 * 将init()方法中的代码进行封装。按照之前的实现思路，先搭基础框架，再填肉注血，具体代码如下：
 */
public class JoyDispatcherServlet extends HttpServlet {

    // 保存 application.properties 配置文件中的内容
    private Properties contextConfig = new Properties();

    // 保存扫描的所有的类名
    List<String> classNames = new ArrayList();

    // 传说中的IOC容器，揭开它的神秘面纱——保存所有实例化对象
    // 为了简化程序，暂不考虑使用 ConcurrentHashMap
    // 主要还是关注设计思想——注册式单例模式
    private Map<String, Object> ioc = new HashMap();

    // 保存Controller中所有Mapping的对应关系 即 url和Method的对应关系
    Map<String, Method> handlerMapping = new HashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 派遣，分发任务
        // doPost()方法中，用了委派模式，委派模式的具体逻辑在doDispatch()方法中：
        try {
            // 委派模式
            // 6 调用，运行阶段
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("50  Exception.Details : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        if (!this.handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Method method = this.handlerMapping.get(url);

        //从request中拿到url传过来的参数
        // 第一参数：方法所在的实例
        // 第二个参数：调用时所需要的的实参
        Map<String, String[]> params = req.getParameterMap();

        //获取方法的形参列表
        Class<?>[] parameterTypes = method.getParameterTypes();

        Object[] paramValues = new Object[parameterTypes.length];

        // 根据参数位置动态赋值
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
            //不能用instanceof，parameterType它不是实参，而是形参
            if (parameterType == HttpServletRequest.class) {
                paramValues[i] = req;
                continue;
            } else if (parameterType == HttpServletResponse.class) {
                paramValues[i] = resp;
                continue;
            } else if (parameterType == String.class) {
                // TODO  通过 java.lang.String 获取注解？？？？有问题
                JoyRequestParam requestParam = (JoyRequestParam) parameterType.getAnnotation(JoyRequestParam.class);
                if (params.containsKey(requestParam.value())) {
                    for (Map.Entry<String, String[]> param : params.entrySet()) {
                        String value = Arrays.toString(param.getValue())
                                .replaceAll("\\[|\\]", "")
                                .replaceAll("\\s", ",");
                        paramValues[i] = value;
                    }
                }
            }
        }

        //投机取巧的方式
        //通过反射拿到method所在class，拿到class之后还是拿到class的名称
        //再调用toLowerFirstCase获得beanName
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName), paramValues);
    }

    //url传过来的参数都是String类型的，HTTP是基于字符串协议
    //只需要把String转换为任意类型就好
    private Object convert(Class<?> type, String value) {
        //如果是int
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现
        return value;
    }

    // 初始化阶段
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1 加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2 扫描相关的类
        doScan(contextConfig.getProperty("scanPackage"));

        // 3 初始化扫描到的类，并且把它们放到 IOC 容器中
        doInstance();

        // 4 完成依赖注入
        doAutowired();

        // 5 初始化 HandlerMapping
        initHandlerMapping();

        System.out.println("Joy Spring framework is init.");

    }

    /**
     * 初始化 URL 和 Method 的一对一对应关系
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if (!clazz.isAnnotationPresent(JoyController.class)) {
                continue;
            }

            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(JoyRequestMapping.class)) {
                JoyRequestMapping requestMapping = clazz.getAnnotation(JoyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //默认获取所有的public方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(JoyRequestMapping.class)) {
                    continue;
                }

                JoyRequestMapping requestMapping = method.getAnnotation(JoyRequestMapping.class);
                //优化
                // //demo///query
                String url = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped :" + url + "," + method);
            }
        }
    }

    /**
     * 依赖自动注入
     */
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // Declared 所有的，特定的字段，包括 private public protected default
            // 正常来说，普通的OOP编程只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(JoyAutowired.class)) {
                    continue;
                }
                JoyAutowired autowired = field.getAnnotation(JoyAutowired.class);

                // 如果这个地方没有自定义beanName，默认就根据类型注入
                // todo 这个地方省去了对类名首字母小写的情况的判断 ???
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    // 获得接口的类型，作为key待会拿这个key到ioc容器中去取值
                    beanName = field.getType().getName();
                }
                // 如果是public以外的修饰符，只加了@Autowired注释，都要强制赋值
                // 反射中叫 暴力访问 即 强吻
                field.setAccessible(true);
                try {
                    // 利用反射机制，动态给字段赋值
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化扫描到的类，并将它们放到 IOC 容器中
     */
    private void doInstance() {
        // 初始化 为DI做准备
        if (classNames.isEmpty()) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                // 什么样的类才需要初始化呢？
                // 加了注解的类，才初始化，怎么判断？
                // 为了简化代码逻辑，主要体会设计思想，只举例 Controller 和 Service
                // Component/Repository... ...

                if (clazz.isAnnotationPresent(JoyController.class)) {
                    Object instance = clazz.newInstance();
                    // Spring默认类名是首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(JoyService.class)) {
                    // 1 自定义的 beanName
                    JoyService service = clazz.getAnnotation(JoyService.class);
                    String beanName = service.value();
                    //2 默认类名首字母小写
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    // 3 根据类型自动赋值，投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("The “" + i.getName() + "” is exists!!");
                        }
                        // 把接口的类型直接当成key了
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 如果类名本身是小写字母，确实会出问题，但是要说明的是，这个方法是我自己用的，private的，
    // 传值也是自己传的，类也都遵循了驼峰命名法，默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况

    // 为了简化程序逻辑，就不做其他判断了，了解便可
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // 之所以加 32 ，是因为大小写字母的 ASCII码相差32，而且大写字母的ASCII码要 小于 小写字母的ASCII码，
        // 在Java中，对char做算数运算，实际上就是对ASCII码做算数运算
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 扫描出相关的类
     *
     * @param scanPackage
     */
    private void doScan(String scanPackage) {
        // scanPackage=com.xc.justforjoy 存储的是包路径，转换为文件路径，实际上就是把 . 换成了 / 就OK了
        // classpath 下不仅有 .class  .xml .properties 文件
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }

    }

    /**
     * 加载配置文件
     *
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {
        // 直接从类路径下找到Spring主配置文件所在的路径
        // 并且将其读取出来放到Properties对象中
        // 相对于scanPackage=com.xc.justforjoy 从文件中保存到了内存中
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
