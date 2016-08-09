package com.qinjiangbo.spread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qinjiangbo.enums.WriteMode;
import com.qinjiangbo.graph.GraphGen;
import com.qinjiangbo.graph.SNGraph;
import com.qinjiangbo.util.FileUtil;

public class KK {
	
	public static KK kk = null;
	private List<String> nodes = new ArrayList<String>();
	
	private KK() {
		
	}
	
	public static KK getInstance() {
		if(kk == null) {
			synchronized (KK.class) {
				if(kk == null) {
					kk = new KK();
				}
			}
		}
		return kk;
	}
	
	/**
	 * 开始贪心算法
	 * @param topK 影响最大的K个节点
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void starts(int topK, int SCALE) throws IOException {
		List<String> data = FileUtil.readFile("/data/weico-graph.txt");
		GraphGen graphGen = GraphGen.getInstance();
		graphGen.generateGraph(data);
		SNGraph graph = graphGen.getGraph();
		for(int i=0; i<topK; i++) {
			Spreador spreador = Spreador.getInstance(graph);
			TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
			for(int j=0; j<SCALE; j++ ) {
				String vertex = graph.getVertexByIndex(i);
				treeMap.put(vertex, spreador.spread(i));
			}
			Iterator iterator = treeMap.entrySet().iterator();
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)iterator.next();
			nodes.add(entry.getKey()+"-"+entry.getValue());
			graph.removeNode(i, SCALE);
			System.out.println("第"+(i+1)+"个节点找到: " + entry.getKey() + ", 影响值是: " + entry.getValue());
		}
		FileUtil.writeFile("/data/result.dat", nodes, WriteMode.FileWriter);
	}
}
