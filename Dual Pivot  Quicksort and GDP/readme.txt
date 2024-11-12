Project Title: Dual Pivot Quick Sort & Percolation Algorithm
Team Members:

Hari Prasad L (Roll No: 23BDS022)
A Doni Adithya (Roll No: 23BDS007)

Project Overview
This repository contains two distinct yet related projects that involve advanced algorithmic implementations. The first project implements the Dual Pivot Quick Sort algorithm on a large dataset. The second project explores percolation theory by simulating percolation through various data structures and graph traversal methods.

Project 1: Dual Pivot Quick Sort Algorithm
In this project, we implement the Dual Pivot Quick Sort algorithm, an efficient sorting algorithm that uses two pivot elements instead of one. This algorithm is applied to a dataset of several hundred thousand entries, simulating real-world data and testing sorting efficiency on large-scale datasets. The goal is to analyze the algorithm's performance and demonstrate its advantages over traditional quicksort.

Project 2: Percolation Algorithm Using BFS
The second project focuses on simulating the percolation process in grid-like structures. We implement the Breadth-First Search (BFS) algorithm to simulate fluid flow through a porous grid, exploring whether there exists a path from the top to the bottom of the grid. The dataset represents a 2D grid structure, where each cell can be either open or blocked, and the algorithm checks for connectivity to simulate percolation.

Table of Contents
Prerequisites
Cloning the Repository
Setting Up the Environment
Compiling and Running the Project via Command Prompt
Project 1: Dual Pivot Quick Sort Algorithm
Running the Algorithm
Dataset Overview
Project 2: Percolation Algorithm
Running the Percolation Simulation
Dataset Overview
Directory Structure
Contributing
License
Prerequisites
Before you begin, ensure that you have the following software installed:

Java Development Kit (JDK) version 8 or higher.
Git (to clone the repository).
Install Java Development Kit (JDK)
To run Java programs from the command prompt, you need to have the JDK installed on your system.

Download JDK:

Visit the official JDK download page: Download JDK (or use OpenJDK if preferred).
Choose the appropriate version for your operating system.
Install JDK:

Follow the installation instructions provided for your operating system.
Once installed, you need to set up the Java environment variables.
Set Up Java Environment Variables on Windows

Find JDK Installation Path: By default, the JDK will be installed in a path similar to C:\Program Files\Java\jdk-11.0.x.

Set JAVA_HOME:

Right-click on "This PC" or "My Computer" and click on Properties.
Click on Advanced System Settings > Environment Variables.
Under System variables, click on New and add:
Variable Name: JAVA_HOME
Variable Value: the path to your JDK folder (e.g., C:\Program Files\Java\jdk-11.0.x).
Update PATH:

In the System variables section, find the Path variable and click Edit.
Add a new entry: %JAVA_HOME%\bin.
Verify Java Installation: Open the command prompt and type:

bash
Copy code
java -version