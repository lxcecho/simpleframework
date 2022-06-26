package com.xc.joy.simpleframework.core;

import com.xc.joy.offer.casedemo.controller.frontend.MainPageController;
import com.xc.joy.offer.casedemo.service.solo.HeadLineService;
import com.xc.joy.offer.casedemo.service.solo.impl.HeadLineServiceImpl;
import com.xc.joy.offer.simpleframework.core.BeanContainer;
import com.xc.joy.offer.simpleframework.core.annotation.Controller;
import org.junit.jupiter.api.*;


/**
 * @author lxcecho
 * @since 2021/1/5
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer：loadBeansTest")
    @Order(1)
    @Test
    public void loadBeansTest() {
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.xc.justforjoy.casedemo");
        Assertions.assertEquals(6, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @DisplayName("根据类获取其实例：getBeanTest")
    @Order(2)
    @Test
    public void getBeanTest() {
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        /*DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, dispatcherServlet);*/
    }

    @DisplayName("根据注解获取对应的实例：getClassedByAnnotationTest")
    @Order(3)
    @Test
    public void getClassedByAnnotationTest() {
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(3,beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类：getClassedBySuperTest")
    @Order(4)
    @Test
    public void getClassedBySuperTest() {
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(true,beanContainer.getClassesBySuper(HeadLineService.class)
                .contains(HeadLineServiceImpl.class));
    }

}
