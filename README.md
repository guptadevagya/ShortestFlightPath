# ✈️ Flight Router: Shortest Path Finder for Airports

## Project Overview

**Flight Router** is an application that enables users to find the shortest sequence of flights (with the fewest miles) between a specified start and destination airport. This is achieved using **Dijkstra's shortest path algorithm**, with airports represented as nodes and flights as edges in a graph. The app reads data from a user-specified local file and provides interactive features for route searching and dataset exploration.

---

## 📑 Key Features

1. **Shortest Route Search**: Finds the shortest sequence of flights between two airports, displaying each flight segment with its distance in miles.
2. **Dataset Statistics**: Displays statistics about the dataset, including the number of airports, flights, and the total miles of all flights in the dataset.
3. **Interactive Interface**: Allows users to load a dataset, perform route searches, view statistics, and exit the app through a user-friendly command loop.

---

## 🎯 Representative Tasks

- **Route Search**: List all flights (with their distances in miles) for the shortest route from a starting airport (e.g., ATL) to a destination airport (e.g., SFO).
- **Dataset Summary**: Show the total number of airports, flights, and cumulative miles in the dataset.

---

## 🛠 Project Structure and Roles

### Backend Developer Role (My Role)

The **Backend Developer** is responsible for implementing the core functionalities of the application, specifically related to data handling, graph management, and shortest path computations.

#### Responsibilities

1. **Graph Data Handling**: Reads airport and flight data from a user-specified file and loads it into a graph data structure (using the `GraphADT` interface).
2. **Shortest Path Computation**: Implements Dijkstra's algorithm to find the shortest path in terms of miles between two airports.
3. **Result Storage**: Creates a class that stores the results of a shortest path search, including:
   - The route as a list of airports.
   - The distance for each segment along the route.
   - The total miles for the route.
4. **Statistics Retrieval**: Provides methods for accessing dataset statistics, including the number of airports, the number of flights, and the total miles of all flights.

#### Interface Design

- **Result Interface**: Provides getter methods for accessing the route, segment distances, and total route miles.
- **Backend Interface**: Provides methods for:
  - Reading data from a file.
  - Finding the shortest route between two airports.
  - Retrieving dataset statistics (number of airports, flights, and total miles).

#### Code and Placeholder Responsibilities

- Fully implement both interfaces, adhering to the specifications.
- Create a placeholder class for `GraphADT` to enable testing and allow the backend classes to function independently.

#### Presentation Responsibilities

After integration, demonstrate the functionality by searching for the shortest route between two airports (e.g., ATL to SFO), showing the miles for each segment and the total miles.

---

### Frontend Developer Role

The **Frontend Developer** is responsible for building the interactive user interface, handling commands, and presenting results from the backend to the user.

#### Responsibilities

1. **Interactive Command Loop**: Prompts the user to select actions and collects required inputs.
2. **Command Implementations**:
   - **Load Data File**: Allows the user to specify and load a dataset.
   - **Show Dataset Statistics**: Displays the number of airports, flights, and the total miles in the dataset.
   - **Shortest Route Search**: Prompts the user for start and destination airports, displays the shortest route, segment distances, and total miles.
   - **Exit**: Closes the application.

#### Interface Design

- **Frontend Interface**: Contains a main command loop with separate methods for each command. The constructor accepts a backend instance and a `Scanner` for user input.

#### Code and Placeholder Responsibilities

- Fully implement the frontend interface according to the project specifications.
- Create a placeholder class for the `BackendInterface` to allow the frontend to be tested independently.

#### Presentation Responsibilities

After integration, demonstrate the app by loading a dataset and displaying statistics, such as the number of airports, flights, and total miles.

---

## 🚀 Running the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/flight-router.git
   cd flight-router
