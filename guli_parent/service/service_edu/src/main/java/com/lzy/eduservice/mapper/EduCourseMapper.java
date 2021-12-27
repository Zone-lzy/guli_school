package com.lzy.eduservice.mapper;

import com.lzy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.eduservice.entity.frontvo.CourseWebVo;
import com.lzy.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2021-12-09
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
	public CoursePublishVo getPublishCourseInfo(String courseId);

	//根据课程id，编写sql语句查询课程信息
	CourseWebVo getBaseCourseInfo(String courseId);
}
