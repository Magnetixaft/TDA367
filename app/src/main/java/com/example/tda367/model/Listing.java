package com.example.tda367.model;

/**
 *  Listing is a class to represent a listing of a Car.
 *  @author Josef Ngo
 */
public class Listing {
    private Car car;
    private int pricePerDay;
    private Location location;
    private User user;

    public Listing(Car car, int pricePerDay, Location location, User user) {
        this.car = car;
        this.pricePerDay = pricePerDay;
        this.location = location;
        this.user = user;
    }

    /**
     * Getter for Listings car.
     * @return the Car in the listing.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Getter for listings pricePerDay
     * @return an int for pricePerDay
     */
    public int getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Getter for listings location
     * @return a Location that represent location for listing.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for listings user
     * @return the User that created the listing.
     */
    public User getUser() {
        return user;
    }
}
