
//UI for user
import java.util.ArrayList;
import java.util.Scanner;

import data.Robot;
import data.User;

public class UserView {
    Controller controller;
    ArrayList<String> fournisseurs = new ArrayList<String>();
    ArrayList<Robot> nosRobots = new ArrayList<Robot>();
    ArrayList<String> actions = new ArrayList<String>();
    ArrayList<Integer> movement = new ArrayList<Integer>();

    private User currUser;

    public UserView(Controller controller) {
        this.controller = controller;
        fournisseurs.add("Fournisseur A");
        fournisseurs.add("Fournisseur B");
        fournisseurs.add("Fournisseur C");
        fournisseurs.add("Fournisseur D");
        fournisseurs.add("Robotix");
    }

    public void run(boolean isNew) {
        User user;
        if (isNew) {
            user = register();
            controller.add(user);
        } else {
            String email, password;
            System.out.print("Entrez votre email: ");
            Scanner scanner = new Scanner(System.in);
            email = scanner.nextLine();
            System.out.print("Entrez votre password: ");
            password = scanner.nextLine();
            user = controller.authenticateUser(email, password);
            if (user == null) {
                System.out.println("Password ou email incorrect!");
                return;
            }
        }
        currUser = user;
        while (true) {
            System.out.println("Bienvenue à Robotix " + user.getName() + ". Veuillez choisir une option:");
            System.out.println("1. Ajouter un robot\n2. Informations sur vos robots\n 3. Acheter des composantes\n" +
                    "4. Créer/modifier un action\n5. Participer a une activite\n" + //
                    "6. Creer/modifier une tache");
            switch (controller.choice(6)) {
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
                case "6":
                    creerTache();
                    ;
                    break;
            }

            System.out.println("Voulez vous: \n1. Retourner au menu principal\n2. Quitter l'application");
            switch (controller.choice(2)) {
                case "1":
                    continue;
                case "2":
                    System.exit(0);
                    break;
            }
        }
    }

