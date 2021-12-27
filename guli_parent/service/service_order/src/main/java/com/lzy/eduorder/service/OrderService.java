package com.lzy.eduorder.service;

import com.lzy.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-23
 */
public interface OrderService extends IService<Order> {

	//生成订单，并生成对应的订单号
	String createOrders(String courseId, String memberId);
}
