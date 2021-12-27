package com.lzy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lzy.oss.service.OssService;
import com.lzy.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

	//上传头像到OSS
	@Override
	public String uploadFileAvatar(MultipartFile file) {
		//工具类获取值
		String endpoint = ConstantPropertiesUtils.END_POINT;
		String accessKeyId = ConstantPropertiesUtils.KEY_ID;
		String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
		String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

		InputStream inputStream = null;

		try {
			// 创建OSS实例。
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

			// 获取上传文件的输入流
			inputStream = file.getInputStream();

			//获取文件名称
			String fileName = file.getOriginalFilename();

			// UUID生成随机唯一的一个值,因为生成的uuid是带有"-"的字符串。
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			fileName = uuid + fileName;

			// 把文件按照日期进行分类
			// 2021/12/7/iu.jpg
			// 获取当前日期
/*			Date date=new Date();
			SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date2=temp.format(date);
			Date date3=temp.parse(date2);*/
			String datePath = new DateTime().toString("yyyy/MM/dd"); //  2021/12/7

			// 拼接
			// 2021/12/7/iu.jpg
			fileName = datePath + "/" + fileName;


			//调用oss实例中的方法实现上传
			//参数1： Bucket名称
			//参数2： 上传到oss文件路径和文件名称 /aa/bb/1.jpg
			//参数3： 上传文件的输入流
			ossClient.putObject(bucketName, fileName, inputStream);
			// 关闭OSSClient。
			ossClient.shutdown();

			/**
			 * aliyun.oss.file.endpoint=oss-cn-hangzhou.aliyuncs.com
			 * aliyun.oss.file.keyid=LTAI5tDvKM8jyYPCm4ZQno6b
			 * aliyun.oss.file.keysecret=YXSHytihfJVRc0V60WEJJya9leIxBl
			 * aliyun.oss.file.bucketname=lzy-gili-edu
			 */
			//把上传后文件路径返回
			//需要把上传到阿里云oss路径手动拼接出来
			// 这里http和https都是可以的
			//https://lzy-gili-edu.oss-cn-hangzhou.aliyuncs.com/iu3.jpg
			String url = "https://"+bucketName+"."+endpoint+"/"+fileName ;

			System.out.println(url);

			return url;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}