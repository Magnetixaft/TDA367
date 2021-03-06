package edu.stulb.rentalcar.controller.Profile;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tda367.R;
import edu.stulb.rentalcar.model.user.Card;
import edu.stulb.rentalcar.model.user.UserHandler;
import edu.stulb.rentalcar.view.Browse.AddListingFragment;
import edu.stulb.rentalcar.view.Profile.ProfileFragment;
import edu.stulb.rentalcar.view.Profile.SignInFragment;
import edu.stulb.rentalcar.view.Profile.SignUpFragment;

public class SignUpViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final UserHandler userHandler = UserHandler.getInstance();

    public SignUpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is signup fragment");
    }

    public boolean isUserSignedIn() {
        return userHandler.isUserSignedIn();
    }

    public boolean checkPasswordsEqual(String passwordOne, String passwordTwo) {
        return passwordOne.equals(passwordTwo);
    }

    public boolean checkPasswordLength(String passwordOne, String passwordTwo) {
        return (passwordOne.length() > 7) ||
                (passwordTwo.length() > 7);
    }

    public boolean checkEmailInput(String email) {
        return email.contains("@");
    }

    public boolean checkCvvLength(String cvv) {
        return cvv.length() > 2;
    }

    public boolean checkCardLength(String card) {
        return card.length() > 15;
    }

    public boolean checkDateInput(String date) {
        String regex = "[0-9]";
        return date.matches(regex + regex + "/" + regex + regex);
    }

    public void loadProfileFragment(FragmentManager fragmentManager) {
        Fragment profileFragment = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, profileFragment).commit();
    }

    public void loadSignInFragment(FragmentManager fragmentManager) {
        Fragment signInFragment = new SignInFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signInFragment).commit();
    }

    public void addCarAdFragment(FragmentManager fragmentManager) {
        Fragment addCarAdFragment = new AddListingFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, addCarAdFragment).commit();
    }

    public void loadSignUpFragment(FragmentManager fragmentManager) {
        Fragment signUpFragment = new SignUpFragment();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, signUpFragment).commit();
    }

    public void createUser(String email, String password, String name, String cardNumber, String cardName, String cardDate, String cardCVV) {
        Card card = new Card(cardName, cardNumber, cardDate, cardCVV);
        userHandler.createUser(name, email, password, card);
    }
}
