package edu.stulb.rentalcar.model.listing;

import java.util.ArrayList;

import edu.stulb.rentalcar.database.Firebase;
import edu.stulb.rentalcar.database.IDatabase;

/**
 * Singleton ListingHandler class to handle Listings
 */
public class ListingHandler {
    public static final ListingHandler instance = new ListingHandler();
    private ArrayList<Listing> listings = new ArrayList<>();

    private IDatabase database = Firebase.getInstance();


    /**
     * Private constructor to limit instances of the class
     */
    public ListingHandler(){

    }


    /**
     * Getter of the single instance of this class
     * @return ListingHandler instance
     */
    public static ListingHandler getInstance(){
        return instance;
    }

    /**
     * Method to create a Listing and adds it to listings
     * @param product a Product which Vehicle & Car extends from
     * @param userEmail email of user creating the listing
     * @param reservation Reservation of the listing
     * @return boolean if successful otherwise false
     */
    public boolean createCarListing(Product product, String userEmail, Reservation reservation){
        Listing listing = new Listing(product, userEmail, reservation);
        database.createListing(listing);
        return true;
        //Have not implemented a case where method fails.
    }


    /**
     * Gets the list of Listings in the instance
     * @return ArrayList<String>
     */
    public ArrayList<Listing> getListings(){
        update();
        return listings;
    }

    /**
     * Method to update reservation (dates available) for a given Listing
     * @param listing a Listing
     * @param reservation a Reservation
     * @return true if successful, false otherwise
     */
    public boolean updateListingReservation(Listing listing, Reservation reservation){
        listings.remove(listing);
        Listing updatedListing = new Listing(listing.getProduct(), listing.getUserEmail(), reservation, listing.getUid());
        database.createListing(updatedListing);
        listings.add(updatedListing);

        return true;
        //Have not implemented a case where method fails.
    }


    /**
     * Getter for a Reservation given a Listing id
     * @param listingId String of Listings unique identifier
     * @return ArrayList<Long> for the given listing
     */
    public ArrayList<Long> getListingReservation(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                return (ArrayList<Long>) listing.getReservation().getReservationsDatesList();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Getter for the listings price per day
     * @param listingId String of Listings unique identifier
     * @return int of the listings price per day
     */
    public int getListingPricePerDay(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                return listing.getProduct().getPricePerDay();
            }
        }
        return 0;
    }

    /**
     * Getter for car title given a listing uid
     * @param listingId String of Listings unique identifier
     * @return String of cars title
     */
    public String getCarTitle(String listingId){
        for (Listing listing: listings) {
            if (listing.getUid().equals(listingId)){
                return listing.getProduct().getName();
            }
        }
        return "";
    }

    /**
     * Getter for a Listing given a listing uid
     * @param uid String of Listings unique identifier
     * @return the Listing that was asked for with uid.
     */
    public Listing getListingFromUid(String uid){
        update();
        for (Listing listing:listings) {
            if (listing.getUid().equals(uid)){
                return listing;
            }
        }
        return null;
    }

    /**
     * Getter for listings belonging to a certain User.
     * @param userEmail email of the User
     * @return ArrayList<Listing> for that User
     */
    public ArrayList<Listing> getUserListings(String userEmail){
        update();
        ArrayList<Listing> usersListing = new ArrayList<>();
        //Nullcatch
        if (userEmail == null){
            return usersListing;
        }
        for (Listing listing: listings) {
            if (listing.getUserEmail().equalsIgnoreCase(userEmail)){
                usersListing.add(listing);
            }
        }
        return usersListing;
    }

    /**
     * Update method, updates the list of listings to the listings stored in firebase
     */
    private void update() {
        listings = database.getListings();
    }

    /**
     * Setter for the list containing all listings
     */
    public void setListings(ArrayList<Listing> listings){
        this.listings = listings;
    }
}
