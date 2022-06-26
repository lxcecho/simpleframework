package com.xc.joy.learn.annotation.configures.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxcecho 909231497@qq.com
 * @since 23:17 25-06-2022
 */
@Configuration
@ComponentScan(value = "com.xc.joy.learn",
//                includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Controller.class})},
//                includeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE,value = {MyController.class})},
//                  includeFilters = {@Filter(type = FilterType.CUSTOM,value = {GPTypeFilter.class})},
                useDefaultFilters = false)
public class MyComponentScanConfig {


}
