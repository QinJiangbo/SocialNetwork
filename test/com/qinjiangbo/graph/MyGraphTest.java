package com.qinjiangbo.graph;

import com.qinjiangbo.enums.TraverseMode;
import com.qinjiangbo.spread.Traverse;

public class MyGraphTest {
	
	public static void main(String[] args) {
		int n = 7;
		String[] labels = {"a", "b", "c", "d", "e", "f", "g"};
		SNGraph graph = new SNGraph(n);
		//插入所有的点
		for (String label : labels) {
			graph.insertVertex(label);
		}
		
		//插入所有的边
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 0, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(2, 0, 1);
		graph.insertEdge(2, 3, 1);
		graph.insertEdge(2, 4, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(3, 2, 1);
		graph.insertEdge(3, 6, 1);
		graph.insertEdge(4, 1, 1);
		graph.insertEdge(4, 2, 1);
		graph.insertEdge(4, 5, 1);
		graph.insertEdge(5, 2, 1);
		graph.insertEdge(5, 4, 1);
		graph.insertEdge(6, 3, 1);
		
		//广度优先搜索
		Traverse.getInstance(graph).traverseGraph(TraverseMode.BroadFirst);
		
		System.out.println("\n"+"===================");
		
		//深度优先搜索
		Traverse.getInstance(graph).traverseGraph(TraverseMode.DepthFirst);
		
	}
}
