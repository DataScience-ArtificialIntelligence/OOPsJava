import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompressionGUI extends JFrame {

    private JLabel statusLabel;
    private File selectedFile;
    private HuffmanTree huffmanTree;
    private File compressedFile; 

    static class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        char character;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    static class HuffmanTree {
        private HuffmanNode root;
        private final Map<Character, String> huffmanCodes = new HashMap<>();

        public void buildTree(String text) {
            Map<Character, Integer> frequencyMap = new HashMap<>();
            for (char c : text.toCharArray()) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }

            PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
            }

            while (priorityQueue.size() > 1) {
                HuffmanNode left = priorityQueue.poll();
                HuffmanNode right = priorityQueue.poll();
                HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
                parent.left = left;
                parent.right = right;
                priorityQueue.add(parent);
            }

            root = priorityQueue.poll();
            generateCodes(root, "");
        }

        private void generateCodes(HuffmanNode node, String code) {
            if (node == null) return;
            if (node.left == null && node.right == null) {
                huffmanCodes.put(node.character, code);
            }
            generateCodes(node.left, code + '0');
            generateCodes(node.right, code + '1');
        }

        public Map<Character, String> getHuffmanCodes() {
            return huffmanCodes;
        }

        public HuffmanNode getRoot() {
            return root;
        }
    }

    public HuffmanCompressionGUI() {
        setTitle("Huffman Compression Tool");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(70, 70, 90), 0, getHeight(), new Color(30, 30, 50));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JLabel titleLabel = new JLabel("Huffman Compression Tool", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);

        JButton selectFileButton = createStyledButton("Choose File");
        JButton compressButton = createStyledButton("Compress");
        JButton decompressButton = createStyledButton("Decompress");

        selectFileButton.addActionListener(e -> chooseFile());
        compressButton.addActionListener(e -> compressFile());
        decompressButton.addActionListener(e -> decompressFile());

        buttonPanel.add(selectFileButton);
        buttonPanel.add(compressButton);
        buttonPanel.add(decompressButton);

        statusLabel = new JLabel("Select a file to start.", SwingConstants.CENTER);
        statusLabel.setForeground(Color.LIGHT_GRAY);
        statusLabel.setPreferredSize(new Dimension(500, 25));

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            statusLabel.setText("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    private void compressFile() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select a file first.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose location to save compressed file");
        fileChooser.setSelectedFile(new File("compressed.bin"));
        int result = fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        compressedFile = fileChooser.getSelectedFile();

        try {
            String inputText = readFile(selectedFile.getAbsolutePath());
            huffmanTree = new HuffmanTree();
            huffmanTree.buildTree(inputText);

            Map<Character, String> huffmanCodes = huffmanTree.getHuffmanCodes();
            StringBuilder compressedText = new StringBuilder();
            for (char c : inputText.toCharArray()) {
                compressedText.append(huffmanCodes.get(c));
            }

            byte[] compressedData = binaryStringToByteArray(compressedText.toString());
            writeCompressedFile(compressedFile.getAbsolutePath(), compressedData);

            long originalSize = selectedFile.length();
            long compressedSize = compressedFile.length();

            double compressionRatio = (double) compressedSize / originalSize;
            String compressionScore = String.format("Compression Ratio: %.2f", compressionRatio);

            statusLabel.setText("Compression successful! " + compressionScore + ". Saved as " + compressedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Compression successful!\n" + compressionScore);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during compression.");
        }
    }

    private void decompressFile() {
        if (huffmanTree == null || compressedFile == null) {
            JOptionPane.showMessageDialog(this, "Please compress a file first.");
            return;
        }

        File outputTextFile = new File(compressedFile.getParent(), "output.txt");

        try {
            byte[] compressedData = readCompressedFile(compressedFile.getAbsolutePath());
            String decompressedText = decompress(compressedData, huffmanTree.getRoot());
            writeDecompressedFile(outputTextFile.getAbsolutePath(), decompressedText);

            statusLabel.setText("Decompression successful! Saved as " + outputTextFile.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Decompression successful! Check " + outputTextFile.getAbsolutePath() + " for the decompressed content.");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during decompression.");
        }
    }



    private static byte[] binaryStringToByteArray(String binaryString) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteString = binaryString.substring(i, Math.min(i + 8, binaryString.length()));
            baos.write(Integer.parseInt(byteString, 2));
        }
        return baos.toByteArray();
    }

    private static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }

    private static void writeCompressedFile(String filename, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        }
    }

    private static byte[] readCompressedFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            return fis.readAllBytes();
        }
    }

    private static void writeDecompressedFile(String filename, String data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(data);
        }
    }

    private static String decompress(byte[] compressedData, HuffmanNode root) {
        StringBuilder binaryString = new StringBuilder();
        for (byte b : compressedData) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        StringBuilder decompressedText = new StringBuilder();
        HuffmanNode currentNode = root;
        for (char bit : binaryString.toString().toCharArray()) {
            currentNode = (bit == '0') ? currentNode.left : currentNode.right;
            if (currentNode.left == null && currentNode.right == null) {
                decompressedText.append(currentNode.character);
                currentNode = root;
            }
        }
        return decompressedText.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HuffmanCompressionGUI().setVisible(true));
    }
}
