/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinders;

import graph.Graph;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra implements PathFinder{

    // returns path as a sequence of node-indices.
    public int[] findPath(Graph g) {
        //initialize all nodes to have max weight
        int[] weights = new int[g.getNumberOfNodes()];
        boolean[] visited = new boolean[g.getNumberOfNodes()];
        int[] prev = new int[g.getNumberOfNodes()];
        prev[0] = -1;
        weights[0] = 0;
        for (int i = 1; i < weights.length; i++) {
            weights[i] = Integer.MAX_VALUE;
        }
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Collections.sort(queue);
            int checking = queue.get(0);
            queue.remove(0);
            //obtain adjacencies
            int[][] adj = g.getNeighborsOfNode(checking);
            //visit each adjacency
            for (int i = 0; i < adj.length; i++) {
                //if it's unvisited, compare weights
                int node = adj[i][0];
                int weight = adj[i][1];
                if (!visited[node]) {
                    if (weights[checking] + weight < weights[node]) {
                        weights[node] = weights[checking] + weight;
                        prev[node] = checking;
                    }
                    //visit in future
                    queue.add(node);
                }
            }
            //never come back to the node
            visited[checking] = true;
        }
        
        int[] path = {0};
        //path was found
        if (weights[1] != Integer.MAX_VALUE) {
            ArrayList<Integer> backtrack = new ArrayList<Integer>();
            int loc = 1;
            while (prev[loc] != -1) {
                backtrack.add(loc);
                loc = prev[loc];
            }
            backtrack.add(0);
            Collections.reverse(backtrack);
            path = new int[backtrack.size()];
            for (int i = 0; i < path.length; i++) {
                path[i] = backtrack.get(i);
            }
        }
        return path;
    }
}
