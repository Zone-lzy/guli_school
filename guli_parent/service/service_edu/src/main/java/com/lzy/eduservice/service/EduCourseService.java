package com.lzy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.eduservice.entity.frontvo.CourseFrontVo;
import com.lzy.eduservice.entity.frontvo.CourseWebVo;
import com.lzy.eduservice.entity.vo.CourseInfoForm;
import com.lzy.eduservice.entity.vo.CoursePublishVo;
import com.lzy.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-09
 */
public interface EduCourseService extends IService<EduCourse> {

	//添加课程基本信息方法
	String saveCourseInfo(CourseInfoForm courseInfoForm);

	//根据课程id查询课程基本信息
	CourseInfoForm getCourseInfo(String courseId);

	//修改课程信息
	void updateCourseInfo(CourseInfoForm courseInfoForm);

	//根据课程id查询课程确认信息
	CoursePublishVo getPublishCourseInfo(String id);

	// 多条件分页查询
	void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

	//课程列表中删除课程方法
	boolean removeCourse(String id);

	// 查询前8个热门课程
	List<EduCourse> getListLimit();

	//1.条件查询带分页查询课程
	Map<String, Object> getFrontCorseList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

	//根据课程id，编写sql语句查询课程信息
	CourseWebVo getBaseCourseInfo(String courseId);
}