    private User register() {
        // boilerplate code copié, question de sauver du temps
        Scanner scanner = new Scanner(System.in);

        // Prompt for email
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();

        // Check email with regex
        if (!email.matches("(?i)^[A-Z0-9+_.-]+@[A-Z0-9.-]+$")) {
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
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        // Check phone number with regex
        if (!phoneNumber.matches("^[0-9]{10}$")) {
            System.out.println("Format invalide !");
            return this.register();
        }

        System.out.print("Choisissez un mot de passe: ");
        String password = scanner.nextLine();

        User user = new User(email, username, phoneNumber, password);

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
        System.out.println("En premier lieu, achetez un CPU de Robotix, puis achetez les autres components plus tard");
        System.out.println("Choisissez la generation de CPU:");
        int gen = 0;
        System.out.println("1. Gen 1 1ghz\n2. Gen 2 2ghz\n3. Gen 3 3ghz\n" +
                "4. Gen 4 4ghz\n5. Gen 5 5ghz");

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
    }

    private void creerTache() {
        System.out.println("Pour quel robot voulez creer une tache ?");
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
        System.out.println("Quelle action voulez vous assigner a cette tache ?");
        for (int i = 0; i < actions.size() + (movement.size() / 3); i++) {
            if (i < actions.size()) {
                System.out.println("Option " + i + ":");
                System.out.println(actions.get(i));
            } else {
                int j = i - actions.size();
                System.out.println("Option " + i + ":");
                System.out.println(
                        "Movement" + "x: " + movement.get(j * 3) + " y: " + movement.get(j * 3 + 1) + " z: "
                                + movement.get(j * 3 + 2));
            }
        }
        wtv = scanner.nextLine();
        int choice2 = 0;
        try {
            choice2 = Integer.parseInt(wtv);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (choice2 >= actions.size() + (movement.size() / 3)) {
            System.out.println("Choix invalide");
            this.buyComponents();
        }

        if (choice2 >= actions.size()) {

            if (movement.get(((choice - actions.size()) * 3) + 2) != 0) {
                System.out.println("Assurez vous que le robot a une helice.");
            }

        } else {
            System.out.println("Assurez vous que le robot puisse display ou jouer votre path.");
            nosRobots.get(choice2);

            nosRobots.get(choice).taches.add(actions.get(choice2));
        }

        System.out.println("Done !");
    }

    private void createAction() {

        System.out.println("1: Creer une action\n 2:Modifier une action");

        switch (controller.choice(2)) {
            case "1":
                wtvwtv();
                break;
            case "2":
                for (int i = 0; i < actions.size() + (movement.size() / 3); i++) {
                    if (i < actions.size()) {
                        System.out.println("Option " + i + ":");
                        System.out.println(actions.get(i));
                    } else {
                        int j = i - actions.size();
                        System.out.println("Option " + i + ":");
                        System.out.println(
                                "Movement" + "x: " + movement.get(j * 3) + " y: " + movement.get(j * 3 + 1) + " z: "
                                        + movement.get(j * 3 + 2));
                    }
                }
                System.out.println("Trop long a implementer la modification, pas le point du cour");
                break;
        }

    }

    public void wtvwtv() {
        System.out.println("Choisissez quel type d'action vous voulez créer, pour ensuite la parmetrer:");
        System.out.println("1. Movement\n2. Jouer un son/musique \n3. afficher quelque chose \n");
        Scanner scanner = new Scanner(System.in);
        switch (controller.choice(3)) {
            case "1":
                try {
                    System.out.println("La mesure des distances est en cm (nombre entier)");
                    System.out.println("Choisissez la direction du vecteur en x: (peut etre negatif)");
                    movement.add(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Choisissez la direction du vecteur en y: (peut etre negatif)");
                    movement.add(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Choisissez la direction du vecteur en z: (robot doit avoir helice)");
                    movement.add(Integer.parseInt(scanner.nextLine()));
                    System.out.println("action cree !");
                } catch (Exception e) {
                    System.out.println("Utilisez le bon systeme de mesure(Pas de virgules)");
                }
                break;
            case "2":
                String str2 = "Son: ";
                System.out.println("Ecrivez le path vers le son/musique");
                str2 += scanner.nextLine();
                System.out.println("action cree !");
                actions.add(str2);
                break;
            case "3":
                String str3 = "Display: ";
                System.out.println("Ecrivez le path vers le display");
                str3 += scanner.nextLine();
                System.out.println("action cree !");
                actions.add(str3);
                break;
        }
    }

    private void interact() {
        Scanner scanner = new Scanner(System.in);
        // TODO
        // assigner tâches, participer à des activités
        System.out.println("Incrivez la journee ou commence l'activite (DD/MM/YYYY)");
        String aws = scanner.nextLine();
        System.out.println("Incrivez l'heure a laquelle vous voulez faire l'activite (HH:MM)");

        aws = scanner.nextLine();
        System.out.println("Incrivez le lieu ou faire l'activite (adresse sera cherchee sur le google maps api)");
        aws = scanner.nextLine();
        System.out.println("Incrivez la fin de l'activite (DD/MM/YYYY)");
        aws = scanner.nextLine();

        System.out.println("Choisissez l'activite a laquelle participer");
        System.out.println("1: faire un tache avec mes robots\n2: jouer/apprendre/eduquer avec un de mes robots \n");

        switch (controller.choice(2)) {
            case "1":
                System.out.println("Quelle tache voulez vous faire ?");
                scanner.nextLine();
                System.out.println(
                        "Avec quel robot voulez vous faire cette tache ? Assurez vous que le robot puisse faire la tache");
                for (int i = 0; i < nosRobots.size(); i++) {
                    System.out.println(String.valueOf(i) + "       " + nosRobots.get(i).name);
                }
                String wtv = scanner.nextLine();
                int choice = 0;
                try {
                    choice = Integer.parseInt(wtv);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                break;
            case "2":
                System.out.println("Avec quel robot voulez vous faire cette activite ?");
                for (int i = 0; i < nosRobots.size(); i++) {
                    System.out.println(String.valueOf(i) + "       " + nosRobots.get(i).name);
                }
                try {
                    String ok = scanner.nextLine();
                    choice = Integer.parseInt(ok);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                break;
        }
        System.out.println("L'inscription est faite !");
    }
}