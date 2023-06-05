
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

        System.out.println("Voulez vous: \n1. Retourner au menu principal\n2. Quitter l'application");
        switch (controller.choice(2)){
            case "1":
                run();
                break;
            case "2":
                System.exit(0);
                break;
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
        System.out.println("Veuillez entrer les informations suivantes sur le robot:");
        System.out.print("Numéro de série:");
        String number = scanner.next();

        System.out.print("Nom du robot:");
        String name = scanner.next();

        System.out.print("Type de robot:");
        scanner.next();

        System.out.println("Le robot \""+ name + "\" avec numéro "+ number +" a été enregistré avec succès.");
    }

    private void robotInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez saisir le nom du robot dont vous voulez voir les informations:");
        String name = scanner.next();
        System.out.println("Voici les informations sur "+name+":");
        System.out.println("Position: (155.12, 245.4, 32.0)\nVitesse: 7.5km/h\nBatterie: 67%\nConsommation CPU: 84%");
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
        System.out.println("En premier lieu, achetez un CPU de Robotix, puis achetez les autres components plus tard");
        System.out.println("Choisissez la generation de CPU:");
        int gen = 0;
        System.out.println("1. Gen 1 1ghz\n2. Gen 2 2ghz\n Gen 3 3ghz\n" +
                "4. Gen 4 4ghz\nGen 5 5ghz");

        switch (controller.choice(5)) {
            case "1" -> type = "Gen 1";
            case "2" -> type = "Gen 2";
            case "3" -> type = "Gen 3";
            case "4" -> type = "Gen 4";
            case "5" -> type = "Gen 5";
        }
        System.out.println("Choisissez le nom de votre Robot:");
        String name = scanner.nextLine();

        Robot robot = new Robot(type, name);
        robot.addPart(type, "Robotix");
        System.out.println("Robot cree !");
        nosRobots.add(robot);

    }

    private void robotInfo() {
        System.out.println("Voici l'information de nos robots:");
        for (int i = 0; i < nosRobots.size(); i++) {
            System.out.print(nosRobots.get(i).name + "   ");
            System.out.println(nosRobots.get(i).getUUID());
            System.out.println(nosRobots.get(i).type);
            System.out.println("Composantes:");
            for (int j = 0; j < nosRobots.get(i).getParts().size(); j++) {
                System.out.println(nosRobots.get(i).getParts().get(j));
            }

        }
        this.run();
    }

    private void buyComponents() {
        System.out.println("Pour quel robot voulez vous acheter un component ?");
        for (int i = 0; i < nosRobots.size(); i++) {
            System.out.println(String.valueOf(i) + "       " + nosRobots.get(i).name);
        }
        Scanner scanner = new Scanner(System.in);
        String wtv = scanner.nextLine();
        int choice = 0;
        try {
            choice = Integer.parseInt(wtv);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (choice >= nosRobots.size()) {
            System.out.println("Choix invalide");
            this.buyComponents();
        }
        System.out.println("Ecrire le nom du component a ajouter");
        wtv = scanner.nextLine();
        nosRobots.get(choice).addPart(wtv, "Robotix");
        System.out.println("Done !");
        this.run();
    }

    private void createAction() {
        System.out.println("Choisissez la nouvelle position x y z de votre robot");
        this.run();
    }

    private void interact() {
        // TODO
        // assigner tâches, participer à des activités
        this.run();
    }
}