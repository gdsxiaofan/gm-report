package com.huaxia.tongyong.api;

import com.huaxia.tongyong.param.GroupParam;
import com.huaxia.tongyong.vo.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:10
 * @className: GroupApi
 */
@RestController
@RequestMapping("/v1/group")
public class GroupApi {

    /**
     * 获取班组列表信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value ="/list",method  =RequestMethod.GET)
    public JsonResult getGroupList(
            @RequestBody GroupParam groupParam
            ){
       return null;
    }

    /**
     * 新增班组信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addGroupInfo(@RequestBody GroupParam groupParam){
        return null;
    }

    /**
     * 更新班组信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public JsonResult updateGroupInfo(@RequestBody GroupParam groupParam){
        return null;
    }
}
