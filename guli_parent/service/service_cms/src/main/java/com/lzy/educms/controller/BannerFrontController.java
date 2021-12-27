package com.lzy.educms.controller;


import com.lzy.commonutils.R;
import com.lzy.educms.entity.CrmBanner;
import com.lzy.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lzy
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

	@Autowired
	private CrmBannerService bannerService;

	// 查询也所有banner
	@ApiOperation(value = "查询也所有banner")
	@GetMapping("getAllBanner")
	public R getAllBannner() {
		List<CrmBanner> crmBanners = bannerService.selectAllBanner();
		return R.ok().data("list", crmBanners);
	}

}

