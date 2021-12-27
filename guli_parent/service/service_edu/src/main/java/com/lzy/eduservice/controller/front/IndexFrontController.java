package com.lzy.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.commonutils.R;
import com.lzy.eduservice.entity.EduCourse;
import com.lzy.eduservice.entity.EduTeacher;
import com.lzy.eduservice.service.EduCourseService;
import com.lzy.eduservice.service.EduTeacherService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@RestController
@CrossOrigin
@RequestMapping("/eduservice/inddexfront")
public class IndexFrontController {

	@Autowired
	private EduCourseService courseService;

	@Autowired
	private EduTeacherService eduTeacherService;


	// 查询前八条热门课程，查询前4条名师
	@GetMapping("index")
	public R index() {

		List<EduCourse> courseList = courseService.getListLimit();

		List<EduTeacher> teacherList = eduTeacherService.getListLimit();

		return R.ok().data("eduList", courseList).data("teacherList", teacherList);
	}
}
