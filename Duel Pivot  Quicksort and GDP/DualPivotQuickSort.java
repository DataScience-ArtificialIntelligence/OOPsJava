import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\donia\\OneDrive\\Desktop\\JAVA data\\Data set (2).csv";  // Path to your CSV file
        String outputCsvFile = "C:\\Users\\donia\\OneDrive\\Desktop\\JAVA data\\Sorted data set.csv";  // Path for the output file

        List<CountryData> countryDataList = loadCSVData(csvFile);

        // Sort the data by GDP in descending order
        countryDataList.sort(CountryData::compareByGDP);

        // Write sorted data to output CSV file
        saveToCSV(outputCsvFile, countryDataList);
        System.out.println("Sorted data has been saved to: " + outputCsvFile);
    }

    // Load data from CSV into a list of CountryData objects
    public static List<CountryData> loadCSVData(String filePath) {
        List<CountryData> dataList = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();  // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {  // Ensure there are enough fields
                    dataList.add(new CountryData(values));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    // Save the sorted data to a CSV file
    public static void saveToCSV(String filePath, List<CountryData> dataList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Country,Year,GDP,Population\n");  // Write header
            for (CountryData data : dataList) {
                writer.write(data.toCSVRow() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// CountryData class representing each row of the dataset
class CountryData implements Comparable<CountryData> {
    private String country;
    private int year;
    private double gdp;
    private int population;

    public CountryData(String[] values) {
        this.country = values[0];
        this.year = Integer.parseInt(values[1]);
        this.gdp = values[2].isEmpty() ? 0 : Double.parseDouble(values[2]);
        this.population = values.length > 10 && !values[10].isEmpty() ? Integer.parseInt(values[10]) : 0;
    }

    // Compare by GDP for sorting
    public static int compareByGDP(CountryData a, CountryData b) {
        return Double.compare(b.gdp, a.gdp);  // Descending order
    }

    @Override
    public int compareTo(CountryData other) {
        return compareByGDP(this, other);  // Default comparison by GDP
    }

    // Convert the object data into a CSV row format
    public String toCSVRow() {
        return String.format("%s,%d,%.2f,%d", country, year, gdp, population);
    }
}
