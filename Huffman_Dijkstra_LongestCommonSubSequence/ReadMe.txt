Group Members:
Kamal Nayan Kumar - 23BDS026
Rahul Patel - 23BDS047
Vijaypal Singh Rathore - 23BDS067

#Huffman Coding:(Kamal Nayan Kumar - 23BDS026)

Huffman Compression and Decompression Tool

1. Introduction:
This project explores text compression and decompression using Huffman coding. Huffman coding is a popular lossless compression algorithm that reduces file sizes by assigning shorter binary codes to frequently occurring characters, optimizing storage and transmission efficiency. This project implements a GUI application allowing users to select a text file, compress it, and later decompress it to the original content.

2. Problem Statement
The objectives of this project are:
1.Develop a GUI that lets users select text files for compression.
2.Apply Huffman Coding to compress the selected file by minimizing the bit size of frequent characters.
3.Enable Decompression so users can restore the original file content from the compressed data.
4.Save Compressed and Decompressed Outputs in a user-defined directory for ease of access.

3. Methodology
3.1 Huffman Coding Process
The algorithm begins by calculating character frequencies in the selected text file. It then constructs a Huffman tree, where each character’s binary code is derived based on its frequency. Frequently occurring characters are assigned shorter codes.
3.2 Compression
File Selection: The GUI enables file selection for compression.
Encoding: The Huffman tree generates unique binary codes for each character in the file.
Output: The compressed binary data is saved as a .bin file in the chosen directory.
3.3 Decompression
Decoding: The algorithm reconstructs the original content by decoding the binary data using the Huffman tree.
Output: The decompressed content is saved in a .txt file in the same directory as the .bin file.

4. Data Flow and GUI Functionality
Select File: Allows user to pick a .txt file for compression.
Compression: Encodes text and saves it in a .bin file. Compression ratio is displayed to indicate efficiency.
Decompression: Restores text from the .bin file, saving it as output.txt in the same location.

5. Results
The tool successfully compresses text files, often achieving significant file size reduction without any data loss. Sample results indicate high compression ratios, especially for text with repetitive characters. Decompression restores the exact original content.

6. Conclusion
This tool demonstrates the effectiveness of Huffman coding in reducing file sizes. Potential improvements include adding support for larger file types and integrating a detailed compression report.

