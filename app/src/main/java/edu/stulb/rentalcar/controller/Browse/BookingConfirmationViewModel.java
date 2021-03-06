package edu.stulb.rentalcar.controller.Browse;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.view.Home.HomeFragment;

public class  BookingConfirmationViewModel extends ViewModel {
    ListingHandler listingHandler = ListingHandler.getInstance();
    String daysBooked;
    Listing bookedListing;

    public void getBookedListing(String listingId, String listingBookedDays){
        daysBooked = listingBookedDays;
        bookedListing = listingHandler.getListingFromUid(listingId);
    }
    public String displayBookedDates(){
        return daysBooked;
    }

    public String displayCarTitle(){
        return bookedListing.getProduct().getName();
    }

    public String displayPricePerDay(){
        return String.valueOf(bookedListing.getProduct().getPricePerDay());
    }

    public String displayTotalPrice(int numDaysBooked){
        int totalPrice = bookedListing.getProduct().getPricePerDay()*numDaysBooked;
        return String.valueOf(totalPrice);
    }
    public void loadHomeFragment(FragmentManager fragmentManager){
        Fragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();
    }

}