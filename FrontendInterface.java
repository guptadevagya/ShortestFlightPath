import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface FrontendInterface {
    /**
     * constructor accepts a reference to the backend and a
     * and a java.util.Scannerinstance to read user input
     * 
     * @param backend the backend interface to interact with
     * @param scanner to initialize that will read user input
     * @param backend the backend interface to interact with
     * @param scanner to initialize that will read user input
     */
    // public IndividualFrontend(BackendInterface backend, Scanner scanner);

    /**
     * shows the user the menu of options
     */
    public void displayMainMenu();

    /**
     * Asks for and attempts to load a data file. If file doesn't exist, says so and
     * returns to menu.
     * If error loading file, warns user.
     */
    public void loadFile();

    /**
     * Shows number of airports, flights, and cumulative flight distance.
     */
    public void showStatistics();

    /**
     * Helper method for shortestRoute
     * Is called by shortestRoute to get info from backend
     */
    public List<City> getRoute(City startAirport, City endAirport);

    /**
     * Asks the user for start and destination airport, then displays the shortest
     * route.
     * If either airport doesn't exist, it will tell the user to check for typos.
     */
    public void shortestRoute();
}