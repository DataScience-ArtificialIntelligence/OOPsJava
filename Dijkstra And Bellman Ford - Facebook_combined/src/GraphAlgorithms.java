import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class GraphAlgorithms {
    static class Edge {
        int source, destination, weight;
        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static Map<Integer, Integer> distances = new HashMap<>();
    static Map<Integer, Integer> previous = new HashMap<>();

  
    public static void loadGraph(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                // Ensure there are exactly two parts (source and destination)
                if (parts.length != 2) {
                    System.out.println("Skipping line: " + line);
                    continue;
                }

                int src = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                int weight = 1; // Assign a default weight

                graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, weight));
                graph.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Edge(dest, src, weight)); // For undirected graph
            }
        }
    }

    // Dijkstra's algorithm
    public static void dijkstra(int startNode) {
        distances.clear();
        previous.clear();

        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, -1);
        }
        distances.put(startNode, 0);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        pq.add(new Edge(startNode, startNode, 0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentNode = currentEdge.destination;

            for (Edge edge : graph.getOrDefault(currentNode, new ArrayList<>())) {
                int newDist = distances.get(currentNode) + edge.weight;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, currentNode);
                    pq.add(new Edge(currentNode, edge.destination, newDist));
                }
            }
        }
    }

    // Bellman-Ford algorithm
    public static boolean bellmanFord(int startNode) {
        distances.clear();
        previous.clear();

        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, -1);
        }
        distances.put(startNode, 0);

        int nodeCount = graph.size();

        // Relax edges up to (nodeCount - 1) times
        for (int i = 0; i < nodeCount - 1; i++) {
            for (int u : graph.keySet()) {
                for (Edge edge : graph.get(u)) {
                    if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + edge.weight < distances.get(edge.destination)) {
                        distances.put(edge.destination, distances.get(u) + edge.weight);
                        previous.put(edge.destination, u);
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u : graph.keySet()) {
            for (Edge edge : graph.get(u)) {
                if (distances.get(u) != Integer.MAX_VALUE && distances.get(u) + edge.weight < distances.get(edge.destination)) {
                    System.out.println("Graph contains a negative weight cycle");
                    return false;
                }
            }
        }
        return true;
    }

    //
    public static void addEdge(int source, int destination, int weight) {
        graph.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(source, destination, weight));
        graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(new Edge(destination, source, weight));
    }

    // Remove an edge from the graph
    public static void removeEdge(int source, int destination) {
        List<Edge> sourceEdges = graph.get(source);
        if (sourceEdges != null) {
            sourceEdges.removeIf(edge -> edge.destination == destination);
        }
        List<Edge> destinationEdges = graph.get(destination);
        if (destinationEdges != null) {
            destinationEdges.removeIf(edge -> edge.destination == source);
        }
    }

    // Update the weight of an existing edge
    public static void updateEdgeWeight(int source, int destination, int newWeight) {
        List<Edge> sourceEdges = graph.get(source);
        if (sourceEdges != null) {
            for (Edge edge : sourceEdges) {
                if (edge.destination == destination) {
                    edge.weight = newWeight;
                    break;
                }
            }
        }
        List<Edge> destinationEdges = graph.get(destination);
        if (destinationEdges != null) {
            for (Edge edge : destinationEdges) {
                if (edge.destination == source) {
                    edge.weight = newWeight;
                    break;
                }
            }
        }
    }
    // Export the graph to an edge list file
// Export the graph to an edge list file
public static void exportGraph(String outputFilename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
        for (int node : graph.keySet()) {
            for (Edge edge : graph.get(node)) {
                // Write each edge as "source destination weight"
                writer.write(edge.source + " " + edge.destination + " " + edge.weight);
                writer.newLine();
            }
        }
    }
}



    public static void main(String[] args) throws IOException {
        // Load the graph from a file
        String filePath = "C:\\\\Users\\\\agraw\\\\Downloads\\\\facebook_combined.txt.gz";
        loadGraph(filePath);

        int startNode = 0; // Choose a start node (e.g., 0)
        String exportPath = "C:\\Users\\agraw\\Downloads\\facebook_graph.edgelist";
        exportGraph(exportPath);
        System.out.println("Graph exported to: " + exportPath);

        // Run Dijkstra
        System.out.println("Running Dijkstra's algorithm...");
        dijkstra(startNode);
        System.out.println("Dijkstra distances from node " + startNode + ": " + distances);

        // Run Bellman-Ford
        System.out.println("\nRunning Bellman-Ford algorithm...");
        if (bellmanFord(startNode)) {
            System.out.println("Bellman-Ford distances from node " + startNode + ": " + distances);
        } else {
            System.out.println("Bellman-Ford detected a negative weight cycle.");
        }

        // Example of real-time updates
        addEdge(1, 5, 10);
        removeEdge(2, 4);
        updateEdgeWeight(3, 6, 2);

        System.out.println("\nAfter real-time updates:");
        dijkstra(startNode);
        System.out.println("Updated Dijkstra distances from node " + startNode + ": " + distances);

        if (bellmanFord(startNode)) {
            System.out.println("Updated Bellman-Ford distances from node " + startNode + ": " + distances);
        } else {
            System.out.println("Bellman-Ford detected a negative weight cycle.");
        }
    }
    


}