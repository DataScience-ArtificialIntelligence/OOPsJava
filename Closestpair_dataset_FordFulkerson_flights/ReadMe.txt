ProjectTitle: ClosestPair and FordFulkersonAlgorithm

Team members
Abhinandan kumar (23bds001)
Amarjeet raj (23bds006)

1.ClosestPair Agorithm

This program implements the Closest Pair algorithm using a divide-and-conquer approach. It calculates the closest pair of cities (represented by their coordinates) from a CSV file. The program provides a graphical user interface (GUI) that allows the user to select a CSV file containing city names and their respective coordinates (latitude and longitude).

How to Run 
1. Clone or Download  the project to your local machine. 
2. Compile and run the program using your preferred IDE or from the command line. 
3. Select a CSV file using the file chooser dialog that appears when the program starts. 4. The program will compute the closest pair of cities based on their coordinates and display the result in a pop-up window.

Code Explanation 
 Point2D Class: Represents a city with its name and coordinates. It includes methods for calculating the distance to another `Point2D` object and for converting the point to a string format. 
ClosestPair Class: Implements the divide-and-conquer algorithm to find the closest pair of points. It includes sorting and merging steps, followed by checking points near the dividing line (strip) to ensure the closest pair is correctly identified.
 Main Method: Provides the GUI for file selection, reads the city data from the selected file, and calculates the closest pair using the `ClosestPair` class. 

Known Issues 
The program assumes that the CSV file has a header row. The header is skipped during processing. 
The program requires the CSV to be correctly formatted. Errors will be shown if there are invalid entries.

2.FordFulkerson Algorithm
This project implements the Ford-Fulkerson algorithm to calculate the maximum flow in a network of airports based on flight data. The user can load a flight data file containing source and destination airports along with the capacity (flight frequencies), and the program will calculate and display the maximum flow from a source airport to a sink airport.

How to Run 
1. Clone or Download  the project to your local machine. 
2. Prepare the Flight Data File in the following format 
- Each row represents a flight with a source airport, a destination airport, and the capacity (e.g., number of flights).
3. Compile and Run the `FordFulkersonproject.java` file.
  - A GUI will appear where you can select the CSV file containing the flight data. 
4. View the Results:- Once the file is loaded, the program will display the maximum flow and details of the flow between airports.

Code Explanation 
FordFulkersonproject.java: Contains the implementation of the Ford-Fulkerson algorithm, file handling, and GUI setup.
Airport Code Mappings: Includes mappings from airport codes (like LAX, SEA) to their full names (e.g., Los Angeles International Airport).
 GUI Components: ‘JButton’ for file selection. ‘JTextArea’ for displaying flow results. ‘JFileChooser’ for selecting flight data files.
   
Known Issues 
1.	CSV Format: The program assumes that the CSV file has a header row, which will be skipped during processing. The CSV file should be properly formatted with the expected data (source, destination, and capacity). Any invalid entries (e.g., incorrect formatting or non-numeric capacity values) will trigger errors in the output.
2.	Airport Code Mapping: The program relies on predefined mappings for airport codes to full names. Missing or incorrect airport codes may result in incorrect or missing output.
3.	No Source/Sink Mapping: If the source or sink airports in the CSV file are not predefined, the program may not handle the flow correctly.
