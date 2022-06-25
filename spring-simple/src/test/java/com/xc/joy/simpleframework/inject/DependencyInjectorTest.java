package com.xc.joy.simpleframework.inject;

import com.xc.joy.casedemo.controller.frontend.MainPageController;
import com.xc.joy.casedemo.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import com.xc.joy.casedemo.service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;
import com.xc.joy.simpleframework.core.BeanContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author lxcecho
 * @since 2021/1/5
 */
public class DependencyInjectorTest {

    @DisplayName("依赖注入doIoc")
    @Test
    public void doIocTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.xc.justforjoy.casedemo");
        Assertions.assertEquals(true, beanContainer.isLoaded());

        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);

        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());

        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        Assertions.assertEquals(true, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
        Assertions.assertEquals(false, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);
    }

}
