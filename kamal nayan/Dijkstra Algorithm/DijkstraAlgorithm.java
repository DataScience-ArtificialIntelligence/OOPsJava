import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.Scanner;

public class DijkstraAlgorithm {

    public static class Edge {
        int dest;
        double weight;

        public Edge(int dest, double weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Dijkstra's algorithm with path tracking
    public static Map<Integer, Double> dijkstra(Map<Integer, List<Edge>> graph, int start, Map<Integer, List<Integer>> paths) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(arr -> arr[1]));
        pq.add(new int[]{start, 0});

        Map<Integer, Double> costs = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        costs.put(start, 0.0);

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            double cost = curr[1];

            if (cost > costs.getOrDefault(node, Double.MAX_VALUE)) continue;

            if (graph.containsKey(node)) {
                for (Edge edge : graph.get(node)) {
                    double newCost = cost + edge.weight;
                    if (newCost < costs.getOrDefault(edge.dest, Double.MAX_VALUE)) {
                        costs.put(edge.dest, newCost);
                        predecessors.put(edge.dest, node);
                        pq.add(new int[]{edge.dest, (int) newCost});
                    }
                }
            }
        }
        constructPaths(predecessors, start, paths);
        return costs;
    }

    // Construct paths for each reachable node
    public static void constructPaths(Map<Integer, Integer> predecessors, int start, Map<Integer, List<Integer>> paths) {
        for (Integer node : predecessors.keySet()) {
            List<Integer> path = new ArrayList<>();
            int current = node;
            while (current != start) {
                path.add(current);
                current = predecessors.get(current);
            }
            path.add(start);
            Collections.reverse(path);
            paths.put(node, path);
        }
    }

    // Read graph from CSV file using JFileChooser
    public static Map<Integer, List<Edge>> readGraphFromCSV() throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select the input CSV file");

        int inputChoice = fileChooser.showOpenDialog(null);
        if (inputChoice != JFileChooser.APPROVE_OPTION) {
            throw new Exception("No file selected.");
        }
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        Map<Integer, List<Edge>> graph = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                int src = Integer.parseInt(tokenizer.nextToken());
                int dest = Integer.parseInt(tokenizer.nextToken());
                double weight = Double.parseDouble(tokenizer.nextToken());

                graph.computeIfAbsent(src, ArrayList::new).add(new Edge(dest, weight));
                graph.computeIfAbsent(dest, ArrayList::new).add(new Edge(src, weight));
            }
        }
        System.out.println("Graph loaded successfully from " + filePath);
        return graph;
    }

    // Save costs and paths to CSV with JFileChooser for save location
    public static void saveCostsToCSV(Map<Integer, Double> costs, Map<Integer, List<Integer>> paths, Set<Integer> allNodes) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save costs to CSV");

        int outputChoice = fileChooser.showSaveDialog(null);
        if (outputChoice != JFileChooser.APPROVE_OPTION) {
            throw new Exception("Save operation cancelled.");
        }
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Node,Cost,Path");
            for (Integer node : allNodes) {
                double cost = costs.getOrDefault(node, Double.POSITIVE_INFINITY);
                String costStr = (cost == Double.POSITIVE_INFINITY) ? "Infinity" : String.valueOf(cost);
                String pathStr = paths.containsKey(node) ? paths.get(node).toString() : "Unreachable";
                writer.println(node + "," + costStr + "," + pathStr);
            }
        }
        System.out.println("Costs and paths saved to " + filePath);
    }

    // Visualize reachable nodes with fixed costs using a scatter plot
    public static void visualizeShortestPaths(Map<Integer, Double> costs) {
        XYSeries series = new XYSeries("Shortest Path Costs");
        for (Map.Entry<Integer, Double> entry : costs.entrySet()) {
            // Only add nodes with a finite cost
            if (!Double.isInfinite(entry.getValue())) {
                series.add(entry.getKey(), entry.getValue());
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createScatterPlot(
                "Shortest Path Costs for Reachable Nodes",
                "Node",
                "Cost",
                dataset
        );

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }

    // Method to allow user to manually enter a source node
    public static int selectSourceNode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the source node: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid source node number.");
            scanner.next(); // consume invalid input
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        try {
            Map<Integer, List<Edge>> graph = readGraphFromCSV();

            int startNode = selectSourceNode();

            if (!graph.containsKey(startNode)) {
                System.out.println("The source node " + startNode + " is not in the graph. Please check the input.");
                return;
            }

            System.out.println("\nThe network was generated using email data from a large European research institution.");
            System.out.println("We have anonymized information about all incoming and outgoing email between members of the research institution.");
            System.out.println("The e-mails only represent communication between institution members (the core),");
            System.out.println("and the dataset does not contain incoming messages from or outgoing messages to the rest of the world.");
            System.out.println("A directed edge (u, v, t) means that person u sent an e-mail to person v at time t.");
            System.out.println("A separate edge is created for each recipient of the e-mail.");
            System.out.println("We also have four sub-networks corresponding to the communication between members of four different departments at the institution.");
            System.out.println("Node IDs in the sub-networks do not correspond to the same node ID in the entire network.");

            Map<Integer, List<Integer>> paths = new HashMap<>();
            Map<Integer, Double> costs = dijkstra(graph, startNode, paths);

            Set<Integer> allNodes = new HashSet<>(graph.keySet());
            for (List<Edge> edges : graph.values()) {
                for (Edge edge : edges) {
                    allNodes.add(edge.dest);
                }
            }

            System.out.println("\nShortest Costs and Paths from Node " + startNode + ":");
            for (Integer node : allNodes) {
                double cost = costs.getOrDefault(node, Double.POSITIVE_INFINITY);
                String pathStr = paths.containsKey(node) ? paths.get(node).toString() : "Unreachable";
                System.out.println("Node " + node + " : Cost = " + (cost == Double.POSITIVE_INFINITY ? "Infinity" : cost) + ", Path = " + pathStr);
            }

            saveCostsToCSV(costs, paths, allNodes);
            visualizeShortestPaths(costs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
