package excel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
	public static void main(String[] args) {
		//实现excel写操作
		//1、设置写入文件夹地址和excel文件名称
		String filename="D:\\program_workspace\\guli_parent\\service\\service_edu\\src\\test\\java\\excel\\DemoData.xlsx";

		//调用easyExcel里面的方法实现写操作
		//参数1：文件名称
		//参数2：对应实体类
		EasyExcel
				.write(filename,DemoData.class)
				.sheet("学生列表")
				.doWrite(getLists());
		method1();
		System.out.println("====================");
		method2();
	}

	//创建方法返回List集合
	private static List<DemoData> getLists(){
		ArrayList<DemoData> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			DemoData demoData = new DemoData();
			demoData.setSno(i);
			demoData.setSname("lzy ："+ i);
			list.add(demoData);
		}
		return list;
	}

	public static void method1() {
		//实现excel写操作
		//1、设置写入文件夹地址和excel文件名称
		String filename="D:\\program_workspace\\guli_parent\\service\\service_edu\\src\\test\\java\\excel\\DemoData2.xlsx";

		ExcelWriter excelWriter = EasyExcel.write(filename, DemoData.class)
				.build();
		WriteSheet build = EasyExcel.writerSheet("写入方法2").build();
		// 这里 需要指定写用哪个class去写
		excelWriter.write(getLists(),build);
		// 千万别忘记finish 会帮忙关闭流
		excelWriter.finish();
	}

	public static void method2() {
		String filename="D:\\program_workspace\\guli_parent\\service\\service_edu\\src\\test\\java\\excel\\DemoData.xlsx";

		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(filename,DemoData.class,new ExcelListener())
				.sheet().doRead();
	}

}
