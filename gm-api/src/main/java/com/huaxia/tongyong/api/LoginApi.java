package com.huaxia.tongyong.api;

import com.huaxia.tongyong.handler.JwtHandler;
import com.huaxia.tongyong.model.Employee;
import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.service.LoginBiz;
import com.huaxia.tongyong.util.json.JSONHelper;
import com.huaxia.tongyong.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:duhongda
 * @Description:
 * @Date:Create in 10:33 2017/8/25
 */
@RestController("/login")
@Slf4j
public class LoginApi {

    @Autowired
    private LoginBiz loginBiz;
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public JsonResult login(HttpServletResponse response,
                            String username,
                            String password) {
        UserInfo userInfo=loginBiz.verificationForLogin(username,password);
        if(userInfo!=null){
//            String jwt = JwtUtil.getJWTString(employee.getId());
            JwtHandler.setCookieJWT(userInfo.getId().intValue(),response);
//            response.setHeader(Constant.AUTHORIZATION,jwt);
            return new JsonResult(1, "登陆成功");
        }

        MDC.put("user", JSONHelper.obj2JSONString(userInfo));
        return new JsonResult(0,"用户名或密码不匹配");
    }

}
