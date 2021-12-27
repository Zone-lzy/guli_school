package com.lzy.oss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * aliyun.oss.file.endpoint=oss-cn-hangzhou.aliyuncs.com
 * aliyun.oss.file.keyid=LTAI5tDvKM8jyYPCm4ZQno6b
 * aliyun.oss.file.keysecret=YXSHytihfJVRc0V60WEJJya9leIxBl
 * aliyun.oss.file.bucketname=lzy-gili-edu
 */
// 当项目已启动，spring接口，spring加载之后，执行一个接口方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {
	// 读取配置文件内容
	@Value("${aliyun.oss.file.endpoint}")
	private String endpoint;

	@Value("${aliyun.oss.file.keyid}")
	private String keyid;

	@Value("${aliyun.oss.file.keysecret}")
	private String keysecret;

	@Value("${aliyun.oss.file.bucketname}")
	private String bucketname;

	// 定义公开静态变量
	public static String END_POINT;
	public static String KEY_ID;
	public static String KEY_SECRET;
	public static String BUCKET_NAME;

	@Override
	public void afterPropertiesSet() throws Exception {
		KEY_ID      = this.keyid;
		KEY_SECRET  = this.keysecret;
		END_POINT   = this.endpoint;
		BUCKET_NAME = this.bucketname;
	}

}
