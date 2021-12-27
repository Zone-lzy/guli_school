package com.lzy.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
//	实现上传头像到oss的功能
	String uploadFileAvatar(MultipartFile file);
}
