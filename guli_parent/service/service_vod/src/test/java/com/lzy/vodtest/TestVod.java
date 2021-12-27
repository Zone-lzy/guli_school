package com.lzy.vodtest;

import com.aliyuncs.DefaultAcsClient;

import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

public class TestVod {
	public static void main(String[] args) throws Exception {
		//1、根据视频id获取视频播放地址
		//创建初始化对象
		DefaultAcsClient cl = InitObject.initVodClient("LTAI5tDvKM8jyYPCm4ZQno6b", "YXSHytihfJVRc0V60WEJJya9leIxBl");
		//创建获取视频地址request对象和response对象
		GetPlayInfoRequest request = new GetPlayInfoRequest();
		GetPlayInfoResponse response = new GetPlayInfoResponse();
		//向request对象设置视频id值
		request.setVideoId("a2c8ddf41cea401d9e2fe7a8784f625d");

//		mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=3.10.2 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-3.10.2.jar

		//调用初始化对象里面的方法传递request，获取数据
		response = cl.getAcsResponse(request);
		List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
		//播放地址
		for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
			System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
			//PlayInfo.PlayURL = https://outin-5e688ec47b2c11ebb00500163e06123c.oss-cn-shanghai.aliyuncs.com/sv/52a57879-177f1fd5033/52a57879-177f1fd5033.mp4?Expires=1614680252&OSSAccessKeyId=LTAIrkwb21KyGjJl&Signature=FPlVfGbIDVGuvR3W8f2K4QcpATw%3D
		}
		//Base信息
		System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");//VideoBase.Title = 6 - What If I Want to Move Faster.mp4
	}

}
