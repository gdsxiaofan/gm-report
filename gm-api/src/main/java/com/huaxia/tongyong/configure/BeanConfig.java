/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.configure;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Configuration
public class BeanConfig {
    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
        List<String> mappingFiles = Arrays.asList(
                "dozer/beanMappers.xml"
        );

        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}
