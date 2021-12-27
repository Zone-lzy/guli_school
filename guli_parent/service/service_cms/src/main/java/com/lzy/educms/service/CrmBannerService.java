package com.lzy.educms.service;

import com.lzy.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lzy
 * @since 2021-12-17
 */
public interface CrmBannerService extends IService<CrmBanner> {

	// 查询所有banner
	public List<CrmBanner> selectAllBanner();
}
