package edu.stulb.rentalcar.controller.Browse;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;

import java.util.ArrayList;

import edu.stulb.rentalcar.model.listing.DateHandler;
import edu.stulb.rentalcar.model.listing.Listing;
import edu.stulb.rentalcar.model.listing.ListingHandler;
import edu.stulb.rentalcar.model.listing.Reservation;
import edu.stulb.rentalcar.view.Browse.BookingConfirmationFragment;

public class DateSelectorViewModel extends ViewModel {
    DateHandler dateHandler = new DateHandler();
    ListingHandler listingHandler = ListingHandler.getInstance();
    ArrayList<Long> clickedDatesList = new ArrayList<>();
    String listingId;
    ArrayList<Long> listingsReservedDates;

    public void clickedDate(int year, int month, int dayOfMonth, Context context){
        Long clickedDate = dateHandler.convertToMillis(dateHandler.formatDate(year, month, dayOfMonth));
        if (!listingsReservedDates.contains(clickedDate)){
            if (clickedDatesList.contains(clickedDate)){
                clickedDatesList.remove(clickedDate);
                makeToast("removed date", context);
            } else {
                clickedDatesList.add(clickedDate);
            }
        } if (listingsReservedDates.contains(clickedDate)){
            makeToast("Date unavailable", context);
        }
    }
    public String displayClickedDates(){
        return dateHandler.getClickedDatesString(clickedDatesList);
    }

    public void confirmBooking(){
        ArrayList<Long> updatedList= new ArrayList<>();
        updatedList.addAll(listingsReservedDates);
        updatedList.addAll(clickedDatesList);
        Reservation reservation = new Reservation(updatedList);
        listingHandler.updateListingReservation(getCurrentListing(), reservation);
    }
    private Listing getCurrentListing(){
        for (Listing listing:listingHandler.getListings()) {
            if (listing.getUid().equals(listingId)){
                return listing;
            }
        }
        return null;
    }

    public void getCurrentUid(String uid){
        listingId = uid;
        listingsReservedDates = ListingHandler.getInstance().getListingReservation(listingId);
    }

    public String getTotalCost(){
        return String.valueOf(listingHandler.getListingPricePerDay(listingId)*clickedDatesList.size());
    }

    public Boolean isListEmpty(){
        return clickedDatesList.isEmpty();

    }

    public String getCarTitle(){
        return listingHandler.getCarTitle(listingId);
    }

    public void loadBookingConfirmationFragment(FragmentManager fragmentManager, String uid, String bookedDates){
        Bundle args = new Bundle();
        args.putString("listingId", uid);
        args.putString("listingBookedDays", bookedDates);
        args.putInt("numDaysBooked", clickedDatesList.size());
        Fragment bookingConfirmationFragment = new BookingConfirmationFragment();
        bookingConfirmationFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, bookingConfirmationFragment).commit();
    }

    public void makeToast(CharSequence message, Context context) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
