import java.io.*;
import java.nio.file.*;


public class LongestCommonSubsequence {

    public static void main(String[] args) {
        // Check if correct number of arguments are provided (two input files and one output file)
        if (args.length != 3) {
            System.out.println("Please provide two input file paths and one output file path.");
            return;
        }

        // Read the content of the two files using BufferedReader
        String text1 = readFile(args[0]);
        String text2 = readFile(args[1]);

        if (text1 == null || text2 == null) {
            System.out.println("Error reading files.");
            return;
        }

        // Find the LCS and the indices
        LCSResult result = findLCS(text1, text2);

        if (result.lcs == null || result.lcs.length() == 0) {
            System.out.println("No common subsequence found.");
            return;
        }

        // Write output to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[2]))) {
            writer.write("Longest Common Subsequence: " + result.lcs + "\n");
            writer.write("Length of LCS: " + result.lcs.length() + "\n");
            writer.write("Start Index in first string (wild_type_APOE.txt): " + result.startIndex1 + "\n");
            writer.write("End Index in first string (wild_type_APOE.txt): " + result.endIndex1 + "\n");
            writer.write("Start Index in second string (mutated_APOE.txt): " + result.startIndex2 + "\n");
            writer.write("End Index in second string (mutated_APOE.txt): " + result.endIndex2 + "\n");

            // Highlight and print the matching LCS with indices
            writer.write("\nMatching LCS Part:\n");
            writer.write("Matching part in text1 (wild_type_APOE.txt) at index: " + result.startIndex1 + " to " + result.endIndex1 + "\n");
            writer.write("Matching part in text2 (mutated_APOE.txt) at index: " + result.startIndex2 + " to " + result.endIndex2 + "\n");
            writer.write("LCS: " + result.lcs + "\n");
            writer.write("\nHighlighted LCS in both strings:\n");
            writer.write("Highlighted in text1: " + highlightMatchingLCS(text1, result.startIndex1, result.endIndex1, result.lcs) + "\n");
            writer.write("Highlighted in text2: " + highlightMatchingLCS(text2, result.startIndex2, result.endIndex2, result.lcs) + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to output file.");
        }

        // Output results to the console
        System.out.println("Longest Common Subsequence: " + result.lcs);
        System.out.println("Length of LCS: " + result.lcs.length());
        System.out.println("Start Index in first string (wild_type_APOE.txt): " + result.startIndex1);
        System.out.println("End Index in first string (wild_type_APOE.txt): " + result.endIndex1);
        System.out.println("Start Index in second string (mutated_APOE.txt): " + result.startIndex2);
        System.out.println("End Index in second string (mutated_APOE.txt): " + result.endIndex2);
        System.out.println("Results written to " + args[2]);
    }

    // Function to read content from a file using BufferedReader
    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);  // Append the line without tokenization
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            return null;
        }
        return content.toString(); // Return the entire concatenated content as a single string
    }

    // Function to find the longest common subsequence between two strings
    public static LCSResult findLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // Create a DP table to store lengths of longest common subsequence
        int[][] dp = new int[m + 1][n + 1];

        // Build the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Now, reconstruct the LCS string and track the indices
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        int startIndex1 = -1, endIndex1 = -1;
        int startIndex2 = -1, endIndex2 = -1;

        // Backtrack to get the LCS and indices
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));

                // Track the indices (only the first match will set the start indices)
                if (startIndex1 == -1 && startIndex2 == -1) {
                    startIndex1 = i - 1; // first match in text1
                    startIndex2 = j - 1; // first match in text2
                }

                // Continue updating end indices as we backtrack
                endIndex1 = i - 1; // last match in text1
                endIndex2 = j - 1; // last match in text2

                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Reverse the LCS since we built it backwards
        lcs.reverse();

        // Ensure the indices are in the correct order
        if (startIndex1 > endIndex1) {
            int temp = startIndex1;
            startIndex1 = endIndex1;
            endIndex1 = temp;
        }

        if (startIndex2 > endIndex2) {
            int temp = startIndex2;
            startIndex2 = endIndex2;
            endIndex2 = temp;
        }

        // Return the LCS along with the start and end indices
        return new LCSResult(lcs.toString(), startIndex1, endIndex1, startIndex2, endIndex2);
    }

    // Function to highlight the matching LCS in the string (just returns the substring for simplicity)
    private static String highlightMatchingLCS(String text, int startIndex, int endIndex, String lcs) {
        // Ensure the indices are within valid bounds
        if (startIndex < 0 || startIndex >= text.length() || endIndex < 0 || endIndex >= text.length()) {
            return "Invalid indices!";
        }

        // Highlight the matching LCS substring in the text
        StringBuilder highlighted = new StringBuilder();
        highlighted.append(text.substring(0, startIndex)); // Before the match
        highlighted.append("["); // Start highlight
        highlighted.append(lcs); // LCS
        highlighted.append("]"); // End highlight
        highlighted.append(text.substring(endIndex + 1)); // After the match
        return highlighted.toString();
    }

    // LCS result class to store LCS and its indices
    static class LCSResult {
        String lcs;
        int startIndex1, endIndex1;
        int startIndex2, endIndex2;

        LCSResult(String lcs, int startIndex1, int endIndex1, int startIndex2, int endIndex2) {
            this.lcs = lcs;
            this.startIndex1 = startIndex1;
            this.endIndex1 = endIndex1;
            this.startIndex2 = startIndex2;
            this.endIndex2 = endIndex2;
        }
    }
}
