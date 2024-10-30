// --== CS400 File Header Information ==--
// Name: Devagya Gupta
// Email: dgupta52@wisc.edu
// Group and Team: Flight: F10
// Group TA: ANVAY GROVER
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     * 
     * @param map the map that the graph uses to map a data object to the node
     *            object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // Check if start or end nodes are null
        if (start == null || end == null) {
            throw new NullPointerException("Start or end node is null");
        }

        // Check if start or end nodes are not in the graph
        if (!this.nodes.containsKey(start) || !this.nodes.containsKey(end)) {
            throw new NoSuchElementException("Start or end node is not in the graph");
        }

        // Initialize a priority queue for node exploration
        PriorityQueue<SearchNode> queue = new PriorityQueue<>();

        // Initialize a map to keep track of visited nodes and their costs
        MapADT<NodeType, Double> costs = new PlaceholderMap<>();

        // Add the start node to the queue with a cost of 0 and no predecessor
        queue.add(new SearchNode(this.nodes.get(start), 0, null));

        // Loop until the queue is empty
        while (!queue.isEmpty()) {
            // Get the node with the lowest cost from the priority queue
            SearchNode currentNode = queue.poll();
            NodeType currentNodeType = currentNode.node.data;

            // Check if we have reached the end node
            if (currentNodeType.equals(end)) {
                return currentNode; // Found the shortest path to the end node
            }

            // Skip processing if this node was visited with a lower cost already
            if (costs.containsKey(currentNodeType) && costs.get(currentNodeType) <= currentNode.cost) {
                continue;
            }

            // Mark the current node as visited with its cost
            costs.put(currentNodeType, currentNode.cost);

            // Explore neighboring nodes
            for (Edge edge : currentNode.node.edgesLeaving) {
                NodeType successor = edge.successor.data;
                double newCost = currentNode.cost + edge.data.doubleValue();

                // Check if a shorter path to the successor node is found
                if (!costs.containsKey(successor) || newCost < costs.get(successor)) {
                    // Add the successor node to the queue with the updated cost and predecessor
                    queue.add(new SearchNode(edge.successor, newCost, currentNode));
                }
            }
        }

        // If no path is found, throw an exception
        throw new NoSuchElementException("No path from " + start + " to " + end);
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        // TODO: implement in step 5.4

        // Check if start or end nodes are null
        if (start == null || end == null) {
            throw new NullPointerException("Start or end node is null");
        }

        // Compute the shortest path using Dijkstra's algorithm
        SearchNode endNode = computeShortestPath(start, end); // This method is your Dijkstra's implementation
        List<NodeType> path = new LinkedList<>();

        // Trace back from end node to start node to build the path
        for (SearchNode node = endNode; node != null; node = node.predecessor) {
            path.add(0, node.node.data); // Insert at the beginning of the list
        }

        return path; // Return the list of data items representing the shortest path
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // TODO: implement in step 5.4

        // Check if start or end nodes are null
        if (start == null || end == null) {
            throw new NullPointerException("Start or end node is null");
        }

        // Compute the shortest path using Dijkstra's algorithm
        SearchNode endNode = computeShortestPath(start, end); // Your Dijkstra's implementation

        // Return the cost of the shortest path, assuming 'cost' is the attribute for
        // path cost in SearchNode
        return endNode.cost;
    }

    // TODO: implement 3+ tests in step 4.1

    private DijkstraGraph<String, Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new DijkstraGraph<>(new PlaceholderMap<>());
        graph.insertNode("H");
        graph.insertNode("B");
        graph.insertNode("M");
        graph.insertNode("F");
        graph.insertNode("A");
        graph.insertNode("E");
        graph.insertNode("I");
        graph.insertNode("D");
        graph.insertNode("L");
        graph.insertNode("G");

        graph.insertEdge("H", "B", 6);
        graph.insertEdge("B", "M", 3);
        graph.insertEdge("M", "F", 4);
        graph.insertEdge("F", "G", 9);
        graph.insertEdge("G", "L", 7);
        graph.insertEdge("I", "L", 5);
        graph.insertEdge("I", "D", 1);
        graph.insertEdge("D", "A", 7);
        graph.insertEdge("A", "H", 8);
        graph.insertEdge("H", "I", 2);
        graph.insertEdge("I", "H", 2);
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "M", 5);
        graph.insertEdge("M", "E", 3);
        graph.insertEdge("D", "G", 2);
    }

    @Test
    public void exampleTrace() {
        ArrayList<String> pathData = new ArrayList<>();
        DijkstraGraph<String, Integer>.SearchNode current = graph.computeShortestPath("D", "M");

        while (current != null) {
            pathData.add(0, current.node.data);
            current = current.predecessor;
        }

        Assertions.assertEquals("[D, A, B, M]", pathData.toString());
    }

    @Test
    public void checkCostSequence() {
        SearchNode current = (SearchNode) graph.computeShortestPath("D", "B");
        Assertions.assertEquals(8, current.cost);
    }

    @Test
    public void pathNoSequence() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            graph.computeShortestPath("F", "E");
        });
    }
}
