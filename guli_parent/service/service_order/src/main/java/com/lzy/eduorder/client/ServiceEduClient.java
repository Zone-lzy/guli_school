package com.lzy.eduorder.client;

import com.lzy.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface ServiceEduClient {

	//3.根据课程id，查询课程信息【订单】
	@PostMapping("/eduservice/coursefront/getCourseInfoByIdOrder/{courseId}")
	public CourseWebVoOrder getCourseInfoByIdOrder(@PathVariable("courseId") String courseId);

}
