
//UI for user
import java.util.ArrayList;
import java.util.Scanner;

public class UserView {
    Controller controller;
    ArrayList<String> fournisseurs = new ArrayList<String>();
    ArrayList<Robot> nosRobots = new ArrayList<Robot>();

    public UserView() {
        controller = new Controller();
        fournisseurs.add("Fournisseur A");
        fournisseurs.add("Fournisseur B");
        fournisseurs.add("Fournisseur C");
        fournisseurs.add("Fournisseur D");
        fournisseurs.add("Robotix");
    }

    public void run() {
        User user = register();

        System.out.println("Bienvenue à Robotix " + user.getUsername() + ". Veuillez choisir une option:");
        System.out.println("1. Ajouter un robot\n2. Informations sur vos robots\n 3. Acheter des composantes\n" +
                "4. Créer un action\n5. Interagir avec vos robots");
        switch (controller.choice(5)) {
            case "1" -> addRobot();
            case "2" -> robotInfo();
            case "3" -> buyComponents();
            case "4" -> createAction();
            case "5" -> interact();
        }
    }

    private User register() {
        // boilerplate code copié, question de sauver du temps
        Scanner scanner = new Scanner(System.in);

        // Prompt for email
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();

        // Check email with regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Format de email invalide !");
            return this.register();
        }
        System.out.print("Entrez votre nom d'usager: ");
        String username = scanner.nextLine();

        // Check username with regex
        if (!username.matches("^[A-Za-z0-9_]+$")) {
            System.out.println("Format d'usager invalide !");
            return this.register();
        }

        // Prompt for phone number
        System.out.print("Entrez votre numero de telephone: ");
        String phoneNumber = scanner.nextLine();

        // Check phone number with regex
        if (!phoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Format invalide !");
            return this.register();
        }
        User user = new User(email, username, phoneNumber);
        return user;
    }

    private void addRobot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel type de robot voulez vous ?");
        System.out.println("1. Amusement\n2. Controlleur\n 3. Ouvrier\n" +
                "4. Manager\n5. Drone");
        String type = "";
        switch (controller.choice(5)) {
            case "1" -> type = "Amusement";
            case "2" -> type = "Controlleur";
            case "3" -> type = "Ouvrier";
            case "4" -> type = "Manager";
            case "5" -> type = "Drone";
        }
        System.out.println("En premier lieu, achetez un CPU, puis achetez les autres components plus tard");

        Robot robot = new Robot(type);

    }

    private void robotInfo() {
        // TODO
        // voir état, métriques
    }

    private void buyComponents() {
        // TODO
        // trouver fournisseur, acheter composantes
    }

    private void createAction() {
        // TODO
        // créer actions, activités
    }

    private void interact() {
        // TODO
        // assigner tâches, participer à des activités
    }
}