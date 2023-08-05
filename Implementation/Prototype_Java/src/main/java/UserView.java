//UI for user

import controllers.Controller;
import controllers.ValidationController;
import data.*;

import java.util.*;

import static java.lang.Integer.parseInt;

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
     * Constructor to create a new UserView instance with the specified controller.
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
            System.out.println("0. Ajouter un robot\n1. Informations sur vos robots\n 2. Acheter des composantes\n" +
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
                        creerTache();
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

        controller.removeUser(currUser);

        currUser.addRobot(robot);
        System.out.println("Robot cree !");

        controller.add(currUser);
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
            System.out.println("   type: " + robot.type);
            System.out.println("    " + "Composantes: ");
            for (Robot.Pair<String, String> pair  : robot.getParts()) {
                System.out.println("    " + pair.getKey());
                System.out.println("    " + pair.getValue());
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

        int componentChoice = validationController.takeValidInput(i);
        Component selectedComponent = componentMap.get(componentChoice);

        currUser.getRobots().get(robotChoice).addPart(selectedComponent.getName(), selectedComponent.getType());

        System.out.println("Done !");
    }



    /**
     * Method to create a new task and associate it with one of the user's actions.
     * The user is prompted to enter the task description, and then the task is added to the chosen action's task list.
     */
    private void creerTache() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("veuillez entrer une tache");
        String task = scanner.nextLine();

        System.out.println("A quelle action voulez vous assigner cette tache ?");
        List<Action> actions = currUser.getActions();
        for (int i = 0; i < currUser.getActions().size(); i++) {
            System.out.println("Option " + i + ": " + actions.get(i));
        }
        int choice = validationController.takeValidInput(actions.size());

        Tache tache = new Tache(task);
        actions.get(choice).addTask(tache);

        System.out.println("Done !");
    }


    /**
     * Method to manage the user's actions, including creating new actions and modifying existing ones.
     * It provides options to create a new action or modify an existing one by changing its tasks.
     */
    private void manageActions() {

        System.out.println("0: Creer une action\n 1:Modifier une action");

        switch (validationController.takeValidInput(1)) {
            case 0:
                createAction();
            case 1:
                modifyAction();
        }
    }

    /**
     * Method to modify an existing action's tasks based on user input.
     * It allows the user to choose an action and then select a task to modify or replace with a new one.
     */
    private void modifyAction() {
        System.out.println("selectionnez une de vos actions a modifier: ");
        List<Action> actions = currUser.getActions();
        for (int i = 0; i < actions.size(); i++) {
                System.out.println("Option " + i + ":");
                System.out.println(actions.get(i).getName());
            }

        int choice = validationController.takeValidInput(actions.size()-1);

        List<Tache> tasks = actions.get(choice).getTasks();

        System.out.println("selectionner une tache a modifier: ");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("Option " + i + ":");
            System.out.println(tasks.get(i));
        }

        int taskChoice = validationController.takeValidInput(tasks.size());

        System.out.println("rentrer votre nouvelle tache: ");
        Scanner scanner = new Scanner(System.in);
        Tache task = new Tache(scanner.nextLine());

        tasks.remove(taskChoice);
        tasks.add(taskChoice, task);

        System.out.println("done!");
    }

    /**
     * Method to create a new action based on user input.
     * It prompts the user to choose a type for the action and enter a name for the action.
     * The new action is created and added to the user's action list.
     */
    private void createAction() {
        ArrayList<String> valableTypes = new ArrayList<>(Arrays.asList("mouvement", "son", "affichage"));

        System.out.println("Choisissez quel type d'action vous voulez créer, voici une liste exhaustive de types...");
        System.out.println("Les options sont: mouvement, son, affichage");

        String type = validationController.validateActionType(valableTypes);

        Scanner scanner = new Scanner(System.in);
        System.out.println("donnez un nom a votre action");

        String name = scanner.nextLine();

        Action action = new Action(type, name);
        currUser.add(action);
        System.out.println("action cree!");
    }


    /**
     * Method to allow the user to participate in or create activities.
     * It provides options to participate in existing activities or create new activities.
     */
    private void interact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("voulez vous 1: participer a une activite, 2:creer une activite ?");

        switch (scanner.nextLine()) {
            case "1" : participateActivity();
            case "2" : createActivity();
        }
        System.out.println("Choisissez l'activite a laquelle participer");
        System.out.println("1: faire un tache avec mes robots\n2: jouer/apprendre/eduquer avec un de mes robots \n");
    }



    /**
     * Method to allow the user to participate in an existing activity.
     * It prompts the user to choose an activity to participate in, and then adds the user to the activity's participants list.
     */
    private void participateActivity() {
        System.out.println("veuillez choisir une activte a laquelle participer");
        List<Activity> activities = controller.getActvities();
        for(int i = 0; i < activities.size(); i++) {
            System.out.println("Option " + i + ":");
            activities.get(i).getActivityinfo();
            System.out.println();
        }

        int choice = validationController.takeValidInput(activities.size());
        activities.get(choice).participate(currUser);

        System.out.println("inscription reussie!");

    }

    /**
     * Method to create a new activity based on user input.
     * It prompts the user to enter information about the new activity, such as name, description, interests, and points.
     * The new activity is created and added to the system through the controller.
     */
    private void createActivity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Donnez un nom a votre activite");
        String name = scanner.nextLine();

        System.out.println("Donnez une description a votre activite");
        String description = scanner.nextLine();

        System.out.println("Donnez une liste d'interets associable a votre activite");
        String[] interests = scanner.nextLine().split(",");

        System.out.println("Incrivez la journee ou commence l'activite (DD/MM/YYYY)");
        String startDate = scanner.nextLine();

        System.out.println("Incrivez la fin de l'activite (DD/MM/YYYY)");
        String endDate = scanner.nextLine();

        System.out.println("combien de points seront attribuable a la completion de votre activite?");
        int points = parseInt(scanner.nextLine());

        Activity activity = new Activity(name, description, interests, startDate, endDate, points);

        controller.add(activity);
        currUser.addActivity(activity);
        controller.update(currUser);

        System.out.println("l'activite a ete cree.");
    }
}