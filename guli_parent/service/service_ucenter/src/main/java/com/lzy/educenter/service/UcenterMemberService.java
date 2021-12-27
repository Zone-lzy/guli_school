package com.lzy.educenter.service;

import com.lzy.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.educenter.entity.vo.LoginVo;
import com.lzy.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-19
 */
public interface UcenterMemberService extends IService<UcenterMember> {

	// 登录方法
	String login(UcenterMember member);

	// 会员注册
	void register(RegisterVo registerVo);

	//判断数据库是否存在相同的微信内容
	UcenterMember getMemberByOpenId(String openid);

	//根据日期，获取那天注册人数
	Integer getCountRegister(String day);
}
