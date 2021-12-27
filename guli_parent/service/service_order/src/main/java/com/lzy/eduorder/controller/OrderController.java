package com.lzy.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.commonutils.R;
import com.lzy.commonutils.jwt.JwtUtils;
import com.lzy.eduorder.entity.Order;
import com.lzy.eduorder.service.OrderService;
import com.lzy.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-23
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	//1.生成订单的方法
	@PostMapping("/createOrder/{courseId}")
	public R createOrder(@PathVariable String courseId, HttpServletRequest request){
		//从请求头中获取用户id
		String memberId = JwtUtils.getMemberIdByJwtToken(request);
		//判断是否登录
		if (StringUtils.isEmpty(memberId)){
			throw new GuliException(20001,"请登录");
		}

		//生成订单，并生成对应的订单号
		String orderNo = orderService.createOrders(courseId,memberId);

		return R.ok().data("orderNo",orderNo);
	}


	//2.根据订单号查询订单信息
	@GetMapping("/getOrderInfoById/{orderNo}")
	public R getOrderInfoById(@PathVariable String orderNo){
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("order_no",orderNo);
		Order order = orderService.getOne(wrapper);
		return R.ok().data("item",order);
	}

	// 根据课程id和用户id查看订单表中的订单状态
	@GetMapping("/isBuyCourse/{memberId}/{courseId}")
	public Boolean isBuyCourse(@PathVariable String memberId,@PathVariable String courseId) {
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id", courseId);
		wrapper.eq("member_id", memberId);
		wrapper.eq("status", 1);//支付状态 【1】代表已支付
		int result = orderService.count(wrapper);  // 表示是否可以查出记录

		if (result > 0) {//已支付
			return true;
		} else {
			return false;
		}
	}
}

