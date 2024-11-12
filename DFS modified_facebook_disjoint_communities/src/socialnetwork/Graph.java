package socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int V;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + ": ");
            for (Integer vertex : adj[i]) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    public List<Integer> getAdj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }
}
