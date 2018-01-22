/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.SelectOption;
import com.huaxia.tongyong.param.UserParam;
import com.huaxia.tongyong.param.UserQueryParam;
import com.huaxia.tongyong.service.UserBiz;
import com.huaxia.tongyong.util.Constant;
import com.huaxia.tongyong.util.jwt.JwtUtil;
import com.huaxia.tongyong.vo.JsonResult;
import com.huaxia.tongyong.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/update/password",method = RequestMethod.PUT)
    public JsonResult updateUser(
        @RequestBody UserParam userParam
    ){
        boolean flag = userBiz.updateUserForPassword(userParam);
        if (flag) {
            return new JsonResult<>(1, "修改成功");
        } else {
            return new JsonResult<>(0, "旧密码不正确");
        }
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

    @ApiOperation(value = "获取当前用户", notes = "获取用户权限相关信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query")
    public JsonResult<UserInfo> getUserPermissionVo(
            HttpServletResponse response
    ) {
        Integer userId = JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        JsonResult<UserInfo> returnJson = new JsonResult<>();

        UserInfo userPermissionVo = userBiz.getUserInfo(Long.valueOf(userId));

        returnJson.setData(userPermissionVo);

        return returnJson;
    }

    @ApiOperation(value="根据当前群组获取当前群组的用户信息",notes = "获取群组用户信息",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/group/user")
    public JsonResult<List<UserInfoVo>> queryUserByGroupId(
            @RequestParam("groupId")Long groupId
    ){
        List<UserInfoVo> userInfoVos = userBiz.getGroupUserInfo(groupId);
        return new JsonResult<>(1,"success",userInfoVos);
    }


}
