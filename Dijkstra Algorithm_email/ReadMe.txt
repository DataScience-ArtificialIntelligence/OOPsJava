Group Members:
Kamal Nayan Kumar - 23BDS026
Rahul Patel - 23BDS047
Vijaypal Singh Rathore - 23BDS067

#Dijkstra Algorithm:(Rahul Patel - 23BDS047)

Shortest Path Analysis on an Institutional Email Network using Dijkstra's Algorithm

1. Introduction
This project analyzes an anonymize email communication network from a large European research institution. The data set includes internal email interactions represented as directed edges between nodes (individuals). Each edge denotes a single email instance from one member to another within the institution. Using Dijkstra’s Algorithm, we compute the shortest paths and corresponding communication costs between nodes, which helps identify the most efficient paths and communication hubs within the network.

2. Problem Statement
The task is to:
1. Construct the email communication network graph from a CSV file of email exchanges.
2. Apply Dijkstra’s Algorithm to calculate the shortest path from a chosen source node to all reachable nodes.
3. Visualize and save the shortest path costs and paths, enabling insights into communication efficiency and structure.

Data-set Overview:
- Nodes represent members of the institution.
- Directed edges represent email exchanges, with weights indicating communication cost.
- Only internal communications are present in the data-set.

3. Methodology
3.1 Graph Representation
The network is represented as a directed graph where nodes represent individuals and directed edges indicate email communications with associated weights.
3.2 Shortest Path Calculation
Dijkstra’s Algorithm is applied to calculate the shortest path from a selected source node, with the Dijkstra’s and `construct-paths` methods facilitating cost and path computation.
3.3 Output
The computed paths and costs are saved to a CSV file, and shortest path costs are visualized to highlight central nodes and communication bottlenecks.

4. Data-set Statistics
The data-set includes a core network and four departmental sub-networks, with statistics summarized below:
Network	Nodes	Temporal Edges	Static Graph Edges	Time Span
Core Network (email-Eu-core)	986	332,334	24,929	803 days
Department 1	309	61,046	3,031	803 days
Department 2	162	46,772	1,772	803 days
Department 3	89	12,216	1,506	802 days
Department 4	142	48,141	1,375	803 days

5. Results
Shortest path costs and paths indicate reachable and unreachable nodes from the selected source, while the visualization highlights high-cost nodes. Below is a sample output:
Node	          Cost	  		 Path
2	          3.0	 		[1,3,2]
5	         Infinity	      Unreachable
7	          8.2	               [1,3,5,7]
The output reveals central nodes and isolated nodes, providing insight into communication flow within the network.


6. Conclusion
Dijkstra’s Algorithm effectively reveals key nodes and potential communication bottlenecks. Future enhancements could involve temporal analysis to understand shifts over time and further refinement of weight metrics to include factors like frequency or latency.

Dataset: 
(https://snap.stanford.edu/data/email-Eu-core-temporal.html)
1. email-Eu-core-temporal-Dept1 
2. email-Eu-core-temporal-Dept2
3. email-Eu-core-temporal-Dept3
4. email-Eu-core-temporal-Dept4

Library Used:
1. jcommon-1.0.23.jar
2. jfreechart-1.0.19.jar