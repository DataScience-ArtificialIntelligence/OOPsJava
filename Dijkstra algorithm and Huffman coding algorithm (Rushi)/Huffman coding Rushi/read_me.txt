Group member are Komeri Rushi and J.P.K. Sree Harsha.

Overview:-
Huffman Coding is a compression algorithm used to reduce the size of data by encoding characters based on their frequencies of occurrence. It achieves efficient data compression by assigning shorter binary codes to frequently occurring characters and longer codes to less frequent characters, ensuring that the total number of bits is minimized. This algorithm is particularly useful in file compression, data transmission, and optimizing storage.


Advantages:-
Compression Efficiency: Huffman Coding significantly reduces the size of files, making data storage more efficient and saving transmission bandwidth.
Lossless Compression: The encoded data can be perfectly decoded to its original form, preserving data integrity.
Variable-Length Encoding: Huffman Coding dynamically assigns shorter codes to more frequent characters, optimizing compression compared to fixed-length codes.
Adaptability: It adapts well to the frequency distribution of characters in the input, making it highly effective for varied datasets.
Widely Used: It’s used in image and text compression formats like JPEG and ZIP due to its simplicity and effectiveness.


Code Summary:-
The provided Java program performs Huffman Coding on a text file (example.txt), producing an output file (output.txt) that includes:
The Huffman codes for each character.
The encoded binary string for the input text.
The decoded string to confirm the data can be retrieved correctly.
The bit counts before and after compression to demonstrate the algorithm's effectiveness.


Main Functions:-
buildHuffmanTree: Constructs the Huffman Tree based on character frequencies and assigns binary codes to each character.
generateHuffmanCodes: Recursively generates binary codes for each character by traversing the Huffman Tree.
encode: Encodes the input string into a binary string based on the generated Huffman codes.
decode: Decodes the binary string back to the original text using the Huffman codes.
calculateBitsBefore and calculateBitsAfter: Calculate the bit lengths before and after encoding to assess the compression efficiency.
writeOutputToFile: Writes the Huffman codes, encoded string, decoded string, and bit counts to output.txt.


How to Use:-
Prepare the Dataset:
Place your input file (example.txt) with the text data to be encoded inside the data/ folder.

Compile the Java Program:
Open a terminal or command prompt.
Navigate to the src/ directory where the Main.java file is located.


Check the Output:-
Open the output.txt file to see the results. The output will include:
The Huffman codes for each character.
The encoded string (compressed binary form).
The decoded string (to ensure that the decoding works correctly).
Bit counts before and after encoding, to show the compression efficiency.
