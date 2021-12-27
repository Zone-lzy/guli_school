package com.lzy.eduservice.controller;


import com.lzy.commonutils.R;
import com.lzy.eduservice.client.VodClient;
import com.lzy.eduservice.entity.EduVideo;
import com.lzy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin //解决跨域问题
public class EduVideoController {

	@Autowired
	private VodClient vodClient;

	@Autowired
	private EduVideoService eduVideoService;

	//添加小节
	@PostMapping("/addVideo")
	public R addVideo(@RequestBody EduVideo eduVideo){
		eduVideoService.save(eduVideo);
		return R.ok();
	}


	//删除小节
	// TODO 后面这个方法需要完善，删除小节的时候，同时也要把视频删除
	@DeleteMapping("/deleteVideo/{id}")
	public R deleteVideo(@PathVariable String id){
		// 根据小结id得到视频id
		EduVideo eduVideo = eduVideoService.getById(id);
		String videoSourceId = eduVideo.getVideoSourceId();
		// 判断不为空，才删除
		if(!StringUtils.isEmpty(videoSourceId)) {
			// 删除视频
			vodClient.removeAliyunVideoById("视频id");
		}
		// 删除小节
		eduVideoService.removeById(id);
		return R.ok();
	}

	//修改小节
	@PostMapping("/updateVideo")
	public R updateVideo(@RequestBody EduVideo eduVideo){
		eduVideoService.updateById(eduVideo);
		return R.ok();
	}

	//根据小节id查询
	@GetMapping("/getVideoById/{videoId}")
	public R getVideoById(@PathVariable String videoId){
		EduVideo eduVideo = eduVideoService.getById(videoId);
		return R.ok().data("video",eduVideo);
	}


}

