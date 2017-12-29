/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.param.UserParam;
import com.huaxia.tongyong.param.UserQueryParam;
import com.huaxia.tongyong.service.UserBiz;
import com.huaxia.tongyong.vo.GroupInfoVo;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.UserInfoVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 提供用户相关的接口
 *
 * @author gongdaoshun
 * @date 2017/12/2 0002
 * @since 1.0.0
 */
@Api(value = "UserController", description = "用户相关api")
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserApi extends BaseApi{

    @Autowired
    private UserBiz userBiz;

    /**
     * 用户用户更新密码接口
     * @param userParam
     * @return
     */
    @RequestMapping(value = "/update/password",method = RequestMethod.POST)
    public JsonResult updateUser(
        @RequestBody UserParam userParam
    ){
        boolean flag = userBiz.updateUserForPassword(userParam);
        return getJsonResult(flag);
    }

    /**
     * 查询用户列表信息
     * @param userQueryParam
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public JsonResult<PageInfo<UserInfoVo>> getUserList(UserQueryParam userQueryParam){

        List<UserInfoVo> userInfoVoList = userBiz.getUserInfoList(userQueryParam);
        if(CollectionUtils.isEmpty(userInfoVoList)){
            return new JsonResult<>(0,"no_data",null);
        }
        PageInfo<UserInfoVo> pageInfo = new PageInfo<>(userInfoVoList);

        return new JsonResult<>(1,"sucess",pageInfo);
    }

    /**
     * 新增用户信息
     * @param userParam
     * @return
     */
    public JsonResult addUserInfo(@RequestBody  UserParam userParam){
        boolean flag = userBiz.addUserInfo(userParam);
        return getJsonResult(flag);
    }
}
