package com.xc.joy.casedemo.controller.frontend;

import com.xc.joy.casedemo.dto.MainPageInfoDTO;
import com.xc.joy.casedemo.dto.Result;
import com.xc.joy.casedemo.service.combine.HeadLineShopCategoryCombineService;
import com.xc.joy.simpleframework.core.annotation.Controller;
import com.xc.joy.simpleframework.inject.annotation.Autowired;
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
