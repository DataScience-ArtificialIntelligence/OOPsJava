package closestpair;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Point2D {
    double x, y;
    String cityName;

    public Point2D(String cityName, double x, double y) {
        this.cityName = cityName;
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point2D other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public String toString() {
        return cityName + " (" + x + ", " + y + ")";
    }
}

public class ClosestPair {
    private Point2D best1, best2;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public ClosestPair(Point2D[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least two points are required.");
        }

        int n = points.length;
        Point2D[] pointsByX = points.clone();
        Arrays.sort(pointsByX, (p1, p2) -> Double.compare(p1.x, p2.x));
        Point2D[] pointsByY = points.clone();
        Arrays.sort(pointsByY, (p1, p2) -> Double.compare(p1.y, p2.y));

        Point2D[] aux = new Point2D[n];
        closest(pointsByX, pointsByY, aux, 0, n - 1);
    }

    private double closest(Point2D[] pointsByX, Point2D[] pointsByY, Point2D[] aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;

        int mid = lo + (hi - lo) / 2;
        Point2D median = pointsByX[mid];
        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid + 1, hi);
        double delta = Math.min(delta1, delta2);

        merge(pointsByY, aux, lo, mid, hi);

        int m = 0;
        for (int i = lo; i <= hi; i++) {
            if (Math.abs(pointsByY[i].x - median.x) < delta) {
                aux[m++] = pointsByY[i];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && (aux[j].y - aux[i].y) < delta; j++) {
                double distance = aux[i].distanceTo(aux[j]);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDistance) {
                        bestDistance = distance;
                        best1 = aux[i];
                        best2 = aux[j];
                    }
                }
            }
        }
        return delta;
    }

    private static void merge(Point2D[] pointsByY, Point2D[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = pointsByY[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) pointsByY[k] = aux[j++];
            else if (j > hi) pointsByY[k] = aux[i++];
            else if (aux[j].y < aux[i].y) pointsByY[k] = aux[j++];
            else pointsByY[k] = aux[i++];
        }
    }

    public Point2D either() {
        return best1;
    }

    public Point2D other() {
        return best2;
    }

    public double distance() {
        return bestDistance;
    }

    public static void main(String[] args) {
        try {
            // Set a modern look and feel (Nimbus)
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Nimbus look and feel not available, using default.");
        }

        List<Point2D> points = new ArrayList<>();

        // Customize the file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a CSV File with City Coordinates");
        fileChooser.setApproveButtonText("Choose File");

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                br.readLine(); // Skip header line
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        System.err.println("Invalid line format (not enough fields): " + line);
                        continue;
                    }

                    try {
                        String cityName = parts[0].trim();
                        double x = Double.parseDouble(parts[1].trim());
                        double y = Double.parseDouble(parts[2].trim());
                        points.add(new Point2D(cityName, x, y));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing coordinates (non-numeric values): " + line);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (points.size() < 2) {
                JOptionPane.showMessageDialog(null, "Not enough points to find a closest pair.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Point2D[] pointsArray = points.toArray(new Point2D[0]);
            ClosestPair closestPair = new ClosestPair(pointsArray);

            // Load custom icon image (replace with your icon path)
            ImageIcon customIcon = new ImageIcon("path/to/your/icon.png"); // Replace with actual path to your icon

            // Show result with custom icon
            JOptionPane.showMessageDialog(null,
                    "Closest distance: " + closestPair.distance() + "\n" +
                            "Closest pair: " + closestPair.either() + " and " + closestPair.other(),
                    "Result", JOptionPane.INFORMATION_MESSAGE, customIcon);
        } else {
            JOptionPane.showMessageDialog(null, "No file selected. Program will exit.", "No File", JOptionPane.WARNING_MESSAGE);
        }
    }
}
