package com.qinjiangbo.spread;

import java.util.LinkedList;

import com.qinjiangbo.enums.TraverseMode;
import com.qinjiangbo.graph.SNGraph;

public class Traverse {
	
	private static SNGraph graph;
	private static Traverse traverse = null;
	
	public static Traverse getInstance(SNGraph snGraph) {
		if(traverse == null) {
			synchronized (Traverse.class) {
				if(traverse == null) {
					traverse = new Traverse();
					graph = snGraph;
				}
			}
		}
		return traverse;
	}
	
	/**
	 * 遍历图结构
	 * @param mode 遍历模式，深度优先/广度优先
	 */
	public void traverseGraph(TraverseMode mode) {
		switch (mode) {
		case DepthFirst:
			depthFirstSearch();
			break;
		case BroadFirst:
			broadFirstSearch();
			break;
		}
	}
	
	//私有函数，深度优先遍历
    private void depthFirstSearch(boolean[] isVisited, int i) {
        //首先访问该结点，在控制台打印出来
        System.out.print(graph.getVertexByIndex(i) + "  ");
        //置该结点为已访问
        isVisited[i] = true;

        int w = graph.getFirstNeighbor(i);//
        while (w != -1) {
            if (!isVisited[w]) {
                depthFirstSearch(isVisited, w);
            }
            w = graph.getNextNeighbor(i, w);
        }
    }

    //对外公开函数，深度优先遍历，与其同名私有函数属于方法重载
    private void depthFirstSearch() {
        boolean[] isVisited=new boolean[graph.getVertexNum()];
        //记录结点是否已经被访问的数组
        for (int i=0; i<graph.getVertexNum(); i++) {
            isVisited[i]=false;//把所有节点设置为未访问
        }
        for(int i=0; i<graph.getVertexNum(); i++) {
            //因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点的。
            if (!isVisited[i]) {
                depthFirstSearch(isVisited, i);
            }
        }
    }

    //私有函数，广度优先遍历
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void broadFirstSearch(boolean[] isVisited, int i) {
        int u,w;
        LinkedList queue = new LinkedList();

        //访问结点i
        System.out.print(graph.getVertexByIndex(i) + "  ");
        isVisited[i] = true;
        //结点入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = ((Integer)queue.removeFirst()).intValue();
            w = graph.getFirstNeighbor(u);
            while(w != -1) {
                if(!isVisited[w]) {
                        //访问该结点
                        System.out.print(graph.getVertexByIndex(w) + "  ");
                        //标记已被访问
                        isVisited[w] = true;
                        //入队列
                        queue.addLast(w);
                }
                //寻找下一个邻接结点
                w = graph.getNextNeighbor(u, w);
            }
        }
    }

    //对外公开函数，广度优先遍历
    private void broadFirstSearch() {
        boolean[] isVisited=new boolean[graph.getVertexNum()];
        for (int i=0; i<graph.getVertexNum(); i++) {
            isVisited[i] = false;
        }
        for(int i=0; i<graph.getVertexNum(); i++) {
            if(!isVisited[i]) {
                broadFirstSearch(isVisited, i);
            }
        }
    }
}
