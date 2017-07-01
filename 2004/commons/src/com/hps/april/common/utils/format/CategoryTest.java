package com.hps.april.common.utils.format;

import java.util.ArrayList;
import java.util.List;

public class CategoryTest {

	class ABC {
		
		public String a;
		public String b;
		public int c;

		public ABC(String a, String b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		public String toString() {
			return "ABC{a="+a+",b="+b+",c="+c+"}";
		}
	}
	
	
	public void testCategory(){
		final FormatService categoryService = new FormatService();
		List target = new ArrayList();
		target.add(new ABC("cat1", "cat2", 4));
		target.add(new ABC("cat1", "cat2", 5));
		target.add(new ABC("cat1", "cat2", 2));
		target.add(new ABC("22", "", 44));
		target.add(new ABC("rrrr", "", 432));
		
		List result = categoryService.categoryList(target, new CategoryChildCallback(){
			public Object defineCategory(Object item) {
				return ((ABC)item).a;					
			}

			public List defineChildren(List childrens) {
				return categoryService.categoryList(childrens, new CategoryCallback(){
					public Object defineCategory(Object item) {
						return ((ABC)item).b;
					}});
			}
		});
		
		System.out.println(result);
		System.out.println(result.size());
	}
	
	public static void main(String[] args) {
		CategoryTest categoryTest = new CategoryTest();
		categoryTest.testCategory();
	}
}
