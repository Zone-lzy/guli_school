package com.lzy.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.commonutils.R;
import com.lzy.eduservice.entity.EduTeacher;
import com.lzy.eduservice.entity.vo.TeacherQuery;
import com.lzy.eduservice.service.EduTeacherService;
import com.lzy.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-04
 */
@Api(description = "讲师管理模块")
@RestController
@CrossOrigin  //解决跨域
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

	// 注入service
	@Autowired
	private EduTeacherService teacherService;

	// 1. 测试环境 ，查询讲师表所有数据
	// rest风格
	//查询讲师表所有数据
	@ApiOperation(value = "查询讲师表所有数据")
	@GetMapping("/findAll")
	public R findAllTeacher(){
		// 调用service的方法实现查询所有
		List<EduTeacher> list = teacherService.list(null);
		return R.ok().data("items", list);
	}

	//2.逻辑删除讲师
	@ApiOperation(value = "逻辑删除讲师")
	@DeleteMapping("/{id}")
	public R deleteTeacherById(@PathVariable String id){
		boolean flag = teacherService.removeById(id);
		if(flag) return R.ok();
		return R.error();
	}

	//3.分页查询
	//page：当前页
	//limit：每页显示记录数
	@ApiOperation(value = "分页讲师列表")
	@GetMapping("/pageList/{page}/{limit}")
	public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
					  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit
	){
		// 创建page对象
		Page<EduTeacher> pageTeacher = new Page<>(page, limit);

//		try {
//			// 模拟异常
//			int i = 10 / 0;
//		} catch (Exception e) {
//			throw new GuliException(20001, "执行了自定义异常处理");
//		}

		// 调用方法实现分页
		// 调用方法的时候，底层封装，把分页所有数据封装到pageTeacher对象里面
		teacherService.page(pageTeacher,null);
		//获取查询到的数据
		List<EduTeacher> records = pageTeacher.getRecords();
		//获取总记录数
		long total = pageTeacher.getTotal();
		// 两种写法
		//1.直接链式后面继续写
//		return R.ok().data("total", total).data("rows", records);
		// 2.创建一个map集合
	/*	Map map = new HashMap();
		map.put("total", total);
		map.put("rows", records);*/
		return R.ok().data("total",total).data("rows",records);
	}

	//4.条件查询带分页
	//多条件查询讲师带分页
	@ApiOperation(value = "多条件查询讲师带分页")
	@PostMapping("/pageTeacherCondition/{page}/{limit}")
	public R pageTeacherCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
								  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
								  @RequestBody(required = false) TeacherQuery teacherQuery){//通过封装TeacherQuery对象来直接传递查询条件
		//创建分页page对象
		Page<EduTeacher> pageParam = new Page<>(page, limit);

		//调用方法实现多条件分页查询    ----》 最后 查询的结果会返回到Page对象里面
		teacherService.pageQuery(pageParam,teacherQuery);

		//获取查询到的数据
		List<EduTeacher> records = pageParam.getRecords();

		//获取总记录数
		long total = pageParam.getTotal();
		return R.ok().data("total",total).data("rows",records);
	}

	// 5、添加讲师接口的方法
	@ApiOperation(value = "添加讲师接口的方法")
	@PostMapping("addTeacher")
	public R addTeacher(@RequestBody EduTeacher eduTeacher) {
		boolean save = teacherService.save(eduTeacher);
		if(save) return R.ok();
		return R.error();
	}

	// 6、根据讲师id进行查询
	@ApiModelProperty(value = "根据id查询")
	@GetMapping("/getById/{id}")
	public R getById(@PathVariable String id){
		EduTeacher teacher = teacherService.getById(id);
		return R.ok().data("item",teacher);
	}

	// 7、修改讲师
	@ApiModelProperty(value = "修改讲师")
	@PostMapping("/updateById")
	public R updateById(@RequestBody EduTeacher teacher){
		boolean flag = teacherService.updateById(teacher);
		if (flag){
			return R.ok();
		}else {
			return R.error();
		}
	}





}

