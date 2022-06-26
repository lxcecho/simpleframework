package com.xc.joy.offer.casedemo.dto;

import com.xc.joy.offer.casedemo.entity.HeadLine;
import com.xc.joy.offer.casedemo.entity.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
@Data
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;

}
