package com.xc.joy.spring.beans.support;

import com.xc.joy.spring.beans.config.EchoBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:49 25-06-2022
 */
public class EchoBeanDefinitionReader {

    // 保存扫描的结果
    private List<String> regitryBeanClasses = new ArrayList<String>();

    private Properties contextConfig = new Properties();

    public EchoBeanDefinitionReader(String... configLocations) {
        doLoadConfig(configLocations[0]);

        // 扫描配置文件中的配置的相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    /**
     * 把配置文件中扫描到的所有配置信息转换为 BeanDefinition 对象，以便之后 IOC 操作方便
     *
     * @return
     */
    public List<EchoBeanDefinition> loadBeanDefinitions() {
        List<EchoBeanDefinition> result = new ArrayList<EchoBeanDefinition>();
        try {
            for (String className : regitryBeanClasses) {
                Class<?> beanClass = Class.forName(className);

                // 保存类对应的 ClassName（全类名），还有 beanName
                // 1、默认是类名首字母小写
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));
                // 2、自定义
                // 3、接口注入
                for (Class<?> i : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 把每一个配置信息解析成一个 BeanDefinition
     *
     * @param beanName
     * @param beanClassName
     * @return
     */
    private EchoBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        EchoBeanDefinition beanDefinition = new EchoBeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }


    private void doLoadConfig(String contextConfigLocation) {
        // 通过 URL 定位找到其所对应的文件，然后转换为文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replaceAll("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        // jar 、 war 、zip 、rar
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        // 当成是一个 ClassPath 文件夹
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                // 全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                // Class.forName(className);
                regitryBeanClasses.add(className);
            }
        }
    }

    //自己写，自己用
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
//        if(chars[0] > )
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return this.contextConfig;
    }

}
