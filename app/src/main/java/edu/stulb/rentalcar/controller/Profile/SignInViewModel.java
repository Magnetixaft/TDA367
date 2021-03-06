package edu.stulb.rentalcar.controller.Profile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.Profile.ProfileFragment;
import edu.stulb.rentalcar.view.Profile.SignUpFragment;

public class SignInViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final UserHandler userHandler = UserHandler.getInstance();

    public SignInViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public boolean isUserLoggedIn(){
        return userHandler.isUserSignedIn();
    }

    public void loadSignupFragment(FragmentManager fragmentManager){
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }

    public void loadProfileFragment(FragmentManager fragmentManager){
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    public boolean signIn(String email, String password){
        return userHandler.signIn(email, password);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
