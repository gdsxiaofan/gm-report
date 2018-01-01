package com.huaxia.tongyong.service.impl;

import com.github.pagehelper.PageHelper;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.param.UserParam;
import com.huaxia.tongyong.param.UserQueryParam;
import com.huaxia.tongyong.repository.UserInfoMapper;
import com.huaxia.tongyong.service.UserBiz;
import com.huaxia.tongyong.util.MD5Util;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.UserInfoVo;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 实现用户信息相关的业务方法
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:27
 * @className: UserBizImpl
 */
@Service
public class UserBizImpl implements UserBiz{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean updateUserForPassword(UserParam userParam) {
        /**
         * 更新用户密码信息有两种情况
         * 1，当前登录人员更新自己的密码信息
         * 2,当前登录人员更新他人的密码信息
         */
        //根据当前参数是否给与用户id判断
        Long userId = userParam.getId();
        if(userId==null){
            UserInfo userInfo = JSONHelper.jsonToObject(MDC.get("user"), UserInfo.class);
            userId = userInfo.getId();
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(!userInfo.getPassword().equals(MD5Util.encode(userParam.getOldPassword()))){
            return false;
        }
        userInfo.setPassword(MD5Util.encode(userParam.getNewPassword()));

        userInfoMapper.updateUser(userInfo);
        return true;
    }

    @Override
    public boolean addUserInfo(UserParam userParam) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(MD5Util.encode("123456"));
        userInfo.setEmployeeNo(userParam.getEmployeeNo());
        userInfo.setMobileNo(userParam.getMobile());
        userInfo.setName(userParam.getUserName());
        userInfo.setGroupId(userParam.getGroupId().longValue());
        userInfo.setLevelId(userParam.getLevelId().longValue());
        userInfo.setCreateTime(new Date());

        userInfoMapper.insertSelective(userInfo);
        return true;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo getUserInfo(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInfoVo> getUserInfoList(UserQueryParam userQueryParam) {
        PageHelper.startPage(userQueryParam.getPageNum(),userQueryParam.getPageSize());
        // 查询对应的用户信息
        List<UserInfoVo> userInfoVos = userInfoMapper.selectUserInfoVo(userQueryParam.getEmployeeNo(),userQueryParam.getUserName());
        return userInfoVos;
    }

    /**
     * 获取群组用户信息
     * @param groupId
     * @return
     */
    @Override
    public List<UserInfoVo> getGroupUserInfo(Long groupId) {
        return null;
    }
}
