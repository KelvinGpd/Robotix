
//UI for user
import java.util.Scanner;

public class UserView {
    Controller controller;

    public UserView() {
        controller = new Controller();
    }

    public void run() {
        User user = register();

        System.out.println("Bienvenue à Robotix " + user.getUsername() + ". Veuillez choisir une option:");
        System.out.println("1. Ajouter un robot\n2. Informations sur vos robots\n 3. Acheter des composantes\n" +
                "4. Créer un action\n5. Interagir avec vos robots");
        switch (controller.choice(5)) {
            case "1":
                addRobot();
                break;
            case "2":
                robotInfo();
                break;
            case "3":
                buyComponents();
                break;
            case "4":
                createAction();
                break;
            case "5":
                interact();
                break;
        }
    }

    private User register() {
        // boilerplate code copié, question de sauver du temps
        Scanner scanner = new Scanner(System.in);

        // Prompt for email
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Check email with regex
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Invalid email format!");
            return this.register();
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Check username with regex
        if (!username.matches("^[A-Za-z0-9_]+$")) {
            System.out.println("Invalid username format!");
            return this.register();
        }

        // Prompt for phone number
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        // Check phone number with regex
        if (!phoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Invalid phone number format!");
            return this.register();
        }
        User user = new User(email, username, phoneNumber);
        return user;
    }

    private void addRobot() {
        // TODO
        // ajouter robot à la flotte
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