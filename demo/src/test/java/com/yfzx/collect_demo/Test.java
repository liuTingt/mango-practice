package com.yfzx.collect_demo;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(5);
		list.add("您");
		list.add("好");
		list.add("啊");
		list.add("朋");
		list.add("有");
		for(int i = 0; i<list.size(); i++) {
			if(i ==2) { 
				list.remove(i); 
			} 
		}
		System.out.println(list.toString());
		
	
		
	}
}
