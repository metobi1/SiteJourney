package sitejourney;

public class Location {

    private String name;
    private int tFare;
    private int fRate;

    public Location(String name, int tFare, int fRate) {
        this.name = name;
        this.tFare = tFare;
        this.fRate = fRate;
    }

    public String getName() {
        return name;
    }

    public int getFare() {
        return tFare;
    }

    public int getRate() {
        return fRate;
    }

    @Override
    public String toString() {
        return String.format("Location name: %s", name);
    }
}