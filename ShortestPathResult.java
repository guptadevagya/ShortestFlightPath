import java.util.ArrayList;
import java.util.List;

public class ShortestPathResult implements ShortestPathResultInterface {

    // Member variable declaration
    DijkstraGraph graph;

    public ShortestPathResult(DijkstraGraph dijkstraGraph) {
        this.graph = dijkstraGraph;
    }

    /**
     * Returns the route for the shortest path.
     * 
     * @return A list of nodes in the path.
     */

    @Override
    public List<City> getRoute(City start, City end) {
        return graph.shortestPathData(start, end);
    }

    /**
     * Returns the miles for each segment in the route.
     * 
     * @return A list of distances for each segment in the path.
     */

    @Override
    public List<Integer> getMilesPerSegment(City start, City end) {
        List<City> airports = graph.shortestPathData(start, end);
        List<Integer> milesPerSegment = new ArrayList<>();

        // Iterate through the list of airports to get miles between each consecutive
        // pair
        for (int i = 0; i < airports.size() - 1; i++) {
            City airport1 = airports.get(i);
            City airport2 = airports.get(i + 1);

            // Use getEdge method to get miles between airport1 and airport2
            Integer miles = (Integer) graph.getEdge(airport1, airport2);

            // Add the miles to the list
            milesPerSegment.add(miles);
        }

        return milesPerSegment;
    }

    /**
     * @return The total miles of the path.
     */

    @Override
    public int getTotalMiles(City start, City end) {
        List<Integer> milesPerSegment = getMilesPerSegment(start, end);
        int totalMiles = 0;

        for (int i = 0; i < milesPerSegment.size(); i++) {
            totalMiles += milesPerSegment.get(i);
        }

        return totalMiles;
    }
}