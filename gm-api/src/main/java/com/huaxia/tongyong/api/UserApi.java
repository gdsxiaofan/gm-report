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
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserApi {
}
