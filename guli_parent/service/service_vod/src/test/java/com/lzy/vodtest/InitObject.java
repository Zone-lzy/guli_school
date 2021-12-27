package com.lzy.vodtest;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;

import com.aliyuncs.profile.DefaultProfile;

//初始化类
public class InitObject {

/*	public static void main(String[] args) {
		System.out.println("12");
	}*/

	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
		String regionId = "cn-shanghai";  // 点播服务接入区域
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		return client;
	}

	//'InitObject()' is already defined in 'com.lzy.vodtest.InitObject'
//	public InitObject() {}
//
//	public InitObject() {}
//
//	public void add() {}

/*	public int add(int a) {
		return a;
	}
	public void add(float a) {}
	public void add(float a, int b) {}
	public void add(int b, float a) {}*/
}
