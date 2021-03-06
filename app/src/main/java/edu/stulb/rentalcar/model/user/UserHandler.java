package edu.stulb.rentalcar.model.user;

import java.util.ArrayList;

import edu.stulb.rentalcar.database.Firebase;
import edu.stulb.rentalcar.database.IDatabase;

/**
 * Singleton UserHandler class to handle currentUser and user sign in
 */
public class UserHandler {
    private static final UserHandler instance = new UserHandler();
    private boolean isUserSignedIn = false;
    private User currentUser;
    private ArrayList<User> users = new ArrayList<>();

    private IDatabase database = Firebase.getInstance();

    /**
     * Private constructor to limit instances of the class
     */
    private UserHandler() {
    }


    /**
     * Getter of the single instance of this class
     * @return UserHandler instance
     */
    public static UserHandler getInstance() {
        return instance;
    }


    /**
     *
     * @param name name of the User
     * @param email email of the User
     * @param password password of the User
     * @param card Card with card information of the User
     * @return boolean, True if creation was successful otherwise False
     */
    public boolean createUser(String name, String email, String password, Card card){
        if (users.size()>0) {
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    return false;
                }
            }
        }

        User user = new User(name, email, password, card);
        database.createUser(user);
        return true;
    }


    /**
     * @param email sign in email
     * @param password sign in password
     * @return boolean returns True if successful sign in otherwise False
     */
    public boolean signIn(String email, String password) {
        update();
        if (!isUserSignedIn) {
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                    currentUser = user;
                    isUserSignedIn = true;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Signs user out if user was signed in
     */
    public void signOut(){
        if (isUserSignedIn){
            isUserSignedIn = false;
            currentUser = null;
        }
    }


    /**
     * getter for current User (user that is signed in)
     * @return current User
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * true or false value if user is signed in or not
     * @return boolean
     */
    public boolean isUserSignedIn() {
        return isUserSignedIn;
    }


    /**
     * Getter for the list containing all Users
     * @return ArrayList containing Users
     */
    public ArrayList<User> getUsers() {
        update();
        return users;
    }

    /**
     * Setter for the list containing all Users
     * @param users list containing users
     */
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    /**
     * Update method, updates the list of users to the users stored in firebase
     */
    private void update() {
        users = database.getUsers();
    }
}
