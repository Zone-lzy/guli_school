package com.lzy.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
	//返回上传视频的id
	String uploadVideoAliyun(MultipartFile file);

	// 删除云端视频
	void removeAliyunVideoById(String id);

	// 删除多个视频
	void removeMoreVideo(List<String> videoIdList);

	//根据视频id获取视频凭证
	String getPlayAuth(String id);
}
