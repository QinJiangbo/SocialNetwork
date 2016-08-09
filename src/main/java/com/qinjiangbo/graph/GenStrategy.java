package com.qinjiangbo.graph;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenStrategy {
	
	/**
	 * 服从平均分布的用户生成策略，即每个人都拥有差不多的社会关系
	 * @param SCALE 用户规模
	 * @param graph 社会关系图对象
	 * @return 社会关系链表
	 */
	public static List<String> normalGen(int SCALE, SNGraph graph) {
		List<String> data = new ArrayList<String>();
		for(int i=0; i < SCALE; i++) {
			Random random = new Random();
			int loopCount = random.nextInt(SCALE)+1;
			for(int k=0; k < loopCount; k++) {
				int index = random.nextInt(SCALE); // [0, SCALE) - 下一个节点的索引
				double weight = Math.random(); // [0.0, 1.0) - 影响概率
				NumberFormat format = new DecimalFormat("0.0");
				format.setRoundingMode(RoundingMode.DOWN);
				float probability = Float.parseFloat(format.format(weight));
				if(i != index) {
					String dataStr = graph.getVertexByIndex(i) + "-" + graph.getVertexByIndex(index) + "-" + probability + "\n";
					data.add(dataStr);
				}
			}
		}
		return data;
	}
	
	/**
	 * 服从微博,Twitter,Facebook等社交平台的粉丝分布规则，即陌生人社交
	 * @param SCALE 用户规模
	 * @param graph 社会关系图对象
	 * @return 社会关系链表
	 */
	public static List<String> weiboGen(int SCALE, SNGraph graph) {
		int bigV = (int) (SCALE * 0.0015);
		int count = 0;
		List<String> data = new ArrayList<String>();
		for(int i=0; i < SCALE; i++) {
			Random random = new Random();
			int loopNum = 0;
			int threshold = 0;
			if(count <= bigV) {
				threshold = (int) (SCALE * 0.0030);
				loopNum = random.nextInt(threshold) + 1;
				count++;
			}
			else {
				threshold = (int) (SCALE * 0.0005);
				loopNum = random.nextInt(threshold) + 1;
			}
			for(int k=0; k<loopNum; k++) {
				int index = random.nextInt(SCALE); // [0, SCALE) - 下一个节点的索引
				double weight = Math.random(); // [0.0, 1.0) - 影响概率
				NumberFormat format = new DecimalFormat("0.0");
				format.setRoundingMode(RoundingMode.DOWN);
				float probability = Float.parseFloat(format.format(weight));
				if(i != index) {
					String dataStr = graph.getVertexByIndex(i) + "-" + graph.getVertexByIndex(index) + "-" + probability + "\n";
					data.add(dataStr);
				}
			}
		}
		return data;
	}
	
	/**
	 * 服从QQ, 微信等社交平台的好友分布规则，即熟人社交
	 * @param SCALE 用户规模
	 * @param graph 社会关系图对象
	 * @return 社会关系链表
	 */
	public static List<String> wechatGen(int SCALE, SNGraph graph) {
		List<String> data = new ArrayList<String>();
		int circleNum = 100; //一百个圈子，每个圈子100人
		for(int i=1; i<=circleNum; i=i+1) {
			Random random = new Random();
			int loopNum = random.nextInt(100) + 1;
			for(int j = 100*(i-1); j<100*i; j++) {
				for(int k=0; k<loopNum; k++) {
					int index = random.nextInt(100)+100*(i-1); // 下一个节点的索引
					double weight = Math.random(); // [0.0, 1.0) - 影响概率
					NumberFormat format = new DecimalFormat("0.0");
					format.setRoundingMode(RoundingMode.DOWN);
					float probability = Float.parseFloat(format.format(weight));
					if(j != index) {
						String dataStr = graph.getVertexByIndex(j) + "-" + graph.getVertexByIndex(index) + "-" + probability + "\n";
						data.add(dataStr);
					}
				}
			}
			int count = random.nextInt(10);
			for(int m=0; m<count; m++) {
				int j = random.nextInt(100)+100*(i-1);
				int num = random.nextInt(getOutCircle(i, SCALE).size());
				int index = getOutCircle(i, SCALE).get(num);
				double weight = Math.random(); // [0.0, 1.0) - 影响概率
				NumberFormat format = new DecimalFormat("0.0");
				format.setRoundingMode(RoundingMode.DOWN);
				float probability = Float.parseFloat(format.format(weight));
				if(j != index) {
					String dataStr = graph.getVertexByIndex(j) + "-" + graph.getVertexByIndex(index) + "-" + probability + "\n";
					data.add(dataStr);
				}
			}
		}
		return data;
	}
	
	/**
	 * 得到圈子外的人数
	 * @param index
	 * @return
	 */
	private static List<Integer> getOutCircle(int index, int SCALE) {
		List<Integer> list = new ArrayList<Integer>();
		if(index == 1) {
			for(int i=100*index; i<SCALE; i++) {
				list.add(i);
			}
		}
		else if(index == 100) {
			for(int i=0; i< (index-1)*100; i++) {
				list.add(i);
			}
		}
		else {
			for(int i=0; i<(index-1)*100; i++) {
				list.add(i);
			}
			for(int i=(index)*100; i<SCALE; i++) {
				list.add(i);
			}
		}
		return list;
	}
}
