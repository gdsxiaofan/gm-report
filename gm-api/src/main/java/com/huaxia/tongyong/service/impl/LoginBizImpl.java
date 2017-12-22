package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.repository.UserInfoMapper;
import com.huaxia.tongyong.service.LoginBiz;
import com.huaxia.tongyong.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/25
 * @since 1.0.0
 */
@Slf4j
@Service
public class LoginBizImpl implements LoginBiz{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo verificationForLogin(String employeeNo, String password) throws IllegalArgumentException {
        // 1.校验参宿
        Assert.isTrue(StringUtils.isNotBlank(employeeNo),"工号不能为空！");
        Assert.isTrue(StringUtils.isNotBlank(password),"密码不能为空！");

        // 2.密码加密处理
        password = MD5Util.encode(password);

        // 3.查询对应数据
//        Employee employee = employeeMapper.selectEmployeeForLogin(employeeNo,password);
        UserInfo userInfo = userInfoMapper.selectUserInfoForLogin(employeeNo,password);
        return userInfo;
    }
}
