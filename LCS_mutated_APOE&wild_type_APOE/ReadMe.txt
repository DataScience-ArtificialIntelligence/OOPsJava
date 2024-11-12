Group Members:
Kamal Nayan Kumar - 23BDS026
Rahul Patel - 23BDS047
Vijaypal Singh Rathore - 23BDS067

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