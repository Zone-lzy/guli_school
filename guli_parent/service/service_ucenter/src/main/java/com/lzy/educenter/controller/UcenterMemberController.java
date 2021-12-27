package com.lzy.educenter.controller;


import com.lzy.commonutils.R;
import com.lzy.commonutils.jwt.JwtUtils;
import com.lzy.educenter.entity.UcenterMember;
import com.lzy.educenter.entity.vo.LoginVo;
import com.lzy.educenter.entity.vo.RegisterVo;
import com.lzy.educenter.entity.vo.UcenterMemberVo;
import com.lzy.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

	@Autowired
	private UcenterMemberService memberService;

	// 登录
	@ApiOperation("会员登录")
	@PostMapping("/login")
	public R login(@RequestBody UcenterMember member) {
		String token = memberService.login(member);

		return R.ok().data("token", token);
	}

	// 注册
	@ApiOperation("会员注册")
	@PostMapping("register")
	public R register(@RequestBody RegisterVo registerVo) {
		memberService.register(registerVo);
		return R.ok();
	}

	// 根据token获取用户信息
	 @GetMapping("getMemberInfo")
	public R getMemberInfo(HttpServletRequest request) {
		// 调用jwt工具类的方法，根据request对象获取头像细腻些，返回用户id
		 String memberId = JwtUtils.getMemberIdByJwtToken(request);
		 // 查询数据库根据用户id获取用户数据
		 UcenterMember member = memberService.getById(memberId);
		 return R.ok().data("userInfo", member);
	}


	//根据用户id查询用户信息
	@PostMapping("/getMemberInfoById/{memberId}")
	public UcenterMemberVo getMemberInfoById(@PathVariable String memberId){
		UcenterMember member = memberService.getById(memberId);
		UcenterMemberVo memberVo = new UcenterMemberVo();
		BeanUtils.copyProperties(member,memberVo);
		return memberVo;
	}


	//根据日期，获取那天注册人数
	@GetMapping("/countRegister/{day}")
	public R countRegister(@PathVariable String day){
		Integer count = memberService.getCountRegister(day);
		return R.ok().data("countRegister",count);
	}

}

