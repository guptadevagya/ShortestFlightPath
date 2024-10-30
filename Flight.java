class Flight {
    private City startAirport;
    private City destinationAirport;
    private int distance;

    /**
     * Constructor for Flight.
     * 
     * @param startAirport
     * @param destinationAirport
     * @param distance
     */
    public Flight(City startAirport, City destinationAirport, int distance) {
        this.startAirport = startAirport;
        this.destinationAirport = destinationAirport;
        this.distance = distance;
    }

    // Getters
    public City getStartAirport() {
        return startAirport;
    }

    public City getDestinationAirport() {
        return destinationAirport;
    }

    public int getDistance() {
        return distance;
    }

    // toString method
    @Override
    public String toString() {
        return "Flight from " + startAirport + " to " + destinationAirport + " covering " + distance + " miles";
    }
}