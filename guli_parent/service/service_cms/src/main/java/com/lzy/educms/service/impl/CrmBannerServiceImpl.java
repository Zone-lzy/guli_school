package com.lzy.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzy.educms.entity.CrmBanner;
import com.lzy.educms.mapper.CrmBannerMapper;
import com.lzy.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lzy
 * @since 2021-12-17
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

	// 查询所有banner
	@Override
	@Cacheable(key = "'selectIndexList'", value = "banner")
	public List<CrmBanner> selectAllBanner() {
		// 根据id进行降序排列，显示排列之后前两条记录
		QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("id");
		// 使用mq的last方法，进行语句的拼接
		wrapper.last("limit 2");

		List<CrmBanner> list = baseMapper.selectList(wrapper);
		return list;
	}
}
