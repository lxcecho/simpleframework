package com.xc.joy.mvcframework.servlet.v1;

import com.xc.joy.mvcframework.annotation.JoyAutowired;
import com.xc.joy.mvcframework.annotation.JoyController;
import com.xc.joy.mvcframework.annotation.JoyRequestMapping;
import com.xc.joy.mvcframework.annotation.JoyService;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lxcecho
 * @since 2020/12/31
 */
public class JoyDispatcherServlet extends HttpServlet {

    private Map<String, Object> map = new HashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if (!this.map.containsKey(url)) {
            resp.getWriter().write("404 Not Found !");
            return;
        }
        Method method = (Method) this.map.get(url);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(this.map.get(method.getDeclaringClass().getName()), new Object[]{req, resp, params.get("name")[0]});
    }

    //????????????????????????????????????????????????

    // init ???????????????????????????????????????
    // init ??????????????????????????????????????????IOC?????? servletBean
    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream is = null;
        try {
            Properties properties = new Properties();
            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            properties.load(is);
            // com.xc.justforjoy
            String scanPackage = properties.getProperty("scanPackage");
            doScan(scanPackage);

            // className ?????????????????? ??? com.xc.justforjoy.mvcframework.servlet.v1.JoyDispatcherServlet
            // ??????????????????
            for (String className : map.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }
                // ???????????????????????????
                Class<?> clazz = Class.forName(className);
                // ??????????????????
                if (clazz.isAnnotationPresent(JoyController.class)) {
                    map.put(className, clazz.newInstance());
                    String baseUrl = "";
                    // ????????????????????? RequestMapping ??????
                    if (clazz.isAnnotationPresent(JoyRequestMapping.class)) {
                        JoyRequestMapping requestMapping = clazz.getAnnotation(JoyRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    // ?????????????????????????????????
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        // ???????????????????????????
                        if (!method.isAnnotationPresent(JoyRequestMapping.class)) {
                            continue;
                        }
                        JoyRequestMapping requestMapping = method.getAnnotation(JoyRequestMapping.class);
                        // ?????????????????????
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        map.put(url, method);
                        System.out.println("Mapped " + url + "," + method);
                    }
                } else if (clazz.isAnnotationPresent(JoyService.class)) {
                    // ?????????????????????
                    JoyService service = clazz.getAnnotation(JoyService.class);
                    // ??????bean????????????
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        // ???????????????????????? ??????
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    // ???bean?????? ??????map???
                    map.put(beanName, instance);
                    for (Class<?> i : clazz.getInterfaces()) {
                        // ???bean?????????????????????map???
                        map.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }

            // ??????map
            for (Object object : map.values()) {
                if (object == null) {
                    continue;
                }
                Class<?> clazz = object.getClass();
                if (clazz.isAnnotationPresent(JoyController.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        // ?????????????????????????????????
                        if (!field.isAnnotationPresent(JoyAutowired.class)) {
                            continue;
                        }
                        JoyAutowired autowired = field.getAnnotation(JoyAutowired.class);
                        String beanName = autowired.value();
                        if ("".equals(beanName)) {
                            beanName = field.getType().getName();
                        }
                        field.setAccessible(true);
                        try {
                            field.set(map.get(clazz.getName()), map.get(beanName));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
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
        System.out.println("Joy MVC Framework is init.");
    }

    private void doScan(String scanPackage) {
        // com.xc.justforjoy
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                // com.xc.justforjoy.SpringMvcApplication.class---> com.xc.justforjoy.SpringMvcApplication
                // "com.xc.justforjoy.mvcframework.servlet.v1.JoyDispatcherServlet"
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                map.put(clazzName, null);
            }
        }
    }

}
