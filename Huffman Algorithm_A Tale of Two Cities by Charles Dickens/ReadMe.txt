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
1. A Tale of Two Cities by Charles Dickens (https://www.gutenberg.org/ebooks/98)
----------------------------------------------------------------