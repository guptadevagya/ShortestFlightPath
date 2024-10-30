import java.io.IOException;
import java.util.List;

public interface BackendInterface {

    /**
     * Expected constructor in implementing classes:
     * Backend(GraphADT<String, Double> graph)
     */

    /**
     * Reads data from a DOT file and constructs a graph from it.
     *
     * @param filePath The path to the DOT file containing graph data.
     */
    void readDataFromFile(String filePath) throws IOException;

    /**
     * Calculates and returns the shortest route between two airports in the
     * dataset.
     *
     * @param startAirport       The starting airport for the route.
     * @param destinationAirport The destination airport for the route.
     */
    List<City> getShortestRoute(City startAirport, City destinationAirport);

    /**
     * Returns a string with statistics about the dataset.
     *
     * @return A string including the number of nodes (airports), the number of
     *         edges (flights),
     *         and the total miles (sum of weights) for all edges in the graph.
     */
    String getDatasetStatistics();
}