Datasets:
1. A Tale of Two Cities by Charles Dickens - Dataset 1 (https://www.gutenberg.org/ebooks/98)
2. Vivekanand - Dataset2 (https://archive.org/details/vivekanandabiogr00swam)
------------------------------------------------------------------------------------------------------------------------------------
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
Node	          Cost	  Path
2	            3.0	       [1,3,2]
5	          Infinity	      Unreachable
7	            8.2	       [1,3,5,7]
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
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Longest Common Subsequence:(Vijaypal Singh Rathore - 23BDS067)

Longest Common Subsequence between Wild-type and Mutated mRNA Sequences

Abstract:
This report presents a solution to find the Longest Common Subsequence (LCS) between two mRNA sequences: a wild-type APOE mRNA sequence and its mutated counterpart. The task involves reading two text files, comparing the sequences, and writing the LCS along with relevant indices to an output file. The problem is addressed using dynamic programming techniques, and the results are displayed both in the console and written to an output file.

Introduction:
The Longest Common Subsequence (LCS) is a classical problem in computer science, particularly relevant in bioinformatics for comparing biological sequences such as DNA, RNA, and protein sequences. The goal of this project is to implement a program that finds the LCS between two mRNA sequences: one representing the wild-type APOE gene and the other representing a mutated version. Identifying the longest subsequence that is common between the two sequences can provide insights into genetic variations, mutations, and their potential impact.

Methodology:
The solution uses a dynamic programming (DP) approach to find the LCS. The key steps involved are:
1.Input Files: Two input files, wild_type_APOE.txt and mutated_APOE.txt, are read into memory. These files contain the RNA sequences for the wild-type and mutated genes, respectively.
2.Dynamic Programming Table: A 2D DP table is constructed where each entry (i, j) stores the length of the LCS of the first i characters of the wild-type sequence and the first j characters of the mutated sequence.
3.Backtracking: Once the table is populated, we backtrack to reconstruct the LCS and track the start and end indices in both sequences.
4.Output: The LCS, its length, and the start and end indices in both sequences are written to the output file. Additionally, the matching LCS is highlighted within both input sequences. 

Results:
The program computes the Longest Common Subsequence (LCS) between the two input mRNA sequences. The results are as follows:
Longest Common Subsequence: GGGATCCTTGGTCCTCTCAGCCCCAGGGAGGTGAAGGACGTCCTTCCCCAGGAGCCGCTGGCCATCCGGCAGGAAGATGAAGGTTCTGTGGGCTGCGTGCTGGTCACATTCCTGCAGGATGCCAGGCCGGTGGAGAAGCGGGGAGACAGAGCGGGCCCGAGCTGCGCCGCAACCAGTGGCAGAGCGGCCAGCGCTGGGAACTGGCACTGGGTCGCTTTTGGGATACCTGCGCTGGGTGCAGACACTGTCTGAGCAGGTGCAGGAGGAGCGCTCAGCTCCCAGGTCACCCAGGAACTGAGGGCGCTGAGGACGAGACCATGAGGATTGAAGGCCTACAAATCGGAACTGGAGAACAATACCCGGGGCGGAGGAGACGCGGGCACGGCTGTCCAAGGAGCTGCAGGCGGCGAGGCCCGGCTGGGCGCGGAATGGAGGACGTGTGCGGCCGCCTGGTGCAGTACCGCGGCGAGGTGCAGGCCATGCTCGCCAGAGCACCGAGACTGCGGTCGCCTCGCCTCCCACCTGCGCAAGCTGCGTAAGCGGCTCCTCCGGATGCCGATGACCTGCAGAAGCGCCTGGCGGTACCACCGGGGCCCGCGAGGCGCCGAGCGCGGCCTCAGCGCCATCCGCGAGCCCTGGGCCCTGGTGGAAAGGCCGCGTGCGGGCGCCACTTGGGCTCCCTGGCCGGCCAGCCGCTACGGGCGGGCCCAGGCGGGGGAGCGGCTGCGCGCGCGGAGGAGGAGATGGGCAGCCGGACCCCACCCCTGGACGAGGTGAAGGGCGGTGGCGAGGTGCGCGCCAAGCTGAGGAGCAGGCCCACAAACGCCTGCAGGCCGACCTTCCAGGCCCGCCTCAAGAGCTGGTTCGAGCCCCTGGTGGAAGACAGAGCGCCAGTGGGCCGGGCTGGTGGAGAAGGGCAGGTGGTGGCACCAGCGCCGCCCCTGTGCCCAGCGACAATCACTGAACGCCGAAGCTGAGCCATGCGACCCCACGCCACCCGTGCCTCCTCCTCCGCGCAGCTGCACGGAGACCTGTCCCGCCCCAGCCGTCCTCCTGGGGTGGACCCTAGTTTAATAAAGATTACCAAGTTTCACGCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
Length of LCS: 1140
Start Index in first string (wild_type_APOE.txt): 0
End Index in first string (wild_type_APOE.txt): 1222
Start Index in second string (mutated_APOE.txt): 24
End Index in second string (mutated_APOE.txt): 3354
The program also generates a visual highlight of the LCS in both input sequences in the output file.

Conclusion:
This project successfully implemented an efficient algorithm to find the Longest Common Subsequence between two mRNA sequences. The use of dynamic programming ensures that the solution is optimal and runs in a time complexity of O(mn), where m and n are the lengths of the two sequences. The program provides valuable information about the genetic similarity between the wild-type and mutated mRNA, which can be useful for further bioinformatics analysis.

Future Work:
In future iterations, the program could be extended to handle larger datasets and different types of sequence comparisons. Additionally, improvements could be made to visualize the sequences and highlight the LCS more intuitively.

Dataset:
1. mutated_APOE - (https://www.ncbi.nlm.nih.gov/nuccore/NM_181458.4?report=fasta&to=3359)
2. wild_type_APOE - (https://www.ncbi.nlm.nih.gov/nuccore/NM_000041.2?report=fasta)
