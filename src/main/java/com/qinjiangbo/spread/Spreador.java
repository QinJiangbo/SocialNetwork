package com.qinjiangbo.spread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.qinjiangbo.graph.SNGraph;
import com.qinjiangbo.util.RandomUtil;

public class Spreador {
	
	private static SNGraph graph;
	private static Spreador spreador = null;
	private List<Integer> activatedNodes = new ArrayList<Integer>();
	
	private Spreador() {
		
	}
	
	/**
	 * 得到单例的Spreador对象
	 * @param snGraph 需要遍历的社会网络关系图
	 * @return Spreador对象
	 */
	public static Spreador getInstance(SNGraph snGraph) {
		if(spreador == null) {
			synchronized (Spreador.class) {
				if(spreador == null) {
					spreador = new Spreador();
					graph = snGraph;
				}
			}
		}
		return spreador;
	}
	
	/**
	 * 传播
	 * @param index 传播的起始点
	 */
	public int spread(int index) {
		//清空掉里面的元素保证每次的激活节点数量真实
		activatedNodes.clear();
		boolean[] activated=new boolean[graph.getVertexNum()];
        for (int i=0; i<graph.getVertexNum(); i++) {
        	activated[i] = false;
        }
        /**
         * 初始化这个节点为已激活
         */
        activated[index] = true;
        /**
         * 从这个点开始传播
         */
        return broadFirstSearch(activated, index);
	}
	
	//私有函数，广度优先遍历
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private int broadFirstSearch(boolean[] activated, int index) {
        int u, w;
        LinkedList queue = new LinkedList();

        //访问节点
        activated[index] = true;
        //加入到激活节点链表
        activatedNodes.add(index);
        //节点入队列 
        queue.addLast(index);
        while (!queue.isEmpty()) {
        	//当前节点
            u = ((Integer)queue.removeFirst()).intValue();
            //当前节点的第一个邻接节点
            w = graph.getFirstNeighbor(u);
            while(w != -1) {
            	float probability = graph.getWeight(u, w);
                if(!activated[w] && RandomUtil.vote(probability)) {
                     //标记已被激活
                     activated[w] = true;
                     //加入到激活节点链表
                     activatedNodes.add(w);
                     //节点入队列
                     queue.addLast(w);
                }
                //寻找下一个邻接节点
                w = graph.getNextNeighbor(u, w);
            }
        }
        return activatedNodes.size();
    }
    
    /**
     * 得到当前节点的影响值
     * @return 影响值
     */
    public int getInfluence() {
    	return activatedNodes.size();
    }
}
