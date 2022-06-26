package com.xc.joy.offer.casedemo.controller.frontend;

import com.xc.joy.offer.casedemo.dto.MainPageInfoDTO;
import com.xc.joy.offer.casedemo.dto.Result;
import com.xc.joy.offer.casedemo.service.combine.HeadLineShopCategoryCombineService;
import com.xc.joy.offer.simpleframework.inject.annotation.Autowired;
import com.xc.joy.offer.simpleframework.core.annotation.Controller;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Controller
@Getter
public class MainPageController {

    @Autowired(value = "HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

}
