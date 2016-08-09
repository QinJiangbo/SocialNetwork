package com.qinjiangbo.greedyhill;

import java.io.IOException;

import com.qinjiangbo.spread.KK;

public class SearchTopK {

	public static void main(String[] args) {
		KK greedyHill = KK.getInstance();
		try {
			long start = System.currentTimeMillis();
			greedyHill.starts(100, 10000);
			long end = System.currentTimeMillis();
			System.out.println("查找结束,耗时"+(end-start)+"ms");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
