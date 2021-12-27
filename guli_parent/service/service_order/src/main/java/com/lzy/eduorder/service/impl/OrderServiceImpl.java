package com.lzy.eduorder.service.impl;

import com.lzy.commonutils.ordervo.CourseWebVoOrder;
import com.lzy.commonutils.ordervo.UcenterMemberOrder;
import com.lzy.eduorder.client.ServiceEduClient;
import com.lzy.eduorder.client.ServiceUcenterClient;
import com.lzy.eduorder.entity.Order;
import com.lzy.eduorder.mapper.OrderMapper;
import com.lzy.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lzy
 * @since 2021-12-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	//远程调用serviceEdu
	@Autowired
	private ServiceEduClient serviceEduClient;

	//远程调用serviceUcenter
	@Autowired
	private ServiceUcenterClient serviceUcenterClient;
	
	//生成订单，并生成对应的订单号
	@Override
	public String createOrders(String courseId, String memberId) {
		//根据用户id，获取用户信息
		UcenterMemberOrder memberInfo = serviceUcenterClient.getMemberInfoById(memberId);

		//根据课程id，获取课程信息
		CourseWebVoOrder courseInfo = serviceEduClient.getCourseInfoByIdOrder(courseId);

		Order order = new Order();
		order.setMobile(memberInfo.getMobile());
		order.setNickname(memberInfo.getNickname());
		order.setMemberId(memberId);
		order.setCourseCover(courseInfo.getCover());
		order.setCourseId(courseId);
		order.setCourseTitle(courseInfo.getTitle());
		order.setTeacherName(courseInfo.getTeacherName());
		order.setTotalFee(courseInfo.getPrice());
		order.setStatus(0);//支付状态：（ 0：已支付，1：未支付 ）
		order.setPayType(1);//支付类型： 1：微信 ， 2：支付宝
		order.setOrderNo(OrderNoUtil.getOrderNo()); //订单号

		//保存订单
		baseMapper.insert(order);

		//返回订单号
		return order.getOrderNo();
	}
}