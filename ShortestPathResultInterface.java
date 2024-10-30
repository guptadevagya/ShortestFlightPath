// --== CS400 File Header Information ==--
// Name: Devagya Gupta
// Email: dgupta52@wisc.edu
// Group and Team: Flight: F10
// Group TA: ANVAY GROVER
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

public interface ShortestPathResultInterface {
    /**
     * Getter for the route as a list of airports along the route.
     *
     * @return List of airport names representing the shortest route.
     */
    List<City> getRoute(City start, City end);

    /**
     * Getter for a list of miles to travel for each segment of the route.
     *
     * @return List of integers where each element represents the miles to travel
     *         for a route segment.
     */
    List<Integer> getMilesPerSegment(City start, City end);

    /**
     * Getter for the total miles for the route.
     *
     * @return Total miles to travel for the shortest route.
     */
    int getTotalMiles(City start, City end);
}
