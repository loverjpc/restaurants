package com.jipengcheng.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulStart {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStart.class,args);
    }
}
