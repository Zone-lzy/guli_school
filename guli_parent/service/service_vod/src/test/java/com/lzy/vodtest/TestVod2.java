package com.lzy.vodtest;
import com.aliyuncs.DefaultAcsClient;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;
public class TestVod2 {
	public static void main(String[] args) throws ClientException {
		//创建初始化对象
		DefaultAcsClient cl = InitObject.initVodClient("LTAI5tDvKM8jyYPCm4ZQno6b", "YXSHytihfJVRc0V60WEJJya9leIxBl");
		//创建获取视频地址request对象和response对象
		GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
		//向request对象设置视频id值
		request.setVideoId("a2c8ddf41cea401d9e2fe7a8784f625d");

		GetVideoPlayAuthResponse response = cl.getAcsResponse(request);

		//播放凭证
		System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
		//VideoMeta信息
		System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");

	}
}
