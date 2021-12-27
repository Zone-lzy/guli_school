package com.lzy.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.lzy"}) //用于扫描swagger
@EnableDiscoveryClient // nacos注册
@EnableFeignClients
public class Service_vod_Main8003 {
	public static void main(String[] args) {
		SpringApplication.run(Service_vod_Main8003.class,args);
	}
}
