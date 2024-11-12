package socialnetwork;

import java.util.*;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int source;

    public BreadthFirstSearch(Graph graph, int source) {
        this.source = source;
        marked = new boolean[graph.getV()];
        edgeTo = new int[graph.getV()];
        bfs(graph, source);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.getAdj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public List<Integer> pathTo(int v) {
        if (!marked[v]) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        for (int x = v; x != source; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }
}
