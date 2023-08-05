package views;//UI for user

import controllers.Controller;
import controllers.ValidationController;
import data.*;

import java.util.*;

/**
 * The user interface for regular users in the Robotix application.
 * This class allows users to register, login, and perform various actions related to their robots and activities.
 */
public class UserView {
    //user
    private User currUser;

    //controllers
    Controller controller;
    ValidationController validationController;

    /**
     * Constructor to create a new views.UserView instance with the specified controller.
     *
     * @param controller The Controller instance used for communication with the data and business logic.
     */
    public UserView(Controller controller) {
        this.controller = controller;
        this.validationController = new ValidationController();
    }


    /**
     * Method to handle the user login process.
     * It prompts the user to enter their email and password, and then attempts to authenticate the user.
     * If the login is successful, the user is assigned to the `currUser` variable for further actions.
     */

    public void login() {

        System.out.print("Entrez votre email: ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.print("Entrez votre password: ");
        String password = scanner.nextLine();
        try {
            currUser = controller.authenticateUser(email, password);
            if (currUser == null) {
                System.out.println("Password ou email incorrect!");
                return;
            }
        }
        catch (Exception e){

        }
    }

    /**
     * Method to run the user view, allowing users to register, login, and perform various actions.
     * This method provides a menu for users to choose between different actions.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("register, or login?");
        String choice = scanner.nextLine();

        switch (choice) {
            case "register" : register();
                break;
            case "login" : login();
                break;
            default: run();
        }

        while (true && (this.currUser != null)) {
            System.out.println("Bienvenue à Robotix " + currUser.getName() + ". Veuillez choisir une option:");
            System.out.println("0. Ajouter un robot\n1. Informations sur vos robots\n2. Acheter des composantes\n" +
                    "3. Créer/modifier un action\n4. Participer/creer une activite\n" + //
                    "5. Creer/modifier une tache");
            try {
                switch (validationController.takeValidInput(5)) {
                    case 0:
                        addRobotToFleet();
                        break;
                    case 1:
                        robotInfo();
                        break;
                    case 2:
                        buyComponents();
                        break;
                    case 3:
                        manageActions();
                        break;
                    case 4:
                        interact();
                        break;
                    case 5:
                        manageTasks();
                        ;
                        break;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Le format de votre input n'est pas le bon !");
            }
            catch (FormatterClosedException e){
                System.out.println("Non !");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("Voulez vous: \n0. Retourner au menu principal\n1. Quitter l'application");
            switch (validationController.takeValidInput(1)) {
                case 0:
                    continue;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Method to handle the user registration process.
     * It prompts the user to enter their email, username, phone number, and password,
     * and then creates a new user instance based on the provided information.
     * The new user is added to the system through the controller.
     */
    private void register() {
        Scanner scanner = new Scanner(System.in);

        // Prompt for email
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();

        // Check email with regex
        if (!validationController.validateEmail(email)) {
            System.out.println("Format de email invalide !");
            register();
        }

        //prompt for username
        System.out.print("Entrez votre nom d'usager: ");
        String username = scanner.nextLine();

        // Check username with regex
        if (!validationController.validateUsername(username)) {
            System.out.println("Format d'usager invalide !");
            register();
        }

        // Prompt for phone number
        System.out.print("Entrez votre numero de telephone: ");
        String phoneNumber = scanner.nextLine();
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");

        // Check phone number with regex
        if (!validationController.validatePhonenum(phoneNumber)) {
            System.out.println("Format invalide !");
            register();
        }

        System.out.print("Choisissez un mot de passe: ");
        String password = scanner.nextLine();

        User user = new User(email, username, phoneNumber, password);
        currUser = user;


        controller.add(user);
    }


    /**
     * Method to add a new robot to the user's fleet.
     * The user is prompted to enter information about the new robot, such as its type, name, and CPU generation.
     * A new robot instance is created and added to the user's robot list.
     */
    private void addRobotToFleet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel type de robot rajoutez vous ?");
        System.out.println("0. Amusement\n1. Controlleur\n 2. Ouvrier\n" +
                "3. Manager\n4. Drone");
        String type = "";
        switch (validationController.takeValidInput(4)) {
            case 0 -> type = "Amusement";
            case 1 -> type = "Controlleur";
            case 2 -> type = "Ouvrier";
            case 3 -> type = "Manager";
            case 4 -> type = "Drone";
        }

        System.out.println("Choisissez la generation de CPU:");
        int gen = 0;
        System.out.println("0. Gen 1 1ghz\n1. Gen 2 2ghz\n2. Gen 3 3ghz\n" +
                "3. Gen 4 4ghz\n4. Gen 5 5ghz");

        switch (validationController.takeValidInput(4)) {
            case 0 -> gen = 0;
            case 1 -> gen = 1;
            case 2 -> gen = 2;
            case 3 -> gen = 3;
            case 4 -> gen = 4;
        }
        System.out.println("Choisissez le nom de votre Robot:");
        String name = scanner.nextLine();

        Robot robot = new Robot(type, name);
        robot.addPart(type, "Robotix");

        currUser.addRobot(robot);
        controller.update(currUser);
        System.out.println("Robot cree !");
    }


