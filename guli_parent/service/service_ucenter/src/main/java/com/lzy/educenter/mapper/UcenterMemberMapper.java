package com.lzy.educenter.mapper;

import com.lzy.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lzy
 * @since 2021-12-19
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

	//根据日期，获取那天注册人数
	Integer getCountRegister(String day);
}
