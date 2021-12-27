package com.lzy.eduservice.service;

import com.lzy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-09
 */
public interface EduChapterService extends IService<EduChapter> {

	//获取课程大纲列表，根据课程id进行查询
	List<ChapterVo> getChapterVideoByCourseId(String courseId);

	//删除章节
	boolean deleteChapter(String chapterId);

	//2、根据课程id删除章节部分
	void removeChapterByCourseId(String id);
}
