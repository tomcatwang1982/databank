package com.lakala.common;

import java.util.UUID;

public class UUIDUtils {
	public  static String getUUid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String []args){
		System.out.println(UUIDUtils.getUUid());
	}
}
