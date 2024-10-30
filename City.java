class City {
    private String name;
    private String abbreviation;

    public City(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    // toString method
    @Override
    public String toString() {
        return name + " (" + abbreviation + ")";
    }
}
