/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.api;

import com.github.pagehelper.PageInfo;
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
import org.apache.commons.lang3.StringUtils;
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
public class UserApi extends BaseApi {

    @Autowired
    private UserBiz userBiz;

    /**
     * 用户用户更新密码接口
     *
     * @param userParam
     * @return
     */
    @RequestMapping(value = "/update/password", method = RequestMethod.PUT)
    public JsonResult updatePwd(
            @RequestBody UserParam userParam
    ) {
        boolean flag = userBiz.updateUserForPassword(userParam);
        if (flag) {
            return new JsonResult<>(1, "修改成功");
        } else {
            return new JsonResult<>(0, "旧密码不正确");
        }
    }

    /**
     * 查询用户列表信息
     *
     * @param userQueryParam
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult<PageInfo<UserInfoVo>> getUserList(UserQueryParam userQueryParam) {

        List<UserInfoVo> userInfoVoList = userBiz.getUserInfoList(userQueryParam);
        if (CollectionUtils.isEmpty(userInfoVoList)) {
            return new JsonResult<>(0, "no_data", null);
        }
        PageInfo<UserInfoVo> pageInfo = new PageInfo<>(userInfoVoList);

        return new JsonResult<>(1, "sucess", pageInfo);
    }

    /**
     * 新增用户信息
     *
     * @param userParam
     * @return
     */
    @ApiOperation(value = "新增用户", notes = "新增用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/addUser")
    public JsonResult addUserInfo(@RequestBody UserParam userParam) {
        boolean flag = userBiz.addUserInfo(userParam);
        return getJsonResult(flag);
    }

    /**
     * 新增用户信息
     *
     * @param employeeNo
     * @return
     */
    @ApiOperation(value = "用户工号重复验证", notes = "用户工号重复验证", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/checkEmployeeNo")
    public JsonResult checkEmployeeNo(String employeeNo) {
        boolean flag = userBiz.checkEmployeeNo(employeeNo);
        return new JsonResult<>(1,"",flag);
    }

    @ApiOperation(value = "获取当前用户", notes = "获取用户权限相关信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/query")
    public JsonResult<UserInfoVo> getUserPermissionVo(
            HttpServletResponse response
    ) {
        Integer userId = JwtUtil.parseJwt2Id(response.getHeader(Constant.AUTHORIZATION));
        JsonResult<UserInfoVo> returnJson = new JsonResult<>();

        UserInfoVo userPermissionVo = userBiz.getUserInfo(Long.valueOf(userId));

        returnJson.setData(userPermissionVo);

        return returnJson;
    }

    @ApiOperation(value = "根据当前群组获取当前群组的用户信息", notes = "获取群组用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/group/user")
    public JsonResult<List<UserInfoVo>> queryUserByGroupId(
            @RequestParam("groupId") Long groupId
    ) {
        List<UserInfoVo> userInfoVos = userBiz.getGroupUserInfo(groupId);
        return new JsonResult<>(1, "success", userInfoVos);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/updateUser")
    public JsonResult<String> updateUser(
            @RequestBody UserParam userParam
    ) {
        try {
            String pwd = userParam.getNewPassword();
            if (StringUtils.isNotBlank(pwd)) {
                userParam.setNewPassword(pwd);
            } else {
                userParam.setNewPassword(null);
            }
            userBiz.updateUser(userParam);
        } catch (Exception e) {
            return new JsonResult<>(0, "修改失败");
        }

        return new JsonResult<>(1, "修改成功");
    }
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/delUser")
    public JsonResult<String> delUser(
            Integer id
    ) {
        try {
            userBiz.delUser(id);
        } catch (Exception e) {
            return new JsonResult<>(0, "删除失败");
        }

        return new JsonResult<>(1, "删除成功");
    }
    @ApiOperation(value = "是否停用用户", notes = "是否停用用户", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("/isActiveUser")
    public JsonResult<String> isActiveUser(
            Integer id,
            Integer isActive
    ) {
        try {
            userBiz.isActiveUser(id, isActive);
        } catch (Exception e) {
            log.error("是否停用用户 :{}", e.getMessage());
            return new JsonResult<>(0, "删除失败");
        }

        return new JsonResult<>(1, "删除成功");
    }
}
