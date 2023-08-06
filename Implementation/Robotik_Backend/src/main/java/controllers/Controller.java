package controllers;


import data.databases.ActivityDb;
import data.databases.SellerDb;
import data.databases.UserDb;


abstract class Controller {

    protected UserDb UserDb = new UserDb("src/main/resources/Json/Users.json");
    protected SellerDb SellerDb = new SellerDb("src/main/resources/Json/Sellers.json");
    protected ActivityDb ActivityDb = new ActivityDb("src/main/resources/Json/Activties.json");

    /**
     * Creates a new instance of the Controller class.
     */
    public Controller() {
    }

}