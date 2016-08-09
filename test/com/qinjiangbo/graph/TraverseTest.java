package com.qinjiangbo.graph;

import com.qinjiangbo.enums.TraverseMode;
import com.qinjiangbo.spread.Traverse;

public class TraverseTest {
	
	public static void main(String[] args) {
		int n = 8;
		String[] labels = {"1", "2", "3", "4", "5", "6", "7", "8"}; //结点的标识
		SNGraph graph = new SNGraph(n);
		for(String label : labels) {
			graph.insertVertex(label); //插入结点
		}
		
		 //插入九条边[无向图]
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(4, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);
        
        float[][] edges = graph.getMatrix();
        for(int i=0; i < 8; i++) {
        	for(int j=0; j<8; j++) {
        		System.out.print((int)(edges[i][j])+"  ");
        	}
        	System.out.println();
        }
        
        System.out.println("深度优先搜索序列为：");
        Traverse.getInstance(graph).traverseGraph(TraverseMode.DepthFirst);
        System.out.println();
        System.out.println("广度优先搜索序列为：");
        Traverse.getInstance(graph).traverseGraph(TraverseMode.BroadFirst);
        
	}
}
