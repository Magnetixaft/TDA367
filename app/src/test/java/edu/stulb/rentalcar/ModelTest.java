package edu.stulb.rentalcar;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.stulb.rentalcar.model.Car;
import edu.stulb.rentalcar.model.CarManufacturer;
import edu.stulb.rentalcar.model.Card;
import edu.stulb.rentalcar.model.Listing;
import edu.stulb.rentalcar.model.Location;
import edu.stulb.rentalcar.model.Reservation;
import edu.stulb.rentalcar.model.User;

public class ModelTest {

    @Test
    public void carCreationIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Honda");
        Car car = new Car("Civic", carManufacturer, 2021);
        assertEquals(car.getCarModel(), "Civic");
        assertEquals(car.getCarYear(), 2021);
        assertEquals(car.getCarManufacturer().getManufacturer(), "Honda");
    }
    @Test
    public void locationIsCorrect(){
        Location location = new Location("Göteborg");
        assertEquals(location.getCity(), "Göteborg");
    }
    @Test
    public void reservationIsCorrect() {
        Reservation reservation = new Reservation();
        List<Long> testList = new ArrayList<>();
        assertEquals(reservation.getReservationsDatesList(), testList);
    }
    @Test
    public void listingIsCorrect(){
        CarManufacturer carManufacturer = new CarManufacturer("Volvo");
        Car car = new Car("v90", carManufacturer, 2005);
        Location location = new Location("Göteborg");
        Card card = new Card("Hannes Thörn", "5355830012341234", "11/25", "111");
        User user = new User("Hannes", "Hannes@gmail.com", card);
        Listing listing = new Listing(car, 200, location, user);
        assertEquals(listing.getCar().getCarModel(), "Volvo");
    }

}