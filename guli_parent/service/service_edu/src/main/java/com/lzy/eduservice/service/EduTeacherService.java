package com.lzy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-04
 */
public interface EduTeacherService extends IService<EduTeacher> {

	// 多条件分页查询
	void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

	// 查询前四个热门讲师
	List<EduTeacher> getListLimit();

	//前台系统分页查询讲师的方法
	Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage);
}
