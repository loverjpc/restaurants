package com.jipengcheng.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jipengcheng.provider.dao")
public class ProviderStart {
    public static void main(String[] args) {
        SpringApplication.run(ProviderStart.class,args);
    }
}
