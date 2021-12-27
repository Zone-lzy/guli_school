package com.lzy.eduservice.client;

import com.lzy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component // 交给spring容器管理
public interface VodClient {

	//根据视频id删除阿里云视频
	@DeleteMapping("/eduvod/video/removeAliyunVideoById/{id}") // 路径改为绝对路径
	public R removeAliyunVideoById(@PathVariable("id") String id);

	// 删除多个视频
	//根据id删除多个阿里云视频
	@DeleteMapping("/eduvod/video/removeBatch")
	public R removeBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
