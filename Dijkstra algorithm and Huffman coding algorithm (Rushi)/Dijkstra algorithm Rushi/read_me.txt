Group members are:-Komeri rushi  and J.P.K.Sree Harsha.


Shortest Path Finder using Dijkstra's Algorithm:-
This project implements Dijkstra's algorithm in Java to find the shortest path between two cities. The application reads city-to-city distance data from an input file, builds a graph, and calculates the shortest path from a specified source to a destination city. The computed path and total distance are written to an output file for easy access.


Project Structure:-
DijkstraAlgorithm.java: Main file containing the implementation of Dijkstra's algorithm.
sample.txt: Input data file containing city pairs and distances, forming the basis of the graph.
output.txt: Output file where the shortest path and distance are written.


Dataset:-
The dataset (sample.txt) contains the following columns:
FromCity: The origin city of the route.
ToCity: The destination city of the route.
Distance: The distance (in km) between the two cities.


How to Use:-
Build the Graph: The program reads the dataset file to build a graph where each city is a node, and each route (city pair) is an edge with a weight representing the distance.
Run the Program: Specify a source and destination city. The program computes the shortest path and saves it in output.txt.
Output: The output file contains the ordered list of cities forming the shortest path and the total distance of this path.


Sample Usage
Place the sample.txt file in the same directory.
Run the program and follow the prompts to enter the source and destination cities.
Check output.txt for the results.


Advantages of Dijkstra's Algorithm:-
Efficiency in Finding Shortest Paths: Dijkstra's algorithm is efficient for finding the shortest path in a weighted, non-negative graph, making it ideal for real-world routing applications such as navigation and logistics.
Wide Applicability: The algorithm can be applied to any network of nodes and edges, including road networks, data networks, and other scenarios involving distances or costs.
Optimal Solution: Dijkstra's algorithm guarantees an optimal solution for shortest-path problems, ensuring the most efficient route in terms of distance or cost.
Customizable for Different Scenarios: With simple modifications, Dijkstra’s algorithm can be adapted to prioritize different metrics (like time, cost, or distance).

Advantages of the Project Setup
File-based Input and Output: The project reads from a file, allowing users to input large datasets with ease, and outputs to a file, making the results accessible for later review.
Scalability: The project structure can be scaled to handle larger datasets or expanded to accommodate additional features, like adding new cities or dynamic route updates.
Path Tracking: The program tracks and stores the shortest path between two cities, making it easy to visualize the route as well as calculate the distance.
Error Handling: The project includes error handling for input validation (e.g., invalid file format or data), ensuring robustness and resilience in handling real-world data.
