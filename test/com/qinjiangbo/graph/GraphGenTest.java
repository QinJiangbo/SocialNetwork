package com.qinjiangbo.graph;

import java.io.IOException;

/**
 * Created by Richard on 5/22/16.
 */
public class GraphGenTest {

    public static void main(String[] args) throws IOException {
        GraphGen graphGen = GraphGen.getInstance();
        graphGen.saveNodes();
    }
}
