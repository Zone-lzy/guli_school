package com.lzy.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lzy")
@MapperScan("com.lzy.educenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class UcenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(UcenterApplication.class,args);
	}
}
