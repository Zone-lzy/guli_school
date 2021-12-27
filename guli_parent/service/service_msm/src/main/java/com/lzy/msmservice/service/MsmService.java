package com.lzy.msmservice.service;

import java.util.HashMap;

public interface MsmService {
	//调用service中发送短信的方法
	boolean sendMsm(HashMap<String, Object> map, String phone);
}
