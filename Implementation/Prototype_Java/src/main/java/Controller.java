import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.*;
import data.databases.ActivityDb;
import data.databases.SellerDb;
import data.databases.UserDb;

public class Controller {

    private UserDb UserDb = new UserDb("src/main/resources/Json/Users.json");

    private SellerDb SellerDb = new SellerDb("src/main/resources/Json/Sellers.json");

    private ActivityDb activityDb = new ActivityDb("src/main/resources/Json/Activties.json");

    public Controller() {
    }


    public void whipeData(){
        SellerDb.clear();
        UserDb.clear();
    }
    public String choice(int numChoices) {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String choice = reader.next();
            if (isValid(choice, numChoices)) {
                return choice;
            }
            System.out.print("Veuillez saisir un choix valide: ");
        }
    }

    public void add(User e) {
        UserDb.add(e);
    }
    public void update(User e) {
        UserDb.remove(e);
        UserDb.add(e);
    }


    public void add(Seller e) {
        SellerDb.add(e);
    }

    public void update(Seller e) {
        SellerDb.remove(e);
        SellerDb.add(e);
    }

    public Seller authenticateSeller(String email, String password) {
       return (Seller) SellerDb.login(email, password);
    }


    public User authenticateUser(String email, String password) {
        return (User) UserDb.login(email,password);
    }

    private boolean isValid(String input, int max) {
        int n;
        if (input == null) {
            return false;
        }
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return n > 0 && n <= max;
    }

    // fonctionnalites Tache 3

    // Récupérer la liste des utilisateurs
    public List<User> getUsers() {
        return UserDb.read();
    }

    // Rechercher un utilisateur (par pseudo)
    public User queryUser(String pseudo) {
        for (User user : UserDb.read()) {
            if (user.getName().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }

    // Rechercher un utilisateur (parmi la liste des suiveurs d'un utilisateur
    // spécifique)
    public User queryUser(User user, String pseudo) {
        List<String> userFollowers = user.getFollowers();
        for (String follower : userFollowers) {
            if (follower.equals(pseudo)) {
                return null;
            }
        }
        return null;
    }

    // show information: pseudo, followers, following, points, interests,
    // not adapted for gui
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

    // Récupérer la liste des fournisseurs
    public List<Seller> getSellers() {
        return SellerDb.read();
    }

    // Rechercher un fournisseur (par nom, adresse ou types de composantes)
    // using choice ig since all three are Strings
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
                // TODO by component type
            case "3":
                for (Seller seller : SellerDb.read()) {
                    List<Component> components = seller.getComponents();
                }
        }
        return null;
    }

    // Voir le profil d'un fournisseur
    public void viewProfile(Seller seller) {
        System.out.println(seller.getName() + "\n");
        System.out.println("Listed components: ");
        for (Component component : seller.getComponents()) {
            System.out.println(component.getName());
        }

    }

    // Rechercher une composante (par nom de la composante, type ou nom du
    // fournisseur)
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

    public List<Activity> getActvities() {
        return  activityDb.read();
    }
}