package com.xc.joy.spring.webmvc.servlet;

import com.xc.joy.spring.annotation.EchoRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxcecho 909231497@qq.com
 * @since 11:18 26-06-2022
 */
public class EchoHandlerAdapter {

    public EchoModelAndView handler(HttpServletRequest req, HttpServletResponse resp, EchoHandlerMapping handler) throws Exception {

        // 保存形参列表，将参数名称和参数的位置，这种关系保存起来
        Map<String, Integer> paramIndexMapping = new HashMap<String, Integer>();

        // 通过运行时的状态去拿到方法中加注解的参数，把方法上的注解拿到，得到的是一个二维数组，因为一个参数可以有多个注解，而一个方法又有多个参数
        Annotation[][] pa = handler.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if (a instanceof EchoRequestParam) {
                    String paramName = ((EchoRequestParam) a).value();
                    if (!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        // 初始化一下，提取方法中的 request 和 response 参数
        Class<?>[] paramTypes = handler.getMethod().getParameterTypes();

        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> parameterType = paramTypes[i];
            if (parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class) {
                paramIndexMapping.put(parameterType.getName(), i);
            }
        }


        // 去拼接方法的形参列表 http://localhost/web/query?name=Tom&Cat
        Map<String, String[]> params = req.getParameterMap();

        // 实参列表
        Object[] paramValues = new Object[paramTypes.length];

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(params.get(param.getKey()))
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s+", ",");

            if (!paramIndexMapping.containsKey(param.getKey())) {
                continue;
            }

            int index = paramIndexMapping.get(param.getKey());

            // 允许自定义的类型转换器 Converter
            paramValues[index] = castStringValue(value, paramTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        Object result = handler.getMethod().invoke(handler.getController(), paramValues);
        if (result == null || (result instanceof Void)) {
            return null;
        }

        boolean isModelAndView = handler.getMethod().getReturnType() == EchoModelAndView.class;
        if (isModelAndView) {
            return (EchoModelAndView) result;
        }
        return null;
    }

    private Object castStringValue(String value, Class<?> paramType) {
        if (String.class == paramType) {
            return value;
        } else if (Integer.class == paramType) {
            return Integer.valueOf(value);
        } else if (Double.class == paramType) {
            return Double.valueOf(value);
        } else {
            if (value != null) {
                return value;
            }
            return null;
        }

    }

}
