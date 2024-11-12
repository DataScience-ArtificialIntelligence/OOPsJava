package socialnetwork;

import java.io.*;
import java.util.*;

public class SocialNetworkAnalysis {

    public static void main(String[] args) {
        String fileName = "modified_facebook_disjoint_communities.txt";
        Graph graph = readGraphFromFile(fileName);
        
        System.out.println("Graph:");
        graph.printGraph();
        
        System.out.println("\nTotal number of communities:");
        int componentCount = findConnectedComponents(graph);
        System.out.println(componentCount);
        
        int source = 0; 
        int target = 4; 
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, source);
        
    }

    private static Graph readGraphFromFile(String fileName) {
        Graph graph = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int V = Integer.parseInt(br.readLine());
            graph = new Graph(V);
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                int v = Integer.parseInt(tokens[0]);
                int w = Integer.parseInt(tokens[1]);
                graph.addEdge(v, w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    private static int findConnectedComponents(Graph graph) {
        boolean[] visited = new boolean[graph.getV()];
        int count = 0;
        
        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(graph, v, visited);
                count++;
            }
        }
        return count;
    }

    private static void dfs(Graph graph, int v, boolean[] visited) {
        visited[v] = true;
        for (int w : graph.getAdj(v)) {
            if (!visited[w]) {
                dfs(graph, w, visited);
            }
        }
    }
}