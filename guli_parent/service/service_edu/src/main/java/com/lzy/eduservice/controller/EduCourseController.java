package com.lzy.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.commonutils.R;
import com.lzy.eduservice.entity.EduCourse;
import com.lzy.eduservice.entity.vo.CourseInfoForm;
import com.lzy.eduservice.entity.vo.CoursePublishVo;
import com.lzy.eduservice.entity.vo.CourseQuery;
import com.lzy.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 *
 * @author lzy
 * @since 2021-12-09
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

	@Autowired
	private EduCourseService eduCourseService;

	//添加课程基本信息方法
	@PostMapping("/addCourseInfo")
	public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
		String id = eduCourseService.saveCourseInfo(courseInfoForm);
		return R.ok().data("courseId", id);
	}

	//根据课程id查询课程基本信息
	@GetMapping("/getCourseInfoById/{courseId}")
	public R getCourseInfoById(@PathVariable String courseId){
		CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(courseId);
		return R.ok().data("courseInfoForm",courseInfoForm);
	}

	//修改课程信息
	@PostMapping("/updateCourseInfo")
	public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
		eduCourseService.updateCourseInfo(courseInfoForm);
		return R.ok();
	}

	//根据课程id查询课程确认信息
	@GetMapping("/getpublishCourseInfo/{id}")
	public R getpublishCourseInfo(@PathVariable String id){
		CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
		return R.ok().data("publishCourse",publishCourseInfo);
	}

	// 课程的最终发布
	@PostMapping("publishCourse/{id}")
	public R publishCourse(@PathVariable String id){
		EduCourse eduCourse = new EduCourse();
		eduCourse.setStatus("Normal"); //设置课程发布状态
		eduCourse.setId(id);
		boolean flag = eduCourseService.updateById(eduCourse);
		if (flag){
			return R.ok();
		}else {
			return R.error();
		}
	}


	//课程列表 基本实现
	@GetMapping("/getCourseList")
	public R getCourseList(){
		List<EduCourse> list = eduCourseService.list(null);
		return R.ok().data("list",list);
	}

	//多条件查询课程带分页
	@ApiOperation(value = "多条件查询课程带分页")
	@PostMapping("/pageCourseCondition/{page}/{limit}")
	public R pageCourseCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
								 @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
								 @RequestBody(required = false) CourseQuery courseQuery){//通过封装courseQuery对象来直接传递查询条件
		//创建分页page对象
		Page<EduCourse> pageParam = new Page<>(page, limit);

		//调用方法实现多条件分页查询
		eduCourseService.pageQuery(pageParam,courseQuery);

		//获取查询到的数据
		List<EduCourse> records = pageParam.getRecords();

		//获取总记录数
		long total = pageParam.getTotal();
		return R.ok().data("total",total).data("rows",records);
	}

	//课程列表中删除课程方法
	@DeleteMapping("/removeCourseById/{id}")
	public R removeCourseById(@PathVariable String id){
		boolean flag = eduCourseService.removeCourse(id);
		if (flag){
			return R.ok();
		}else {
			return R.error();
		}
	}


}

