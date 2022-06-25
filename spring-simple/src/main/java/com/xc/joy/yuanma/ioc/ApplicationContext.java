package com.xc.joy.yuanma.ioc;

import com.xc.joy.yuanma.ioc.anno.Component;
import com.xc.joy.yuanma.ioc.anno.ComponentScan;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

/**
 * @author lxcecho 909231497@qq.com
 */
public class ApplicationContext {

    private final DefaultListableBeanFactory beanFactory;

    public ApplicationContext(Class<?> annotatedClasses) {
        this.beanFactory =  new DefaultListableBeanFactory();
        register(annotatedClasses);
        refresh();
    }

    public void register(Class<?> annotatedClasses){

        try {
            String shortClassName = ClassUtils.getShortName(annotatedClasses.getName());
            // 注册配置类
            registerBeanDefinition(annotatedClasses, shortClassName);

            //扫描 @ComponentScan
            scan(annotatedClasses);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void refresh(){
       // 实例化bean
        beanFactory.preInstantiateBeans();
    }


    public void scan(Class<?> annotatedClasses) throws Exception{

        ComponentScan componentScan = annotatedClasses.getAnnotation(ComponentScan.class);
        String basePackage = componentScan.value();
        String dir = basePackage.replaceAll("\\.","/");
        URL url = ClassUtils.getDefaultClassLoader().getResource(dir);
        String filePath = url.getPath();

        parse(filePath, basePackage);


    }


    private void parse(String filePath, String basePackage) {
        //解析@Component
        try {
            File[] files = getClassFile(filePath);
            if (files != null) {
                for (File f : files) {
                    String fileName = f.getName();
                    if (f.isFile()) {
                        String shortClassName = fileName.substring(0,fileName.lastIndexOf("."));
                        String fullName = basePackage+"."+shortClassName;
                        Class<?> clazz = Class.forName(fullName);
                        //判断是否实现@Component注解
                        if(clazz.isAnnotationPresent(Component.class)) {
                            registerBeanDefinition(clazz, shortClassName);
                        }
                    } else {
                        parse(f.getPath(), basePackage + "." + fileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private  File[] getClassFile(String filePath) {
        //获取该路径下所有的class文件和目录
        return new File(filePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
    }

    private void registerBeanDefinition(Class<?> annotatedClasses, String shortClassName) throws Exception {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        String beanName = ClassUtils.decapitalize(shortClassName);
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(annotatedClasses.getName());
        // 注册bean
        registry.registerBeanDefinition(beanName,beanDefinition);
    }

    public Object getBean(String name) {
        try {
            return beanFactory.getBean(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getBean(Class<T> requiredType){
        try {
            return beanFactory.getBean(requiredType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
