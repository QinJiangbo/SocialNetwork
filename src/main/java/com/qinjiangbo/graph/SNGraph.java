package com.qinjiangbo.graph;

import java.util.ArrayList;

public class SNGraph {
	
	private ArrayList<String> vertexList; // list of vertex
	private float[][] matrix; // matrix used to store edges
	private int edgeNum; // number of edge
	
	public SNGraph(int n) {
		// initialize list of vertex, matrix and number of edge
		vertexList = new ArrayList<String>(n);
		matrix = new float[n][n];
		edgeNum = 0;
	}
	
	/**
	 * 得到节点的数量
	 * @return
	 */
	public int getVertexNum() {
		return vertexList.size();
	}
	
	/**
	 * 得到边的数量
	 * @return
	 */
	public int getEdgeNum() {
		return edgeNum;
	}
	
	/**
	 * 根据索引查找节点
	 * @param i 节点的索引[0---(SCALE-1)]
	 * @return
	 */
	public String getVertexByIndex(int i) {
		return vertexList.get(i);
	}
	
	/**
	 * 得到两个节点间的边的权值从V1到V2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public float getWeight(int v1, int v2) {
		return matrix[v1][v2];
	}
	
	/**
	 * 插入节点
	 * @param vertex
	 */
	public void insertVertex(String vertex) {
		vertexList.add(vertexList.size(), vertex);
	}
	
	/**
	 * 插入边
	 * @param v1
	 * @param v2
	 * @param weight
	 */
	public void insertEdge(int v1, int v2, float weight) {
		if(matrix[v1][v2] == 0.0) {
			matrix[v1][v2] = weight;
			edgeNum++;
		}
	}
	
	/**
	 * 移除边
	 * @param v1
	 * @param v2
	 */
	public void deleteEdge(int v1, int v2) {
		matrix[v1][v2] = 0;
		edgeNum--;
	}
	
	/**
	 * 得到当前节点的第一个邻接节点
	 * @param index
	 * @return
	 */
	public int getFirstNeighbor(int index) {
		for(int i = 0; i < vertexList.size(); i++) {
			if(matrix[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 得到当前节点v1的下一个邻接节点
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for(int i = v2 + 1; i < vertexList.size(); i++) {
			if(matrix[v1][i] > 0) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 将该节点从图中去除
	 * @param i
	 * @param SCALE
	 */
	public void removeNode(int i, int SCALE) {
		for(int j=0; j<SCALE; j++) {
			matrix[i][j] = 0;
			matrix[j][i] = 0;
		}
	}
    
	public float[][] getMatrix() {
		return matrix;
	}
	
    /**
	 * 显示图结构
	 */
	public void showGraph(int SCALE) {
		for(int i = 0; i < SCALE; i++) {
			for(int j = 0; j < SCALE; j++) {
				if(matrix[i][j] != 0.0)
					System.out.print("[" + matrix[i][j] + "] ");
				else if((j+1)<SCALE && matrix[i][j+1] > 0.0)
					System.out.print(matrix[i][j] + " ");
				else
					System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
	}
}
