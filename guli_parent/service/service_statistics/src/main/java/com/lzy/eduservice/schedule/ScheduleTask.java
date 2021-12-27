package com.lzy.eduservice.schedule;

import com.lzy.eduservice.service.StatisticsDailyService;
import com.lzy.eduservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

	@Autowired
	private StatisticsDailyService dailyService;

	// 0/5 * * * * ?每隔五秒去执行一次
//	@Scheduled(cron = "0/5 * * * * ?")
//	public void task1() {
//		System.out.println("***********定时任务执行了");
//	}

	//在每天凌晨1点执行方法，把前一天的数据查询进行添加
	@Scheduled(cron = "0 0 1 * * ? ")//指定cron表达式规则
	public void task02(){
		dailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
	}

}
