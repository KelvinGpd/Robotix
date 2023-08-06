package controllers;

import data.Seller;
import data.User;


/**
 * Login Controller defines core methods for Client authentification.
 */
public class LoginController extends Controller {


    /**
     * Authenticates a Seller using the provided email and password.
     * This method attempts to log in a Seller using the provided email and password
     * by calling the login method of the Seller database.
     *
     * @param email    The email of the Seller to be authenticated.
     * @param password The password of the Seller to be authenticated.
     * @return The authenticated Seller object, or null if authentication fails.
     */
    public Seller authenticateSeller(String email, String password) {
        return (Seller) SellerDb.login(email, password);
    }


    /**
     * Authenticates a User using the provided email and password.
     * This method attempts to log in a User using the provided email and password
     * by calling the login method of the User database.
     *
     * @param email    The email of the User to be authenticated.
     * @param password The password of the User to be authenticated.
     * @return The authenticated User object, or null if authentication fails.
     */
    public User authenticateUser(String email, String password) {
        return (User) UserDb.login(email,password);
    }

}
