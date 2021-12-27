package com.lzy.vod.controller;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.lzy.commonutils.R;
import com.lzy.servicebase.exceptionhandler.GuliException;
import com.lzy.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {

	@Autowired
	private VodService vodService;
	//上传视频到阿里云
	@PostMapping("/uploadAliyunVideo")
	public R uploadAliyunVideo(MultipartFile file){
		//返回上传视频的id
		String videoId = vodService.uploadVideoAliyun(file);
		return R.ok().data("videoId",videoId);
	}

	@GetMapping("/test")
	public String uploadAliyunVideo(){
		String accessKeyId = "LTAI5tDvKM8jyYPCm4ZQno6b";
		String accessKeySecret = "YXSHytihfJVRc0V60WEJJya9leIxBl";
		String title = "6 - What If I Want to Move Faster"; //上传之后文件的名称
		String fileName = "D:\\桌面\\项目\\2.在线教育-谷粒学宛\\项目资料\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4"; //本地文件的路径和名称


		UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);

//		 可指定分片上传时每个分片的大小，默认为2M字节
		request.setPartSize(2 * 1024 * 1024L);
//		 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）
		request.setTaskNum(1);

		UploadVideoImpl uploader = new UploadVideoImpl();
		UploadVideoResponse response = uploader.uploadVideo(request);
		if (response.isSuccess()) {
			System.out.print("VideoId=" + response.getVideoId() + "\n"); //获取到上传视频的id
		} else {
//			 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
			System.out.print("VideoId=" + response.getVideoId() + "\n");
			System.out.print("ErrorCode=" + response.getCode() + "\n");
			System.out.print("ErrorMessage=" + response.getMessage() + "\n");
		}
		return response.getVideoId();
	}

	//根据视频id删除阿里云视频
	@DeleteMapping("/removeAliyunVideoById/{id}")
	public R removeAliyunVideoById(@PathVariable String id){
		vodService.removeAliyunVideoById(id);
		return R.ok();
	}

	// 删除多个视频
	//根据id删除多个阿里云视频
	@DeleteMapping("/removeBatch")
	public R removeBatch(@RequestParam("videoIdList") List<String> videoIdList){
		vodService.removeMoreVideo(videoIdList);
		return R.ok();
	}

	//根据视频id获取视频凭证
	@GetMapping("/getPlayAuth/{id}")
	public R getPlayAuth(@PathVariable String id){
		try {
			String playAuth = vodService.getPlayAuth(id);
			return R.ok().data("playAuth",playAuth);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GuliException(20001,"获取视频凭证失败");
		}
	}


}
