package com.lzy.eduservice.client;


import com.lzy.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

// 运行成功的时候不会调用该实现了，当报错的时候会调用改类中对应出错的方法
@Component
public class VodFileDegradeFeignClient implements VodClient{
	@Override
	public R removeAliyunVideoById(String id) {
		return R.error().message("删除视频出错了");
	}

	@Override
	public R removeBatch(List<String> videoIdList) {
		return R.error().message("删除多条视频出错了");
	}
}