    /**
     * Method to display information about the user's robots.
     * It lists the details of each robot, including its name, type, and components.
     */
    private void robotInfo() {
        System.out.println("Voici l'information de vos robots:");
        for (Robot robot : currUser.getRobots()) {
            System.out.println("Le robot:" + robot.name + "   ");
            System.out.println("    uuid: " + robot.getUUID());
            System.out.println("    type: " + robot.type);
            System.out.println("    " + "Composantes: ");
            for (Robot.Pair<String, String> pair  : robot.getParts()) {
                System.out.print("      " + pair.getKey());
                System.out.println("    Fournisseur:" + pair.getValue());
            }
            System.out.println("    taches courantes:");
            for (Tache task  : robot.tasks) {
                System.out.print("      " + task.getName());
                System.out.println("    Continuelle: " + task.getRepeats());
                System.out.println("    Heure: " + task.getTimeFormat());
            }
        }
    }


    /**
     * Method to allow the user to buy components for their robots.
     * It prompts the user to select a robot from their fleet and then choose a component to purchase from the market.
     * The purchased component is added to the selected robot's parts list.
     */
    private void buyComponents() {
        System.out.println("Pour quel robot voulez vous acheter une composante ?");
        for (int i = 0; i < currUser.getRobots().size(); i++) {
            System.out.println(i + "       " + currUser.getRobots().get(i).name);
        }
        int robotChoice  = validationController.takeValidInput(currUser.getRobots().size());

        System.out.println("voici une liste de composantes sur le marche: ");

        Map<Integer, Component> componentMap = new HashMap<>();

        int i = 0;
        for (Seller seller : controller.getSellers()) {
            ArrayList<Component> components = seller.getComponents();
            for (Component component : components) {
                System.out.println(i + "." + component.getName() + " " + component.getType());
                componentMap.put(i, component);
                i++;
            }
        }
        if(controller.getSellers().size() == 0){
            System.out.println("Il n'y a aucun vendeur pour l'instant.");
        }
        else{
            int componentChoice = validationController.takeValidInput(i);
            Component selectedComponent = componentMap.get(componentChoice);

            currUser.getRobots().get(robotChoice).addPart(selectedComponent.getName(), selectedComponent.getType());
            controller.update(currUser);
        }


        System.out.println("Done !");
    }



    /**
     * Method to create a new task and associate it with one of the user's actions.
     * The user is prompted to enter the task description, and then the task is added to the chosen action's task list.
     */
    private void manageTasks(){
        System.out.println("0: Creer une tache\n1:Modifier une tache\n2:Assigner une tache");
        TaskView actionView = new TaskView(currUser, controller);

        switch (validationController.takeValidInput(2)) {
            case 0:
                actionView.createTask();
                break;
            case 1:
                actionView.modifyTask();
                break;
            case 2:
                actionView.assignTask();
                break;
        }
    }



    /**
     * Method to manage the user's actions, including creating new actions and modifying existing ones.
     * It provides options to create a new action or modify an existing one by changing its tasks.
     */
    private void manageActions() {

        System.out.println("0: Creer une action\n1:Modifier une action");
        ActionView actionView = new ActionView(currUser, controller);

        switch (validationController.takeValidInput(1)) {
            case 0:
               actionView.createAction();
               break;
            case 1:
                actionView.modifyAction();
                break;
        }
    }

    /**
     * Method to allow the user to participate in or create activities.
     * It provides options to participate in existing activities or create new activities.
     */
    private void interact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("voulez vous 1: participer a une activite, 2:creer une activite ?");
        ActivityView activityView = new ActivityView(currUser, controller);

        switch (validationController.takeValidInput(1)) {
            case 0 :
                activityView.participateActivity();
                break;
            case 1 :
                activityView.createActivity();
                break;
        }
        System.out.println("Choisissez l'activite a laquelle participer");
        System.out.println("1: faire un tache avec mes robots\n2: jouer/apprendre/eduquer avec un de mes robots \n");
    }

}