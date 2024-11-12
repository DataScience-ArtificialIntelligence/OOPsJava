# Graph-Based Analysis of Social Networks Using Shortest Path Algorithms and Real-Time Updates
## Team Members
- **Atharva Agrawal**: 23BDS010
- **Arnav Gupta**: 23BDS009
## Description
This project explores the use of Dijkstra’s and Bellman-Ford algorithms to determine shortest paths in a massive unweighted social network graph. By analyzing connections within a real-world dataset, we aim to understand the structure and relationships within social networks, specifically through calculating the shortest paths between users.

## Dataset
The analysis is based on an anonymized dataset from Facebook’s social network, containing connections between users. Each node represents a unique user, while each edge signifies a friendship or connection between users, forming an undirected, unweighted graph.

- **Dataset Source:** [Facebook Social Network Dataset](https://snap.stanford.edu/data/ego-Facebook.html)
- **Nodes (Users):** Represent individual users within the social network.
- **Edges (Friendships):** Indicate friendships or connections between users. Edges are unweighted, meaning they only reflect the existence of a connection without information on strength or interaction frequency.

## Key Algorithms

### Dijkstra’s Algorithm
- **Purpose:** Calculates shortest paths from a source node to all other nodes in a non-negative weighted graph.
- **Limitation:** Cannot handle negative weights.
- **Use Case:** Effective in social networks with non-negative edge weights, as it is computationally efficient for large datasets.

### Bellman-Ford Algorithm
- **Purpose:** Calculates shortest paths in graphs with possible negative edge weights and detects negative cycles.
- **Limitation:** Slower than Dijkstra for non-negative weights but essential for graphs where edge weights may be negative.
- **Use Case:** Useful when edge weights could be negative, representing scenarios with fluctuating connection values (e.g., relationship strength or trust scores).

## Project Structure

To access the source code:

1. Open the **algo** folder.
2. Go to the **src** directory.
3. Open the Java file named `GraphAlgorithms`.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE such as Eclipse or IntelliJ IDEA

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
