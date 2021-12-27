package com.lzy.vodtest;

public class Test {
	public static void main(String[] args) {
		Test.M test = new Test().new M();
		test.methogs();

	}

	private String name = "3";
	public class M {
		private String name = "1";

		public void methogs() {
			String name = "2";
			System.out.println(name);
			System.out.println(this.name);
//			System.out.println(this.name);
//		Test t = new Test();
			System.out.println(Test.this.name);
		}
	}
}
