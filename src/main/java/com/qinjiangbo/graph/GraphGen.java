package com.qinjiangbo.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qinjiangbo.enums.WriteMode;
import com.qinjiangbo.util.FileUtil;

public class GraphGen {
	private final int SCALE = 10000;
	private SNGraph graph = new SNGraph(SCALE);
	private static GraphGen snGraphGen = null;
	
	private GraphGen() {
		//禁止外部实例化
	}
	
	/**
	 * 获取唯一实例
	 * @return 返回社交网络图生成器唯一实例
	 */
	public static GraphGen getInstance() {
		if(snGraphGen == null) {
			synchronized (GraphGen.class) {
				if(snGraphGen == null) {
					snGraphGen = new GraphGen();
				}
			}
		}
		return snGraphGen;
	}
	
	/**
	 * 生成社交网络图并储存起来
	 * @throws IOException 
	 */
	public void saveNodes() throws IOException {
		// V1, V2,..., Vn --注意这个仅仅是名字而已，不代表图中的顺序，图是无序的
		for (int i = 0; i < SCALE; i++) {
			graph.insertVertex("V" + (i+1));
		}
		List<String> data = GenStrategy.wechatGen(SCALE, graph);
		String path = "/data/weico-graph.txt";
		long start = System.currentTimeMillis();
		FileUtil.writeFile(path, data, WriteMode.FileWriter);
		long end = System.currentTimeMillis();
		System.out.println("写入完成! 耗时：" + (end - start) + "ms");
	}
	
	/**
	 * 根据数据生成图
	 * @param data 节点数据
	 */
	public void generateGraph(List<String> data) {
		List<String> nodes = insertNodes(data);
		//System.out.println(nodes.size());
		int size = data.size();
		for(int i=0; i<size; i++) {
			String[] edge = data.get(i).split("-");
			int index1 = nodes.indexOf(edge[0]);
			int index2 = nodes.indexOf(edge[1]);
			float probability = Float.parseFloat(edge[2]);
			graph.insertEdge(index1, index2, probability);
		}
	}
	
	/**
	 * 构建图节点
	 * @param data 图数据
	 * @return 图节点链表
	 */
	private List<String> insertNodes(List<String> data) {
		int size = data.size();
		List<String> list = new ArrayList<String>();
		for(int i=0; i < size; i++) {
			String[] edge = data.get(i).split("-");
			for(int j=0; j<2; j++) {
				if(!list.contains(edge[j])) {
					list.add(edge[j]);
					graph.insertVertex(edge[j]);
				}
			}
		}
		return list;
	}
	
	/**
	 * 显示图结构 -- 邻接矩阵
	 */
	public void showGraph() {
		//调用社会网络图对象的showGraph方法
		graph.showGraph(SCALE);
	}
	
	/**
	 * 获取生成的社会网络图
	 * @return 社会网络图
	 */
	public SNGraph getGraph() {
		return graph;
	}
}
