import java.io.*;
import java.util.*;

public class MSTWithPrims {

    private Map<String, List<Edge>> graph = new HashMap<>();
    private Map<String, Integer> minEdge = new HashMap<>();
    private Map<String, String> pathPredecessors = new HashMap<>();
    private Set<String> visited = new HashSet<>();
    private List<Edge> mstEdges = new ArrayList<>();  // List to store the edges of the MST

    static class Edge {
        String source;
        String destination;
        int weight;

        Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Method to add an edge to the graph
    private void addEdge(String origin, String destination, int weight) {
        graph.putIfAbsent(origin, new ArrayList<>());
        graph.get(origin).add(new Edge(origin, destination, weight));

        graph.putIfAbsent(destination, new ArrayList<>());
        graph.get(destination).add(new Edge(destination, origin, weight));
    }

    // Prim's algorithm to compute Minimum Spanning Tree (MST) from a specified start node
    private void computeMST(String start) {
        // Initialize all nodes with infinity weight and no predecessor
        for (String node : graph.keySet()) {
            minEdge.put(node, Integer.MAX_VALUE);
            pathPredecessors.put(node, null);
        }

        // Start from the specified node
        minEdge.put(start, 0);
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(minEdge::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();
            if (!visited.contains(current)) {
                visited.add(current);

                // If the current node has a predecessor, add the edge to the MST
                String predecessor = pathPredecessors.get(current);
                if (predecessor != null) {
                    int weight = minEdge.get(current);
                    mstEdges.add(new Edge(predecessor, current, weight));
                }

                // Explore the neighbors of the current node
                for (Edge edge : graph.getOrDefault(current, new ArrayList<>())) {
                    String neighbor = edge.destination;
                    if (!visited.contains(neighbor) && edge.weight < minEdge.get(neighbor)) {
                        // If the edge weight is less than the current min, update the MST
                        minEdge.put(neighbor, edge.weight);
                        pathPredecessors.put(neighbor, current);
                        pq.add(neighbor);
                    }
                }
            }
        }
    }

    // Write the MST paths and total cost to the output file
    private void writeMSTToFile(String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, false))) { // false means overwrite if file exists
            int totalCost = 0;

            // Write each edge in the MST to the file
            writer.write("Edges in the MST:\n");
            for (Edge edge : mstEdges) {
                writer.write(edge.source + " - " + edge.destination + " : " + edge.weight + "\n");
                totalCost += edge.weight;
            }

            // Write the total cost of the MST
            writer.write("Total cost of MST: " + totalCost + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file.");
            e.printStackTrace();
        }
    }

    // Load the graph from a file
    public void loadGraphFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();  // Skip the first line (which can be the header)
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String origin = parts[0];
                String destination = parts[1];
                int distance = Integer.parseInt(parts[2]);
                addEdge(origin, destination, distance);
            }
        }
    }

    public static void main(String[] args) {
        MSTWithPrims mst = new MSTWithPrims();
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask the user for the input file path
            System.out.print("Enter the file path for the input graph (e.g., data.txt): ");
            String inputFilePath = scanner.nextLine();  // Read the file path from the user
            mst.loadGraphFromFile(inputFilePath);

            // Ask the user for the starting node for Prim's Algorithm
            System.out.print("Enter the starting vertex for Prim's MST: ");
            String start = scanner.nextLine();

            // Check if the start node exists in the graph
            if (!mst.graph.containsKey(start)) {
                System.out.println("The specified start vertex does not exist in the graph.");
                return;
            }

            mst.computeMST(start);

            // Write the MST result (edges and total cost) to a fixed output file
            String outputFilePath = "mst_output.txt";  // Fixed output file path
            mst.writeMSTToFile(outputFilePath);

            System.out.println("The MST edges and total cost have been written to the output file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the input file.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
