package com.huaxia.tongyong.service;

import com.huaxia.tongyong.model.Employee;
import com.huaxia.tongyong.model.UserInfo;

/**
 * 登录相关的业务逻辑
 *
 * @author gongdaoshun
 * @date 2017/8/25
 * @since 1.0.0
 */
public interface LoginBiz {

    /**
     * 根据工号和密码校验登陆
     * @param employeeNo
     * @param password
     * @return
     */
//    Employee verificationForLogin(String employeeNo, String password);

    UserInfo verificationForLogin(String employeeNo, String password);

}
