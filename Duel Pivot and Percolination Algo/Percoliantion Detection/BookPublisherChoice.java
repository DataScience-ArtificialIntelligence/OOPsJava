import java.io.*;
import java.util.*;

public class BookPublisherChoice {

    static class Book {
        String title;
        String publisher;

        public Book(String title, String publisher) {
            this.title = title;
            this.publisher = publisher;
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/hariprasadl/Documents/Percolation/src/BX-Books.csv";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the year of publication: ");
        String inputYear = scanner.nextLine();
        Map<String, List<Book>> publisherMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] columns = line.split(";");
                if (columns.length >= 5) {
                    String year = columns[3].replace("\"", "");
                    String title = columns[1].replace("\"", "");
                    String publisher = columns[4].replace("\"", "");
                    if (year.equals(inputYear)) {
                        publisherMap.putIfAbsent(publisher, new ArrayList<>());
                        publisherMap.get(publisher).add(new Book(title, publisher));
                    }
                }
            }
            if (publisherMap.isEmpty()) {
                System.out.println("No books found for the given year.");
                return;
            }
            System.out.println("Publishers who published books in the year " + inputYear + ":");
            List<String> publishers = new ArrayList<>(publisherMap.keySet());
            for (int i = 0; i < publishers.size(); i++) {
                System.out.println((i + 1) + ". " + publishers.get(i));
            }
            System.out.print("Choose a publisher by number: ");
            int publisherChoice = scanner.nextInt();
            scanner.nextLine();
            if (publisherChoice < 1 || publisherChoice > publishers.size()) {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
            String selectedPublisher = publishers.get(publisherChoice - 1);
            List<Book> books = publisherMap.get(selectedPublisher);
            System.out.println("Books published by " + selectedPublisher + " in " + inputYear + ":");
            for (Book book : books) {
                System.out.println("Title: " + book.title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}