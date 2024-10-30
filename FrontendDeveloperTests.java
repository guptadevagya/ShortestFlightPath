
//--== CS400 File Header Information ==--
// Name: Will Foster
// Email: wjfoster2@wisc.edu
// Group and Team: F10
// Group TA: Anvay Grover
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
import java.security.KeyStore;
import java.util.*;
import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test methods for the functionalities of the Frontend
 */
public class FrontendDeveloperTests {

    private Frontend frontend;
    private Backend backend;

    /**
     * Tests the main menu method
     */
    @Test
    public void testDisplayMainMenu() {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
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
    public void testExit() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("App exited"));
    }

    /**
     * Tests the loadFile method with valid input
     */
    @Test
    public void testLoadValidFile() throws IOException {
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
    public void testLoadInvalidFile() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\ninvalid.dot\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        String result = uiTester.checkOutput();
        try {
            frontend.mainLoop();
        } catch (IOException e) {
            assertTrue(result.contains("Error loading file"));

        }
    }

    /**
     * Tests the showStatistics method
     */
    @Test
    public void testShowStatistics() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\nflights.dot\n2\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        System.out.println(result);
        assertTrue(result.contains("Total Airports: 58\nTotal Flights: 1598\nTotal Miles: 1668672699"));
    }

    /**
     * Checks if the frontend and backend work together correctly after integration
     */
    @Test
    public void testIntegrationLoadDataFromValidFile() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\nflights.dot\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("File loaded"));
    }

    /**
     * Checks if the frontend and backend work together correctly after integration
     */
    @Test
    public void testIntegrationShortestRoute() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        TextUITester uiTester = new TextUITester("1\nflights.dot\n3\nJFK\nLAX\n4\n");
        frontend = new Frontend(backend, new Scanner(System.in));
        frontend.mainLoop();
        String result = uiTester.checkOutput();
        assertTrue(result.contains("[New York (JFK), Los Angeles (LAX)]"));
    }

    /**
     * Test for partners code to display the correct output of flights when
     * the file have not been loaded yet
     */
    @Test
    public void firstTestForParnersCodeGetFlights() throws IOException {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        String output = backend.getDatasetStatistics();
        assertTrue(output.contains("Flights: 0"));
    }

    /**
     * Test for partners code for path when airports are null
     * Should catch NullPointer exception
     */
    @Test
    public void testShortestRouteWithNullInput() {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        backend = new Backend(graph);
        assertThrows(NullPointerException.class, () -> {
            List<City> shortestRoute = backend.getShortestRoute(null, null);
        });
    }
}
