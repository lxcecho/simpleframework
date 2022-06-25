package com.xc.joy.casedemo.service.combine.impl;

import com.xc.joy.casedemo.dto.MainPageInfoDTO;
import com.xc.joy.casedemo.dto.Result;
import com.xc.joy.casedemo.entity.HeadLine;
import com.xc.joy.casedemo.entity.ShopCategory;
import com.xc.joy.casedemo.service.combine.HeadLineShopCategoryCombineService;
import com.xc.joy.casedemo.service.solo.HeadLineService;
import com.xc.joy.casedemo.service.solo.ShopCategoryService;
import com.xc.joy.simpleframework.core.annotation.Service;
import com.xc.joy.simpleframework.inject.annotation.Autowired;

import java.util.List;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 1 获取头条
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult =  headLineService.queryHeadLine(headLine,1,4);
        // 2 获取店铺类别列表
        ShopCategory shopCategory = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategory,1,100);
        // 3 合并两者并返回
        Result<MainPageInfoDTO> result =  mergeMainPageInfoResult(headLineResult, shopCategoryResult);

        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
