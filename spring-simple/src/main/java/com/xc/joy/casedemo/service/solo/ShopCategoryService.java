package com.xc.joy.casedemo.service.solo;

import com.xc.joy.casedemo.dto.Result;
import com.xc.joy.casedemo.entity.ShopCategory;

import java.util.List;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public interface ShopCategoryService {

    Result<Boolean> addShopCategory(ShopCategory shopCategory);

    Result<Boolean> removeShopCategory(int shopCategoryId);

    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);

    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);

    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);

}
