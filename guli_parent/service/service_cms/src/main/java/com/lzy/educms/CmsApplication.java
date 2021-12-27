package com.lzy.educms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan("com.lzy.educms.mapper")
@ComponentScan(basePackages = "com.lzy")
public class CmsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}
}
