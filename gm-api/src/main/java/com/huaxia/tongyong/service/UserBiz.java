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
     * 获取用户信息
     * @param id
     * @return
     */
    public UserInfoVo getUserInfo(Long id);

    /**
     * 查询用户列表信息
     * @param userQueryParam
     * @return
     */
    public List<UserInfoVo> getUserInfoList(UserQueryParam userQueryParam);

    /**
     * 获取群组下的所有用户信息
     * @param groupId
     * @return
     */
    public List<UserInfoVo> getGroupUserInfo(Long groupId);

    /**
     * 用户工号重复验证
     * @param employeeNo
     * @return
     */
    boolean checkEmployeeNo(String employeeNo);

    void updateUser(UserParam userParam);

    void delUser(Integer id);

    /**
     *  冻结
     * @param id
     * @param isActive
     */
    void isActiveUser(Integer id, Integer isActive);
}
