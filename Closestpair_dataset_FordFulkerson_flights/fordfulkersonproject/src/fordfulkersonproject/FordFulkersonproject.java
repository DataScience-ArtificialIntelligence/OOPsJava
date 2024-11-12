package fordfulkersonproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map; 

public class FordFulkersonproject {
    private static final int MAX_NODES = 50;
    private static int[][] capacity = new int[MAX_NODES][MAX_NODES];
    private static int[] parent = new int[MAX_NODES]; 
    private static boolean[] visited;
    private static Map<String, Integer> airportMap = new HashMap<>();
    private static Map<Integer, String> reverseAirportMap = new HashMap<>();

    // Mapping of airport codes to full names
    @SuppressWarnings("serial")
    private static Map<String, String> airportFullNames = new HashMap<>() {{
        put("LAX", "Los Angeles International Airport");
        put("SEA", "Seattle-Tacoma International Airport");
        put("ORD", "Chicago O'Hare International Airport");
        put("SFO", "San Francisco International Airport");
        put("PHX", "Phoenix Sky Harbor International Airport");
        put("DEN", "Denver International Airport");
        put("ATL", "Hartsfield-Jackson Atlanta International Airport");
        put("BOS", "Logan International Airport");
        put("IAD", "Washington Dulles International Airport");
    }};

    public static void main(String[] args) {
        // Create the frame and set up GUI components
        JFrame frame = new JFrame("Ford-Fulkerson Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a panel for the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a text area for displaying the output
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(230, 230, 250)); // Light background for text area
        outputArea.setForeground(new Color(0, 0, 0)); // Black text color
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Change the font
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a button for file selection with color customization
        JButton loadButton = new JButton("Load Flight Data");
        loadButton.setBackground(new Color(0, 123, 255)); // Blue background
        loadButton.setForeground(Color.WHITE); // White text color
        loadButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold text
        loadButton.setFocusPainted(false); // Remove focus outline
        panel.add(loadButton, BorderLayout.NORTH);

        // Define the file chooser
        JFileChooser fileChooser = new JFileChooser();

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open file chooser to select file
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();
                    outputArea.setText(""); // Clear previous output
                    try {
                        loadFlightData(filename, outputArea);
                    } catch (IOException ex) {
                        outputArea.append("Error loading flight data: " + ex.getMessage() + "\n");
                    }

                    // Show a notification that the file is selected
                    JOptionPane.showMessageDialog(frame, "File selected: " + filename, "File Selection", JOptionPane.INFORMATION_MESSAGE);

                    // Remove background color from the panel
                    panel.setBackground(null); // Remove the gradient background
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void loadFlightData(String filename, JTextArea outputArea) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int nodeIndex = 5; // Start node index from 2 as 0 and 1 are SOURCE and SINK

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length < 5) {
                    outputArea.append("Skipping line due to parse error: " + line + "\n");
                    continue;
                }

                String source = parts[0];
                String destination = parts[1];
                int capacityValue;

                try {
                    capacityValue = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    outputArea.append("Skipping line due to capacity parse error: " + line + "\n");
                    continue;
                }

                // Map airports to unique node indices
                if (!airportMap.containsKey(source)) {
                    airportMap.put(source, nodeIndex);
                    reverseAirportMap.put(nodeIndex++, source);
                }
                if (!airportMap.containsKey(destination)) {
                    airportMap.put(destination, nodeIndex);
                    reverseAirportMap.put(nodeIndex++, destination);
                }

                int sourceNode = airportMap.get(source);
                int destinationNode = airportMap.get(destination);

                // Set capacity for the edge
                capacity[sourceNode][destinationNode] += capacityValue;
            }

            // Connect SOURCE and SINK to the respective airports (we can modify this part based on our use case)
            airportMap.put("SOURCE", 1);
            airportMap.put("SINK", 5);

            String[] startingAirports = {"JFK", "LAX"};
            String[] endingAirports = {"SEA", "ORD"};

            int sourceIndex = airportMap.get("SOURCE");
            for (String start : startingAirports) {
                if (airportMap.containsKey(start)) {
                    int startNode = airportMap.get(start);
                    capacity[sourceIndex][startNode] = Integer.MAX_VALUE;
                }
            }

            int sinkIndex = airportMap.get("SINK");
            for (String end : endingAirports) {
                if (airportMap.containsKey(end)) {
                    int endNode = airportMap.get(end);
                    capacity[endNode][sinkIndex] = Integer.MAX_VALUE;
                }
            }

            // Run Ford-Fulkerson algorithm to find the max flow
            int maxFlow = fordFulkerson(sourceIndex, sinkIndex, outputArea);
            outputArea.append("Maximum flow (Timetable capacity): " + maxFlow + "\n");

            outputArea.append("\nAirport codes stand for the following full names:\n");
            airportFullNames.forEach((code, fullName) -> outputArea.append(code + " : " + fullName + "\n"));

        }
    }

    private static boolean bfs(int source, int sink) {
        visited = new boolean[MAX_NODES];
        visited[source] = true;
        parent[source] = -1;

        int[] queue = new int[MAX_NODES];
        int front = 0, rear = 0;
        queue[rear++] = source;

        while (front < rear) {
            int current = queue[front++];
            for (int next = 0; next < MAX_NODES; next++) {
                if (!visited[next] && capacity[current][next] > 0) {
                    queue[rear++] = next;
                    visited[next] = true;
                    parent[next] = current;

                    if (next == sink) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int fordFulkerson(int source, int sink, JTextArea outputArea) {
        int maxFlow = 0;

        while (bfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE;

            // Find the maximum flow in the path from source to sink
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            // Output the flow for this path and the airports involved
            String sourceAirport = getAirportName(source);
            String sinkAirport = getAirportName(sink);

            if (sourceAirport != null && sinkAirport != null) {
                outputArea.append("Flow from " + sourceAirport + " to " + sinkAirport + " : " + pathFlow + "\n");
            }

            // Update capacities of the edges and reverse edges
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;

                // Print the source and destination airport with their flow
                String sourceFlowAirport = getAirportName(u);
                String destFlowAirport = getAirportName(v);

                if (sourceFlowAirport != null && destFlowAirport != null) {
                    outputArea.append("Flow from " + sourceFlowAirport + " to " + destFlowAirport + " : " + pathFlow + "\n");
                }
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private static String getAirportName(int node) {
        if (reverseAirportMap.containsKey(node)) {
            return reverseAirportMap.get(node);
        }
        return null;
    }
}