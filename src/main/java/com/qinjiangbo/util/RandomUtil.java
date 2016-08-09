package com.qinjiangbo.util;

import java.util.Random;

public class RandomUtil {
	
	/**
	 * 掷骰子，看此概率下是否成功
	 * @param probability 概率
	 * @return true--成功，false--失败
	 */
	public static boolean vote(float probability) {
		Random random = new Random();
		float value = random.nextFloat();
		while(value == 0.0f) {
			value = random.nextFloat();
		}
		if(value > 0.0f && value <= probability) {
			return true;
		}
		return false;
	}
}
