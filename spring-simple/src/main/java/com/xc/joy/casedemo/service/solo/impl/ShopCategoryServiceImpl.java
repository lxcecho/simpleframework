package com.xc.joy.casedemo.service.solo.impl;

import com.xc.joy.casedemo.dto.Result;
import com.xc.joy.casedemo.entity.ShopCategory;
import com.xc.joy.casedemo.service.solo.ShopCategoryService;
import com.xc.joy.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
