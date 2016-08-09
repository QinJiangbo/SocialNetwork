package com.qinjiangbo.graph;

public class GraphTest {

	public static void main(String[] args) {
		int vertex = 4;
		String labels[] = {"V1", "V2", "V3", "V4"};
		
		SNGraph graph = new SNGraph(vertex);
		
		for(String label : labels) {
			graph.insertVertex(label);
		}
		
		// insert edges
		graph.insertEdge(0, 1, 2); // V1, V2
		graph.insertEdge(1, 3, 10); // V2, V4
		graph.insertEdge(2, 1, 6); // V3, V2
		graph.insertEdge(3, 2, 4); // V4, V3
		
		System.out.println("number of vertex: " + graph.getVertexNum());
        System.out.println("number of edge: " + graph.getEdgeNum());
        float[][] edges = graph.getMatrix();
        for(int i=0; i < 4; i++) {
        	for(int j=0; j<4; j++) {
        		System.out.print(edges[i][j]+"  ");
        	}
        	System.out.println();
        }
        
        System.out.println("Weight between V3 and V2: " + graph.getWeight(2, 1));

        graph.deleteEdge(0, 1);//delete <V1,V2> edge
        System.out.println("deleted <V1,V2> ...");
        System.out.println("number of vertex: " + graph.getVertexNum());
        System.out.println("number of edge: " + graph.getEdgeNum());
        edges = graph.getMatrix();
        for(int i=0; i < 4; i++) {
        	for(int j=0; j<4; j++) {
        		System.out.print(edges[i][j]+"  ");
        	}
        	System.out.println();
        }
        
        System.out.println("Weight between V3 and V2: " + graph.getWeight(2, 1));
	}

}
