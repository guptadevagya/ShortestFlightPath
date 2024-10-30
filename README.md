# ✈️ Flight Router: Shortest Path Finder for Airports

## Project Overview

**Flight Router** is an application that enables users to find the shortest flight path (measured in miles) between two airports. This application leverages **Dijkstra's shortest path algorithm** and represents airports as nodes and flights as edges within a graph data structure. Users can load data from a local file and use the application to discover optimal routes, view dataset statistics, and interact with a command-line interface for seamless navigation.

---

## 📑 Key Features

1. **Shortest Route Search**: Finds the shortest sequence of flights between two specified airports, displaying each flight segment with its distance in miles.
2. **Dataset Statistics**: Provides a summary of the dataset, including the number of airports, flights, and the cumulative mileage of all flights.
3. **Interactive CLI**: Allows users to load datasets, perform route searches, view statistics, and exit the application in an interactive, user-friendly way.

---

## 🎯 Representative Tasks

- **Route Search**: Identify the shortest route from a starting airport (e.g., ATL) to a destination airport (e.g., SFO), displaying each flight and its distance.
- **Dataset Summary**: Display the total number of airports, flights, and the cumulative mileage for all available routes in the dataset.

---

## 🛠 Project Structure and Roles

### Backend Developer Role (Your Role)

The **Backend Developer** is responsible for implementing the core functionalities of the application, specifically related to data handling, graph construction, and shortest path computations. 

#### Responsibilities

1. **Graph Data Handling**: Reads airport and flight data from a user-specified DOT file and constructs a graph representation using `GraphADT`.
2. **Shortest Path Computation**: Utilizes Dijkstra's algorithm to determine the shortest path in miles between two airports.
3. **Result Storage**: Implements a class (`ShortestPathResult`) that stores the shortest path results, including:
   - The route as a list of airports.
   - The distance for each segment along the route.
   - The total distance for the route.
4. **Statistics Retrieval**: Provides methods to retrieve dataset statistics, such as the number of airports, flights, and the total mileage.

#### Core Backend Files

- **`Backend.java`**: Implements backend functionalities for reading data, calculating shortest paths, and retrieving dataset statistics.
- **`BackendInterface.java`**: Defines the interface for backend operations, specifying methods for data loading, route finding, and statistics retrieval.
- **`DijkstraGraph.java`**: Implements Dijkstra's algorithm for finding the shortest path between nodes (airports) in the graph.
- **`ShortestPathResult.java`**: Stores the results of a shortest path search, providing access to the route, segment distances, and total route distance.
- **`ShortestPathResultInterface.java`**: Interface defining methods for retrieving details from `ShortestPathResult`.
- **`GraphADT.java`**: Abstract Data Type defining the structure and operations of the graph, representing airports and flights.
- **`BaseGraph.java`**: Base implementation of the graph structure, providing foundational methods for node and edge management.
- **`City.java`**: Represents an individual airport node within the graph, storing information about each airport.
- **`Flight.java`**: Represents individual flights, including details like distance and connecting airports.

#### Presentation Responsibilities

After integration, the backend developer demonstrates the shortest route calculation between airports, displaying each segment and the total distance.

---

### Frontend Developer Role

The **Frontend Developer** builds the interactive user interface, which facilitates command-based interactions between the user and the backend.

#### Responsibilities

1. **Command Loop**: Prompts the user to select actions and collects required inputs for each command.
2. **Command Implementations**:
   - **Load Data File**: Allows the user to load a DOT file containing airport and flight data.
   - **Show Dataset Statistics**: Displays the number of airports, flights, and cumulative mileage.
   - **Shortest Route Search**: Collects start and destination airports from the user and displays the shortest route with segment distances.
   - **Exit**: Exits the application.

#### Core Frontend Files

- **`Frontend.java`**: Manages the command-line interface and coordinates with the backend to display results to the user.
- **`FrontendInterface.java`**: Defines the interface for frontend operations, specifying methods for each command.
- **`TextUITester.java`**: Contains tests for the text-based user interface.
- **`FrontendDeveloperTests.java`**: Contains test cases to verify frontend functionality.

---

## 🚀 Running the Application

1. Compile the Code:

   ```bash
   javac *.java
   
2. Run the Application:

   ```bash
   java Main
   
3. Commands:
   - **Load Data**: Specify a DOT file to load airport and flight data.
   - **View Dataset Statistics**: Display the number of airports, flights, and total mileage.
   - **Find Shortest Route**: Enter a start and destination airport to view the shortest route and segment distances.
   - **Exit**: Close the application.

---

## 📝 Example Usage

- **Load a Data File**:  
  Input: `flights.dot`

- **View Dataset Statistics**:  
  Output: `Airports: 50, Flights: 200, Total Miles: 25,000`

- **Find Shortest Route**:  
  Input: Start: `ATL`, Destination: `SFO`
   
  Output:
  ```plaintext
  Route: ATL -> DFW -> PHX -> SFO
  Distances: 760 miles, 870 miles, 650 miles
  Total Distance: 2,280 miles
- **Exit the Application**

---

## 🔧 Technologies Used

- **Java**: Primary programming language.
- **Dijkstra's Algorithm**: Core algorithm for finding shortest paths.
- **Graph Data Structure**: Represents airports and flights within the application.

---

## 🏆 Key Contributions

As the **Backend Developer**, my contributions include:

- Implementing data parsing and graph loading functionalities.
- Designing and implementing shortest path algorithms for route finding.
- Creating interfaces and classes to store and retrieve shortest path results.
- Integrating backend functionality with the frontend for seamless user interactions.
