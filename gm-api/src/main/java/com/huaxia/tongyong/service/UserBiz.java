package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.UserParam;
import com.huaxia.tongyong.param.UserQueryParam;
import com.huaxia.tongyong.vo.UserInfoVo;

import java.util.List;

/**
 * @Description:  用户相关的接口
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:19
 * @className: UserBiz
 */
public interface UserBiz {

    /**
     * 更新用户的密码信息
     * @param userParam
     * @return
     */
    public boolean updateUserForPassword(UserParam userParam);

    /**
     * 新增用户信息
     * @param userParam
     * @return
     */
    public boolean addUserInfo(UserParam userParam);

    /**
     * 查询用户列表信息
     * @param userQueryParam
     * @return
     */
    public List<UserInfoVo> getUserInfoList(UserQueryParam userQueryParam);
}
