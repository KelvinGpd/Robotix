package controllers;


import data.databases.ActivityDb;
import data.databases.SellerDb;
import data.databases.UserDb;


/**
 * The abstract Controller class serves as a base class for specific controllers in the application.
 * It provides access to different database objects for managing users, sellers, and activities.
 */
abstract class Controller {

    protected UserDb UserDb = new UserDb("src/main/resources/Json/Users.json");
    protected SellerDb SellerDb = new SellerDb("src/main/resources/Json/Sellers.json");
    protected ActivityDb ActivityDb = new ActivityDb("src/main/resources/Json/Activties.json");


}