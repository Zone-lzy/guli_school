package com.lzy.eduservice.controller;


import com.lzy.commonutils.R;
import com.lzy.eduservice.entity.subject.OneSubject;
import com.lzy.eduservice.service.EduSubjectService;
import com.lzy.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

	@Autowired
	private EduSubjectService subjectService;

	// 添加课程分类
	// 获取上传过来文件，将文件读取出来
	@PostMapping("addSubject")
	public R addSubject(MultipartFile file) {
		//上传过来excel文件
		subjectService.saveSubject(file, subjectService);
		System.out.println("===添加课程分类信息");
		return R.ok();
	}

	// 课程分类列表（树形）
	@ApiOperation(value = "嵌套数据列表")
	@GetMapping("/getAllSubject")
	public R getAllSubject() {
		// list集合泛型是一级分类
		List<OneSubject> list = subjectService.getAllOneTwoSubject();
		return R.ok().data("list", list);
	}
}

