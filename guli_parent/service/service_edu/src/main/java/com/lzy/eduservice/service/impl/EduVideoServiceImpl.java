package com.lzy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.eduservice.client.VodClient;
import com.lzy.eduservice.entity.EduVideo;
import com.lzy.eduservice.mapper.EduVideoMapper;
import com.lzy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lzy
 * @since 2021-12-09
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

	@Autowired
	private VodClient vodClient;

	//根据课程id删除小节
	// TODO 删除小节，要删除对应的视频文件
	@Override
	public void removeVideoByCourseId(String id) {
		// 1、根据课程查询所有的视频id,只把视频id查询出去
		QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("course_id", id);
		queryWrapper.select("video_source_id");
		List<EduVideo> eduVideoList = baseMapper.selectList(queryWrapper);

		// 将集合的值取出来，变成List<String>
		List<String> videoIds = new ArrayList<>();
		for (int i = 0; i < eduVideoList.size(); i++) {
			EduVideo eduVideo = eduVideoList.get(i);
			// 放到videoIds的集合里面
			String videoSourceId = eduVideo.getVideoSourceId();
			if(!StringUtils.isEmpty(videoIds)) {
				videoIds.add(videoSourceId);
			}
		}

		if(videoIds.size() > 0) {
			// 多个视频删除id
			vodClient.removeBatch(videoIds);
		}

		QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id",id);
		baseMapper.delete(wrapper);
	}

}
