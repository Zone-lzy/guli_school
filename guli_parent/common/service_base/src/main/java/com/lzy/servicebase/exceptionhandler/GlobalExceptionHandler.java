package com.lzy.servicebase.exceptionhandler;

import com.lzy.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice // 使得异常处理类启动时生效
@Slf4j
public class GlobalExceptionHandler {

	// 指定出现什么异常执行这个方法
	@ExceptionHandler(Exception.class)
	@ResponseBody  // 为了返回数据
	public R error(Exception e){
		e.printStackTrace();;
		return R.error().message("执行了全局异常处理");
	}

	// 特定异常
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public R error(ArithmeticException e) {
		e.printStackTrace();
		return R.error().message("执行力ArithmeticException异常处理...");
	}

	// 自定义异常
	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public R error(GuliException e) {
		log.error(e.getMessage());
		e.printStackTrace();
		return R.error().code(e.getCode()).message(e.getMsg()); //e.getMessage() 不要写这
 	}

}

