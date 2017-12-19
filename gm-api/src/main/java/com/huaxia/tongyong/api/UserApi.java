/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.api;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/12/2 0002
 * @since 1.0.0
 */
@Api(value = "UserController", description = "用户相关api")
@RestController("/v1/user")
@Slf4j
public class UserApi {

    //1.获取所有的有效班组信息
    //2.根据班组id获取所有有效的用户信息
    //3.根据用户id获取当前用户的相关信息
}
