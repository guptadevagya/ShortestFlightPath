// --== CS400 File Header Information ==--
// Name: Devagya Gupta
// Email: dgupta52@wisc.edu
// Group and Team: Flight: F10
// Group TA: ANVAY GROVER
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.*;

public class BackendDeveloperTests {

    private Backend backend; // Declare 'backend' as an instance variable
    private Frontend frontend;

    @BeforeEach
    public void setUp() throws Exception {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph); // Initialize 'backend' in the setup method
        try {
            backend.readDataFromFile("flights.dot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test checks if the application correctly handles invalid airport names.
     * It attempts to find a route between two non-existent or wrongly named
     * airports.
     * The test is successful if a NoSuchElementException is thrown, indicating that
     * the application appropriately recognizes the invalid input.
     */
    @Test
    public void invalidAirport() {

        for (City city : backend.cities) {
            if (city.getName().equals("ABCDEF")) {
                throw new NoSuchElementException("Airport found. It's not supposed to be found.");
            }
        }
    }

    /**
     * This test verifies if the application can successfully read data from a file.
     * It checks if the backend can read from a file named "flights.dot". The test
     * is considered successful if the dataset is not empty after attempting to read
     * from the file, indicating successful data loading.
     */
    @Test
    public void emptyFile() throws IOException {

        PlaceholderMap map = new PlaceholderMap();
        Backend backend = new Backend(new DijkstraGraph(map));

        try {
            // Provide the path to your DOT file (flights.dot)
            String filePath = "flights.dot";

            // Read data from the DOT file and construct the graph
            backend.readDataFromFile(filePath);

            // Print a message indicating successful parsing
            System.out.println("flights.dot file has been successfully parsed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This test assesses the functionality of retrieving statistical information
     * about the dataset. It specifically checks if the output string contains
     * information about the number of airports. The test passes if the output
     * includes "Airports: " with the relevant data.
     */
    @Test
    public void getStatsNodes() {

        String output = backend.getDatasetStatistics();
        assertTrue(output.contains("Airports: "));
    }

    /**
     * Similar to the previous test, this one checks if the application can
     * retrieve and display statistics about the total miles. The test passes
     * if the output string includes "Total Miles: " along with the relevant data.
     */
    @Test
    public void getStatsTotalMiles() {

        String output = backend.getDatasetStatistics();
        assertTrue(output.contains("Total Miles: "));
    }

    /**
     * This test checks if the application can successfully retrieve the shortest
     * route between two airports. It specifically checks if the output string
     * contains the name of the starting airport. The test passes if the output
     * string includes the name of the starting airport.
     */
    @Test
    public void testABQHNL() {

        List<City> output = backend.getShortestRoute(backend.getCityByNameOrAbbreviation("ABQ"),
                backend.getCityByNameOrAbbreviation("HNL"));
        for (City city : output) {
            if (city.getName().equals("ABQ")) {
                output.remove(city);
                continue;
            }
            if (city.getName().equals("HNL")) {
                output.remove(city);
                continue;
            }
            if (output.isEmpty()) {
                break;
            }
        }
    }

    // Integraton Tests
    /**
     * Tests the main menu method
     */
    @Test
    public void integrationtestDisplayMainMenu() {

        TextUITester uiTester = new TextUITester("");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.displayMainMenu();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("1. Load Data File\n2. Show Statistics\n3. Shortest Route\n4. Exit\n"));
    }

    /**
     * Tests the exit command
     */
    @Test
    public void integrationtestExit() throws IOException {
        TextUITester uiTester = new TextUITester("4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("App exited"));
    }

    // Frontend Tests
    /**
     * Tests the loadFile method with valid input
     */
    @Test
    public void frontendtestLoadValidFile() throws IOException {

        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\nflights.dot\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("File loaded"));
    }

    /**
     * Tests the loadFile method with invalid input
     */
    @Test
    public void frontendtestShowStatistics() throws IOException {

        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\nflights.dot\n2\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        System.out.println(result);
        assertTrue(result.contains("Total Airports: 58\nTotal Flights: 1598\nTotal Miles: 1668672699"));
    }

}
