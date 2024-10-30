// Importing necessary libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class declaration
public class Backend implements BackendInterface {
    // Member variable declaration
    DijkstraGraph<City, Integer> graph;
    Map<City, Integer> weightsMap;
    int totalMiles = 0;

    /**
     * Constructor for Backend.
     * Initializes the Backend with the given GraphADT instance for graph
     * operations.
     * 
     * @param graph The GraphADT instance to be used for graph operations.
     */

    public Backend(DijkstraGraph<City, Integer> graph) {
        this.graph = graph;
        this.weightsMap = new HashMap<>();
    }

    /**
     * Reads data from a DOT file and constructs a graph from it.
     * 
     * @param filePath
     * @throws IOException
     */

    @Override
    public void readDataFromFile(String filePath) throws IOException {
        List<String> temList = new ArrayList<String>();
        if (!filePath.equals("flights.dot")) {
            throw new IOException();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(" -- ")) {
                    temList.add(line);

                } else if (line.contains("[city=")) {
                    City city = getCity(line);
                    if (city != null) {
                        cities.add(city);
                    }
                }
            }
        } catch (Exception e) {
            throw new IOException("File not found");
        }

        // Adding flights to the flights list
        for (Object i : temList) {
            Flight flight = getFlight((String) i);
            if (flight != null) {
                flights.add(flight);
            }
        }
        // Adding cities to the graph as nodes
        for (City city : cities) {
            graph.insertNode(city);
            if (city == null) {
                System.out.println("City is null");
            }
        }

        // Adding flights to the graph as edges
        for (Flight flight : flights) {
            graph.insertEdge(flight.getStartAirport(), flight.getDestinationAirport(), flight.getDistance());
        }
    }

    /**
     * Returns a Flight object from the given line.
     * 
     * @param line The line containing flight information.
     * @return A Flight object.
     */

    public Flight getFlight(String line) {
        // Splitting the line by "--"
        String[] airports = line.split("--");

        // Removing quotes and trimming spaces for start and end airports
        String startAirport = airports[0].replace("\"", "").trim();
        String destinationAirport = airports[1].substring(0, airports[1].indexOf("[")).replace("\"", "").trim();

        // Finding the distance
        int milesIndex = line.indexOf("miles=");

        if (milesIndex != -1) {
            int semicolonIndex = line.indexOf("]", milesIndex);
            String weightStr = line.substring(milesIndex + 6, semicolonIndex).trim();
            int weight = Integer.parseInt(weightStr);
            City startCity = null;
            City endCity = null;

            // finding total miles in the dot file
            for (Flight i : flights) {
                totalMiles += i.getDistance();
            }

            for (City i : cities) {
                // Finding the start city
                if (i.getAbbreviation().equals(startAirport)) {
                    startAirport = i.getName();
                    startCity = i;
                }
                // Finding the end city
                if (i.getAbbreviation().equals(destinationAirport)) {
                    destinationAirport = i.getName();
                    endCity = i;
                }
            }
            // Adding the weight to the weights map
            return new Flight(startCity, endCity, weight);
        }
        // Return null if no distance is found
        return null;
    }

    // Member variable declaration
    List<Flight> flights = new ArrayList<>();
    List<City> cities = new ArrayList<>();

    /**
     * Returns a City object from the given line.
     * 
     * @param line The line containing city information.
     * @return
     */

    public City getCity(String line) {
        // Extracting the city abbreviation (assuming it's before the '[')
        int bracketIndex = line.indexOf("[");
        String abbreviation = line.substring(0, bracketIndex).trim().replace("\"", "");

        // Extracting the city name
        int cityIndex = line.indexOf("city=");
        if (cityIndex != -1) {
            int semicolonIndex = line.indexOf("];", cityIndex);
            String fullName = line.substring(cityIndex + 6, semicolonIndex).replace("\"", "").trim();

            // Extracting only the city name, not the state
            String cityName = fullName.contains(",") ? fullName.substring(0, fullName.indexOf(",")).trim() : fullName;

            return new City(cityName, abbreviation);
        }
        return null;
    }

    /**
     * Returns a City object from the given identifier.
     * 
     * @param identifier The name or abbreviation of the city.
     * @return
     */

    public City getCityByNameOrAbbreviation(String identifier) {
        for (City city : cities) {
            if (identifier.length() == 3) {
                if (city.getAbbreviation().equalsIgnoreCase(identifier)) {
                    return city;
                }
            } else {
                if (city.getName().equalsIgnoreCase(identifier)) {
                    return city;
                }
            }
        }
        return null; // Return null if no matching city is found
    }

    /**
     * Calculates the shortest route between the given airports.
     * 
     * @param startAirport       The three letter code of the starting airport.
     * @param destinationAirport The three letter code of the destination airport.
     * @return An instance of ShortestPathResultInterface containing the shortest
     *         route details.
     */

    @Override
    public List<City> getShortestRoute(City startAirport, City destinationAirport) {

        ShortestPathResult shortestPathResult = new ShortestPathResult(graph);
        List<City> route = shortestPathResult.getRoute(startAirport, destinationAirport);
        List<Integer> milesPerSegment = shortestPathResult.getMilesPerSegment(startAirport, destinationAirport);
        int totalMiles = shortestPathResult.getTotalMiles(startAirport, destinationAirport);

        // Check if the total miles is equal to the miles of a single segment (Direct
        // Flight)
        if (milesPerSegment.size() == 1 && totalMiles == milesPerSegment.get(0)) {
            System.out.println("It is a direct flight!");
        }
        // Printing miles for each segment
        System.out.println("Miles for each segment:");
        for (int i = 0; i < route.size() - 1; i++) {
            System.out.println(route.get(i) + " to " + route.get(i + 1) + ": " + milesPerSegment.get(i) + " miles");
        }

        // Printing total miles
        System.out.println("Total miles: " + totalMiles);

        return route;
    }

    /**
     * Returns a string with statistics about the dataset.
     * 
     * @return A string including the number of nodes (airports), the number of
     *         edges (flights),
     *         and the total miles (sum of weights) for all edges in the graph.
     */

    @Override
    public String getDatasetStatistics() throws IllegalArgumentException {
        int flights = graph.getEdgeCount();
        int airports = graph.getNodeCount();
        String output = "Total Airports: " + airports + "\n" + "Total Flights: " + flights + "\n" + "Total Miles: "
                + totalMiles + " Miles";
        return output;
    }

    /**
     * Main method
     * 
     * @param args
     */

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
}