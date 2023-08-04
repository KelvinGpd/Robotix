import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.*;
import data.databases.SellerDb;
import data.databases.UserDb;

public class Controller {

    private UserDb UserDb = new UserDb("src/main/resources/Json/Users.json");

    private SellerDb SellerDb = new SellerDb("src/main/resources/Json/Sellers.json");

    public Controller() {
        tests();
    }

    public void tests(){
        User testUser = new User("a","Preuve qu'on recover le user","a","a");
        testUser.addFollower("RandomMf");
        Robot randomRobot = new Robot("Executionner", "Top g");
        testUser.add(randomRobot);
        this.add(testUser);
        User myClient = this.authenticateUser("a", "a");
        System.out.println(myClient.getName());




        Component[] components = { new Component("HyperRollers", "wheels",
                "Légère et élégante, notre roue en alliage améliore les performances et l'efficacité de votre véhicule.",
                199),
                new Component("AMD 7600X", "cpu",
                        "Puissance de traitement exceptionnelle, permettant des opérations rapides et fluides.", 350) };
        this.add(new Seller("Companie A", "1234 street", "company@email.com", "111-1111-1111", components, "qwerty"));

        Component[] components2 = { new Component("RapidLift 2.0", "propeller",
                "Efficacité maximale et une propulsion optimale, garantissant une vitesse et une maniabilité exceptionnelles.",
                95),
                new Component("Energizer", "batteries", "Une batterie longue durée avec une autonomie exceptionnelle",
                        45),
                new Component("Shockwave V", "speakers",
                        "Haut-parleur sans fil qui offre une connectivité sans encombrement", 89) };
        this.add(new Seller("Companie B", "5678 street", "company2@email.com", "222-2222-2222", components2, "qwerty"));
        Component[] components3 = { new Component("Ultragrip 4", "arms",
                "bras robotique polyvalent conçu pour s'adapter à une large gamme d'applications industrielles", 60),
                new Component("VisonLens HD", "camera", "Caméra HD offrant une résolution haute définition", 210) };
        this.add(new Seller("Companie C", "12313 street", "companysdasd@email.com", "333-1111-1111", components3,
                "qwerty"));
        Component[] components4 = {
                new Component("ImageColor", "screen",
                        "Une qualité d'image exceptionnelle avec une netteté et des détails époustouflants", 99),
                new Component("RealSound 1665-E", "speaker", "Haut-parleur compact et facilement transportable.", 70) };
        this.add(new Seller("Companie D", "1234123 street", "company222@email.com", "333-4567-1111", components4,
                "qwerty"));
        Component[] components5 = {
                new Component("OmniCapture 3", "micro",
                        "une qualité audio exceptionnelle, capturant des sons clairs et détaillés", 50),
                new Component("Intel i7 8th Gen", "cpu", "CPU conçu pour une efficacité énergétique optimale.", 199) };
        this.add(new Seller("Companie E", "1234 street", "company@email.com", "111-1111-1111", components5, "qwerty"));

        this.add(new User("randomUser@email.com", "randomUser1", "111-1111-1111", "qwerty"));
        this.add(new User("randomUser2@email.com", "randomUser2", "121-1111-1111", "qwerty"));
        this.add(new User("randomUser3@email.com", "randomUser3", "131-1111-1111", "qwerty"));
        this.add(new User("randomUser4@email.com", "randomUser4", "141-1111-1111", "qwerty"));
        this.add(new User("randomUser5@email.com", "randomUser5", "151-1111-1111", "qwerty"));
        this.add(new User("randomUser6@email.com", "randomUser6", "161-1111-1111", "qwerty"));
        this.add(new User("randomUser7@email.com", "randomUser7", "171-1111-1111", "qwerty"));
        this.add(new User("randomUser8@email.com", "randomUser8", "181-1111-1111", "qwerty"));
        this.add(new User("randomUser9@email.com", "randomUser9", "191-1111-1111", "qwerty"));
        this.add(new User("randomUser10@email.com", "randomUser10", "221-1111-1111", "qwerty"));

        Seller sell = this.authenticateSeller("company@email.com", "qwerty");
        System.out.println(sell.getName());
        sell.setName("Le nom a bel et bien ete changé");
        this.update(sell);
        System.out.println(sell.getName());


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
}