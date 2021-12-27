package com.lzy.educms.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.commonutils.R;
import com.lzy.educms.entity.CrmBanner;
import com.lzy.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/educms/banner")
@CrossOrigin
public class BannerAdminController {

	@Autowired
	private CrmBannerService crmBannerService;

	// 1、分页查询banner
	@GetMapping("pageBanner/{page}/{limit}")
	public R pageBanner(@PathVariable long page, @PathVariable long limit) {
		Page<CrmBanner> pageBanner = new Page<>(page, limit);
		crmBannerService.page(pageBanner, null);
		return R.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
	}

	// 添加banner
	@PostMapping("addBanner")
	public R addBanner(@RequestBody CrmBanner crmBanner) {
		crmBannerService.save(crmBanner);
		return R.ok();
	}

	// 获取banner
	@GetMapping("get/{id}")
	public R get(@PathVariable String id) {
		CrmBanner banner = crmBannerService.getById(id);
		return R.ok().data("item", banner);
	}

	// 修改banner
	@PutMapping("update")
	public R updateById(@RequestBody CrmBanner crmBanner) {
		crmBannerService.updateById(crmBanner);
		return R.ok();
	}

	// 删除banner
	@DeleteMapping("remove/{id}")
	public R remove(@PathVariable String id) {
		crmBannerService.removeById(id);
		return  R.ok();
	}
}

