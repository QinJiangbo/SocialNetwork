package com.qinjiangbo.util;

import java.util.UUID;

public class GUID {
	
	public static void main(String[] args) {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
	}
}
