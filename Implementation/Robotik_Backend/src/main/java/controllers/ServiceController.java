package controllers;

import data.Component;
import data.Seller;
import data.User;
import data.databases.SellerDb;
import data.databases.UserDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Controller defines core methods for services.
 */
public class ServiceController extends Controller{

    private data.databases.UserDb UserDb = new UserDb("src/main/resources/Json/Users.json");
    private data.databases.SellerDb SellerDb = new SellerDb("src/main/resources/Json/Sellers.json");

    /**
     * Wipes the data from the User and Seller databases.
     */
    public void wipeData(){
        SellerDb.clear();
        UserDb.clear();
    }

    /**
     * Adds a User object to the User database.
     *
     * @param e The User object to be added.
     */
    public void add(User e) {
        UserDb.add(e);
    }

    /**
     * Removes a User object to the User database.
     *
     * @param e The User object to be added.
     */
    public void removeUser(User e){
        UserDb.remove(e);
    }

    /**
     * Updates a User object in the User database.
     *
     * @param e The User object to be updated.
     */
    public void update(User e) {
        UserDb.remove(e);
        UserDb.add(e);
    }

    /**
     * Adds a Seller object to the Seller database.
     *
     * @param e The Seller object to be added.
     */
    public void add(Seller e) {
        SellerDb.add(e);
    }


    /**
     * Updates a Seller object in the Seller database.
     * The existing Seller object is removed and then the updated object is added.
     *
     * @param e The Seller object to be updated.
     */

    public void update(Seller e) {
        SellerDb.remove(e);
        SellerDb.add(e);
    }

    /**
     * Retrieves the list of all users from the User database.
     *
     * @return A List containing all User objects stored in the database.
     */
    public List<User> getUsers() {
        return UserDb.read();
    }

    /**
     * Retrieves the list of all sellers from the Seller database.
     *
     * @return A List containing all Seller objects stored in the database.
     */
    public List<Seller> getSellers() {
        return SellerDb.read();
    }


    /**
     * Searches for a user in the User database by their pseudo (username).
     *
     * @param pseudo The pseudo (username) of the user to search for.
     * @return The User object with the specified pseudo if found, or null if not found.
     */
    public User queryUser(String pseudo) {
        for (User user : UserDb.read()) {
            if (user.getName().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Searches for a user in the list of followers of a specific user.
     *
     * @param user   The User object representing the specific user whose followers are searched.
     * @param pseudo The pseudo (username) of the user to search for among the followers.
     * @return The User object with the specified pseudo if found among the followers, or null if not found.
     */
    public User queryUser(User user, String pseudo) {
        List<String> userFollowers = user.getFollowers();
        for (String follower : userFollowers) {
            if (follower.equals(pseudo)) {
                return null;
            }
        }
        return null;
    }

    /**
     * Searches for a seller in the Seller database based on the specified factor and choice.
     *
     * @param factor The factor to search for (e.g., name, address, or types of components).
     * @param choice The choice determining the search criteria (e.g., "1" for name, "2" for address).
     * @return The Seller object matching the specified factor and choice, or null if not found.
     */
    public Seller querySeller(String factor, String choice) {
        switch (choice) {
            // by name
            case "1":
                for (Seller seller : getSellers()) {
                    if (seller.getName().equals(factor)) {
                        return seller;
                    }
                }
                // by address
            case "2":
                for (Seller seller : getSellers()) {
                    if (seller.getEmail().equals(factor)) {
                        return seller;
                    }
                }
        }
        return null;
    }

    /**
     * Displays the profile information of a Seller.
     * This method prints the name of the Seller and the names of the components listed
     * by the Seller to the console.
     *
     * @param seller The Seller object whose profile information is to be displayed.
     */
    public void viewProfile(Seller seller) {
        System.out.println(seller.getName() + "\n");
        System.out.println("Listed components: ");
        for (Component component : seller.getComponents()) {
            System.out.println(component.getName());
        }

    }

    /**
     * Searches for components in the Seller database based on the specified factor and choice.
     *
     * @param factor The factor to search for (e.g., name of the component, type, or name of the supplier).
     * @param choice The choice determining the search criteria (e.g., "1" for name of the supplier, "2" for name of the component, "3" for type of the component).
     * @return A List of Component objects matching the specified factor and choice, or null if no components are found.
     */
    public List<Component> queryComponent(String factor, String choice) {
        switch (choice) {
            // par nom de fournisseur
            case "1":
                for (Seller seller : getSellers()) {
                    if (seller.getName().equals(factor)) {
                        return seller.getComponents();
                    }
                }
                // par nom de la composante
            case "2":
                List<Component> matchingNameComponents = new ArrayList<>();
                for (Seller seller : getSellers()) {
                    List<Component> components = seller.getComponents();
                    for (Component component : components) {
                        if (component.getName() == factor) {
                            matchingNameComponents.add(component);
                        }
                    }
                }
                return matchingNameComponents;
            // par type de la composante
            case "3":
                List<Component> matchingTypeComponents = new ArrayList<>();
                for (Seller seller : getSellers()) {
                    List<Component> components = seller.getComponents();
                    for (Component component : components) {
                        if (component.getType().equals(factor)) {
                            matchingTypeComponents.add(component);
                        }
                    }
                }
                return matchingTypeComponents;
        }

        return null;
    }

    /**
     * Displays the profile information of a User.
     * This method prints the pseudo, followers, following, points, and interests
     * of the specified User to the console. This information is not adapted for
     * graphical user interfaces (GUIs).
     *
     * @param user The User object whose profile information is to be displayed.
     */
    public void viewProfile(User user) {
        System.out.println("nom: " + user.getName() + "\n");

        System.out.println("followers: ");
        for (String follower : user.getFollowers()) {
            System.out.println(follower);
        }
        System.out.println("\n");

        System.out.println("following: ");
        for (String following : user.getFollowing()) {
            System.out.println(following);
        }
        System.out.println("\n");

        System.out.println(user.getPoints() + "\n");
        for (String interest : user.getInterests()) {
            System.out.println(interest);
        }
    }
}
