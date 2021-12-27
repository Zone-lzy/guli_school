package com.lzy.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.commonutils.R;
import com.lzy.commonutils.jwt.JwtUtils;
import com.lzy.commonutils.ordervo.CourseWebVoOrder;
import com.lzy.eduservice.client.OrdersClient;
import com.lzy.eduservice.entity.EduCourse;
import com.lzy.eduservice.entity.chapter.ChapterVo;
import com.lzy.eduservice.entity.frontvo.CourseFrontVo;
import com.lzy.eduservice.entity.frontvo.CourseWebVo;
import com.lzy.eduservice.service.EduChapterService;
import com.lzy.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

	@Autowired
	private EduChapterService eduChapterService;

	@Autowired
	private EduCourseService eduCourseService;

	@Autowired
	private OrdersClient ordersClient;

	//1.条件查询带分页查询课程
	@PostMapping("getFrontCorseList/{page}/{limit}")
	public R getFrontCorseList(@PathVariable long page, @PathVariable long limit,
							   @RequestBody(required = false)CourseFrontVo courseFrontVo) {
		Page<EduCourse> pageCourse = new Page<>(page, limit);
		Map<String, Object> map = eduCourseService.getFrontCorseList(pageCourse, courseFrontVo);
		// 返回分页所有数据
		return R.ok().data(map);
	}
	
	//2. 课程详情的方法
	@GetMapping("/getFrontCourseInfo/{courseId}")
	public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
		//根据课程id，编写sql语句查询课程信息
		CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

		//根据课程id，查询章节和小节信息
		List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(courseId);

		//获取用户id
		String memberId = JwtUtils.getMemberIdByJwtToken(request);
		boolean isBuyCourse = false;

		String token = request.getHeader("token");

		// 根据课程id和用户id查询当前课程是否被购买
		if (!StringUtils.isEmpty(memberId) && !StringUtils.isEmpty(token)){
			//根据课程id、用户id，查询课程是否已经购买
			isBuyCourse = ordersClient.isBuyCourse(memberId, courseId);
		}

		return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",isBuyCourse);
	}

	//3.根据课程id，查询课程信息【订单】
	@PostMapping("/getCourseInfoByIdOrder/{courseId}")
	public CourseWebVoOrder getCourseInfoByIdOrder(@PathVariable String courseId){
		CourseWebVo courseInfo = eduCourseService.getBaseCourseInfo(courseId);

		CourseWebVoOrder eduCourseVo = new CourseWebVoOrder();
		BeanUtils.copyProperties(courseInfo,eduCourseVo);

		return eduCourseVo;
	}
}