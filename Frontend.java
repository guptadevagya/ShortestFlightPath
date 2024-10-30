
// --== CS400 File Header Information ==--
// Name: Will Foster
// Email: wjfoster2@wisc.edu
// Group and Team: F10
// Group TA: Anvay Grover
// Lecturer: Gary
// Notes to Grader: <optional extra notes>
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * The Frontend class implements the FrontendInterface and interacts with the
 * user
 */
public class Frontend implements FrontendInterface {
    private final Backend backend;
    private final Scanner scanner;

    /**
     * constructor accepts a reference to the backend and a
     * and a java.util.Scannerinstance to read user input
     * 
     * @param backend the backend interface to interact with
     * @param scanner to initialize that will read user input
     */
    public Frontend(Backend backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    /**
     * Separates prompts for clarity for user
     */
    private void lineSeparator() {
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        DijkstraGraph<City, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        Frontend frontend = new Frontend(backend, new Scanner(System.in));
        try {
            frontend.mainLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainLoop() throws IOException {
        boolean keepRunning = true;
        while (keepRunning) {
            lineSeparator();
            System.out.println("Enter number corresponding to command you want to execute");
            displayMainMenu();
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    loadFile();
                    break;
                case "2":
                    showStatistics();
                    break;
                case "3":
                    shortestRoute();
                    break;
                case "4":
                    System.out.println("App exited");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, try again");
            }
        }
    }

    /**
     * shows the user the menu of options
     */
    @Override
    public void displayMainMenu() {
        System.out.println("1. Load Data File");
        System.out.println("2. Show Statistics");
        System.out.println("3. Shortest Route");
        System.out.println("4. Exit");
    }

    /**
     * Asks for and attempts to load a data file. If file doesn't exist, says so and
     * returns to menu.
     * If error loading file, warns user.
     */
    @Override
    public void loadFile() {
        System.out.println("Enter the path of the file:");
        String filePath = scanner.nextLine();
        try {
            backend.readDataFromFile(filePath);
            System.out.println("File loaded");
        } catch (IOException e) {
            System.out.println("Invalid file name, try again");
        }
    }

    /**
     * Shows number of airports, flights, and cumulative flight distance.
     */
    @Override
    public void showStatistics() {
        String statistics = backend.getDatasetStatistics();
        System.out.println("Dataset Statistics:\n" + statistics);
    }

    /**
     * Helper method for shortestRoute
     * Is called by shortestRoute to get info from backend
     */

    public List<City> getRoute(City startAirport, City endAirport) {
        return backend.getShortestRoute(startAirport, endAirport);
    }

    /**
     * Asks the user for start and destination airport, then displays the shortest
     * route.
     * If either airport doesn't exist, it will tell the user to check for typos.
     */
    @Override
    public void shortestRoute() {
        System.out.println("Enter the start city:");
        String startAirport = scanner.nextLine();
        System.out.println("Enter the destination city:");
        String endAirport = scanner.nextLine();
        try {
            List<City> route = getRoute(backend.getCityByNameOrAbbreviation(startAirport),
                    backend.getCityByNameOrAbbreviation(endAirport));
            System.out.println(route);
        } catch (NullPointerException e) {
            System.out.println("Check for typos, no route was found");
        } catch (NoSuchElementException e) {
            System.out.println("Check for typos, no route was found");
        }
    }
}
