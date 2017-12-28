package com.huaxia.tongyong.api;

import com.huaxia.tongyong.vo.JsonResult;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/28 22:30
 * @className: BaseApi
 */
public class BaseApi {
    protected JsonResult getJsonResult(boolean flag){
        if(flag){
            return new JsonResult();
        }
        return new JsonResult(0,"操作失败",null);
    }
}
