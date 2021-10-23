package edu.stulb.rentalcar.controller;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import com.google.firebase.auth.FirebaseUser;

import edu.stulb.rentalcar.model.FirebaseHandler;
import edu.stulb.rentalcar.view.AddCarAdFragment;
import edu.stulb.rentalcar.view.DashboardFragment;
import edu.stulb.rentalcar.view.SignInFragment;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseHandler firebaseHandler = new FirebaseHandler();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Homepage for featured images.");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void loadAddCarFragment(FragmentManager fragmentManager){
        Fragment addCarFragment = new AddCarAdFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarFragment).commit();
    }

    public void loadDashboardFragment(FragmentManager fragmentManager){
        Fragment dashboardFragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
    }


    public void loadSignInFragment(FragmentManager fragmentManager){
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }
}