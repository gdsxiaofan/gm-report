package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.GroupParam;
import com.huaxia.tongyong.service.GroupBiz;
import com.huaxia.tongyong.vo.GroupInfoVo;
import com.huaxia.tongyong.vo.JsonResult;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:10
 * @className: GroupApi
 */
@RestController
@RequestMapping("/v1/group")
public class GroupApi extends BaseApi{

    @Autowired
    private GroupBiz groupBiz;

    /**
     * 获取班组列表信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value ="/list",method  =RequestMethod.POST)
    public JsonResult<PageInfo<GroupInfoVo>> getGroupList(
            @RequestBody GroupParam groupParam
            ){
        List<GroupInfoVo> groupInfoVos = groupBiz.getGroupInfoVoList(groupParam);
        if(CollectionUtils.isEmpty(groupInfoVos)){
            return new JsonResult<>(0,"no_data",new PageInfo<>(new ArrayList<>()));
        }
        PageInfo<GroupInfoVo> pageInfo = new PageInfo<>(groupInfoVos);

       return new JsonResult<>(1,"sucess",pageInfo);
    }

    /**
     * 新增班组信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResult addGroupInfo(@RequestBody GroupParam groupParam){

        boolean flag = groupBiz.addGroupInfo(groupParam);

        return getJsonResult(flag);
    }

    /**
     * 更新班组信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public JsonResult updateGroupInfo(@RequestBody GroupParam groupParam){

        boolean flag = groupBiz.updateGroupInfo(groupParam);
        return getJsonResult(flag);
    }

    /**
     * 删除班组信息
     * @param groupParam
     * @return
     */
    @RequestMapping(value="/del",method = RequestMethod.DELETE)
    public JsonResult updateGroupInfo(Long id){

        boolean flag = groupBiz.delGroupInfo(id);
        return getJsonResult(flag);
    }

}
