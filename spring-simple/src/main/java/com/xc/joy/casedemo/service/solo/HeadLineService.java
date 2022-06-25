package com.xc.joy.casedemo.service.solo;

import com.xc.joy.casedemo.dto.Result;
import com.xc.joy.casedemo.entity.HeadLine;

import java.util.List;

/**
 * @author lxcecho
 * @since 2021/1/4
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);

    Result<Boolean> removeHeadLine(int headLineId);

    Result<Boolean> modifyHeadLine(HeadLine headLine);

    Result<HeadLine> queryHeadLineById(int headLineId);

    Result<List<HeadLine>> queryHeadLine(HeadLine headLine, int pageIndex, int pageSize);
}
