package com.lzy.eduorder.client;

import com.lzy.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-ucenter")
public interface ServiceUcenterClient {

	//根据用户id，获取用户信息
	@PostMapping("/educenter/member/getMemberInfoById/{memberId}")
	public UcenterMemberOrder getMemberInfoById(@PathVariable("memberId") String memberId);

}
