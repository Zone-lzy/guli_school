package com.lzy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.eduservice.entity.EduSubject;
import com.lzy.eduservice.entity.excel.SubjectData;
import com.lzy.eduservice.service.EduSubjectService;
import com.lzy.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

	// 监听器无法使用IOC容器里面的数据，，因为其不能交给springIOC容器进行处理，需要自己new，不能注入其他对象
	// 不能实现数据库操作，所以手动构造注入下
	public EduSubjectService subjectService;

	public SubjectExcelListener(EduSubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public SubjectExcelListener() {}


	@Override
	public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
		if(subjectData == null) {
			throw new GuliException(20001, "文件数据为空");
		}

		//一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
		//判断是否有一级分类是否重复
		EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
		if (existOneSubject == null){ //没有相同的一级分类，进行添加
			existOneSubject = new EduSubject();
			existOneSubject.setParentId("0"); //设置一级分类id值，0代表为一级分类
			existOneSubject.setTitle(subjectData.getOneSubjectName());//设置一级分类名
			subjectService.save(existOneSubject);//给数据库添加一级分类
		}

		//获取一级分类的id值
		String parent_id = existOneSubject.getId();
		//判断是否有耳机分类是否重复
		EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), parent_id);
		if (existTwoSubject==null){//没有相同的二级分类，进行添加
			existTwoSubject = new EduSubject();
			existTwoSubject.setParentId(parent_id); //设置二级分类id值
			existTwoSubject.setTitle(subjectData.getTwoSubjectName());//设置二级分类名
			subjectService.save(existTwoSubject);//给数据库添加二级分类
		}

	}

	// 判断一级分类不能重复添加
	private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title", name);
		wrapper.eq("parent_id", "0");
		EduSubject oneSubject = subjectService.getOne(wrapper);
		return oneSubject;
	}

	// 判断二级分类不能重复添加
	private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
		QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
		wrapper.eq("title", name);
		wrapper.eq("parent_id", pid);
		EduSubject twoSubject = subjectService.getOne(wrapper);
		return twoSubject;
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {

	}
}
