package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:17
 * @className: BaseQueryParams
 */
@Data
public class BaseQueryParams {
    /**
     * 一页展示的数据
     */
    private int pageSize=10;
    /**
     * 页数
     */
    private int pageNum=1;
}
