package com.qinjiangbo.graph;

import java.io.IOException;
import java.util.List;

import com.qinjiangbo.spread.Spreador;
import com.qinjiangbo.util.FileUtil;

public class SpreadTest {
	
	public static void main(String[] args) {
		GraphGen snGraphGen = GraphGen.getInstance();
		try {
//			snGraphGen.saveNodes();
			List<String> data = FileUtil.readFile("/data/graph.dat");
			snGraphGen.generateGraph(data);
			SNGraph graph = snGraphGen.getGraph();
			Spreador spreador = Spreador.getInstance(graph);
			for(int i=0; i < 10000; i++) {
				spreador.spread(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
