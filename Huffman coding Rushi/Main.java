import java.io.*;
import java.util.*;

class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    // Constructor
    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = this.right = null;
    }
}

class HuffmanCoding {
    private static Map<Character, String> huffmanCode = new HashMap<>();
    private static Map<String, Character> reverseHuffmanCode = new HashMap<>();
    private static PriorityQueue<HuffmanNode> priorityQueue;

    // Function to build the Huffman Tree and generate codes
    public static void buildHuffmanTree(String input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : input.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Create a priority queue to build the Huffman Tree
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        // Generate Huffman codes from the tree
        generateHuffmanCodes(priorityQueue.peek(), "");
    }

    // Function to generate Huffman codes recursively
    private static void generateHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) return;

        // If the node is a leaf node, store its code
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, code);
            reverseHuffmanCode.put(code, root.ch);
        }

        // Traverse left and right children
        generateHuffmanCodes(root.left, code + "0");
        generateHuffmanCodes(root.right, code + "1");
    }

    // Function to encode the input string using Huffman codes
    public static String encode(String input) {
        StringBuilder encodedString = new StringBuilder();
        for (char ch : input.toCharArray()) {
            encodedString.append(huffmanCode.get(ch));
        }
        return encodedString.toString();
    }

    // Function to decode the encoded string using the reverse Huffman code
    public static String decode(String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        
        for (char bit : encodedString.toCharArray()) {
            currentCode.append(bit);
            if (reverseHuffmanCode.containsKey(currentCode.toString())) {
                decodedString.append(reverseHuffmanCode.get(currentCode.toString()));
                currentCode.setLength(0);  // Reset current code
            }
        }
        return decodedString.toString();
    }

    // Function to calculate the number of bits before Huffman coding
    public static int calculateBitsBefore(String input) {
        // Assuming each character is 1 byte (8 bits) in ASCII encoding
        return input.length() * 8;
    }

    // Function to calculate the number of bits after Huffman coding
    public static int calculateBitsAfter(String encodedString) {
        return encodedString.length();
    }

    // Function to write the Huffman codes, encoded string, decoded string, and bit count to output file
    public static void writeOutputToFile(String encodedString, String decodedString, int bitsBefore, int bitsAfter) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        
        // Write Huffman codes
        writer.write("Huffman Codes:\n");
        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        
        // Write the encoded string
        writer.write("\nEncoded String:\n");
        writer.write(encodedString);
        
        // Write the decoded string
        writer.write("\nDecoded String:\n");
        writer.write(decodedString);

        // Write the bit counts
        writer.write("\nBit Count Before Huffman Coding: " + bitsBefore + " bits\n");
        writer.write("Bit Count After Huffman Coding: " + bitsAfter + " bits\n");

        writer.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        // Read the data from the example.txt file
        BufferedReader reader = new BufferedReader(new FileReader("example.txt"));
        StringBuilder inputData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            inputData.append(line);
        }
        reader.close();

        // Perform Huffman coding on the data
        String input = inputData.toString();
        HuffmanCoding.buildHuffmanTree(input);
        String encodedString = HuffmanCoding.encode(input);
        String decodedString = HuffmanCoding.decode(encodedString);

        // Calculate bits before and after Huffman coding
        int bitsBefore = HuffmanCoding.calculateBitsBefore(input);
        int bitsAfter = HuffmanCoding.calculateBitsAfter(encodedString);

        // Write the Huffman coding output (including decoding and bit counts) to the output.txt file
        HuffmanCoding.writeOutputToFile(encodedString, decodedString, bitsBefore, bitsAfter);

        System.out.println("Huffman coding and decoding completed. Check the output.txt file.");
    }
}
