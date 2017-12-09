/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@Slf4j
public class AppStartConfiguration {

    public static void main(String[] args) {
        SpringApplication app =new SpringApplication(AppStartConfiguration.class);
        Environment env = app.run(args).getEnvironment();
        try {
            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}/index.html\n\t" +
                            "External: \thttp://{}:{}/index.html\n----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }

        String configServerStatus = env.getProperty("configserver.status");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Config Server: \t{}\n----------------------------------------------------------",
                configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
