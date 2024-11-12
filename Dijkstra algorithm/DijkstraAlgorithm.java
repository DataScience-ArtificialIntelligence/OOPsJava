import java.io.*;
import java.util.*;

public class DijkstraAlgorithm {

    // Node class to represent each city and distance
    static class Node implements Comparable<Node> {
        String city;
        double distance;

        public Node(String city, double distance) {
            this.city = city;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        } 
    }

    // Graph representation
    static Map<String, List<Node>> graph = new HashMap<>();

    // Build graph from the input file
    public static void buildGraph(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+"); // Split by whitespace
                if (parts.length == 3) {
                    try {
                        String city1 = parts[0];
                        String city2 = parts[1];
                        double distance = Double.parseDouble(parts[2]);

                        // Add edge from city1 to city2
                        graph.putIfAbsent(city1, new ArrayList<>());
                        graph.get(city1).add(new Node(city2, distance));

                        // Add edge from city2 to city1
                        graph.putIfAbsent(city2, new ArrayList<>());
                        graph.get(city2).add(new Node(city1, distance));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping line with invalid distance value: " + line);
                    }
                } else {
                    System.out.println("Skipping line with invalid format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Dijkstra's algorithm to find the shortest path
    public static Map<String, Double> dijkstra(String start, Map<String, String> previousNodes) {
        Map<String, Double> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for (String city : graph.keySet()) {
            distances.put(city, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(new Node(start, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node neighbor : graph.getOrDefault(current.city, new ArrayList<>())) {
                double newDist = distances.get(current.city) + neighbor.distance;
                if (newDist < distances.get(neighbor.city)) {
                    distances.put(neighbor.city, newDist);
                    pq.add(new Node(neighbor.city, newDist));
                    previousNodes.put(neighbor.city, current.city); // Track path
                }
            }
        }
        return distances;
    }

    // Helper method to get the shortest path from source to destination
    public static List<String> getPath(String destination, Map<String, String> previousNodes) {
        List<String> path = new LinkedList<>();
        for (String at = destination; at != null; at = previousNodes.get(at)) {
            path.add(0, at); // Add to start of list to reverse path
        }
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the file and build the graph
        String inputFile = "sample.txt"; // Replace with your file path if needed
        buildGraph(inputFile);

        // User input for source and destination
        System.out.print("Enter source city: ");
        String source = scanner.nextLine().trim();
        System.out.print("Enter destination city: ");
        String destination = scanner.nextLine().trim();

        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            System.out.println("One of the cities is not in the graph.");
            return;
        }

        // Compute shortest paths from the source city
        Map<String, String> previousNodes = new HashMap<>();
        Map<String, Double> distances = dijkstra(source, previousNodes);

        // Retrieve and print the path
        List<String> path = getPath(destination, previousNodes);
        double distance = distances.get(destination);

        if (distance == Double.MAX_VALUE) {
            System.out.println("No path exists between " + source + " and " + destination);
        } else {
            // Write output to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write("Shortest path from " + source + " to " + destination + ":\n");
                writer.write(String.join(" -> ", path) + "\n");
                writer.write("Total distance: " + distance + " km\n");
                System.out.println("Output written to output.txt");
            } catch (IOException e) {
                System.err.println("Error writing to the output file: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
