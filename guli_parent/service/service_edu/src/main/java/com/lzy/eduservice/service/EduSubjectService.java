package com.lzy.eduservice.service;

import com.lzy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-08
 */
public interface EduSubjectService extends IService<EduSubject> {

	// 添加课程分类
	void saveSubject(MultipartFile file, EduSubjectService subjectService);

	// 课程分类列表（树形）
	List<OneSubject> getAllOneTwoSubject();
}
