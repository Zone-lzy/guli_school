package com.atguigu.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.lzy","com.atguigu"})
@MapperScan("com.atguigu.aclservice.mapper")
@EnableSwagger2
public class ServiceAclApplication {

    public static void main(String[] args) {
        System.out.println("============================sdfjweownfknf=================================");
        SpringApplication.run(ServiceAclApplication.class, args);
    }

}
