/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.handler;


import com.huaxia.tongyong.util.jwt.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author:duhongda
 * @Description: jwt登录token认证
 * @Date:Create in 17:25 2017/8/25
 */
//@Component
public class JwtHandler implements HandlerInterceptor {
    public static final String AUTHORIZATION="Authorization";


    //    @Autowired
//    private EmployeeMapper employeeMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String jwt = getCookieJWT(request);
        if (StringUtils.isBlank(jwt)) {//判断jwt是否为空
            response.setStatus(401);
            return false;
        }
        Integer id = JwtUtil.parseJwt2Id(jwt);

        if (id == null) {//判断jwt是否为真或是否过期
            response.setStatus(401);
            return false;
        }
        //查询对应的用户信息
//        Employee employee = employeeMapper.selectById(id);
//        MDC.put("user", JSONHelper.obj2JSONString(employee));
        setCookieJWT(id, response);
        return true;

    }


    /**
     *
     * @param request
     * @return null 代表没有
     */
    public static String getCookieJWT(HttpServletRequest request) {
        Cookie[] cs = request.getCookies();
        if (null != cs && cs.length > 0) {
            Optional<Cookie> v = Stream.of(cs).filter((c) -> c.getName().equals(AUTHORIZATION)).findFirst();
            if (v.isPresent()) {
                Cookie cookie = v.get();
                return  cookie.getValue();
            }
        }
        return null;
    }

    public static void setCookieJWT(Integer id, HttpServletResponse response) {
        String jwt = JwtUtil.getJWTString(id);
        Cookie cookie = new Cookie(AUTHORIZATION, jwt);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);//过期时间为24小时
        response.addCookie(cookie);
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
