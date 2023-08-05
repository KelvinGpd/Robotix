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
    ValidationController validationController = new ValidationController();

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
     * Method to run the user view, allowing users to register, login, and perform various actions.
     * This method provides a menu for users to choose between different actions.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.register, or 2.login?");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" : register();
                break;
            case "2" : login();
                break;
            default:
                System.out.println("invalid option, please try again");
                run();
        }

        while (true && (this.currUser != null)) {
            System.out.println("Bienvenue à Robotix " + currUser.getName() + ". Veuillez choisir une option:");
            System.out.println("0. Ajouter un robot\n1. Informations sur vos robots\n2. Acheter des composantes\n" +
                    "3. Créer/modifier un action\n4. Participer/creer une activite\n" + //
                    "5. Creer/modifier/assigner une tache");
            RobotManagerView managerView = new RobotManagerView(currUser, controller);
            try {
                switch (validationController.takeValidInput(5)) {
                    case 0:
                        managerView.addRobotToFleet();
                        break;
                    case 1:
                        managerView.robotInfo();
                        break;
                    case 2:
                        managerView.buyComponents();
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
     * Method to handle the user registration process.
     * It prompts the user to enter their email, username, phone number, and password,
     * and then creates a new user instance based on the provided information.
     * The new user is added to the system through the controller.
     */
    private void register() {
        Scanner scanner = new Scanner(System.in);

        // Prompt for email
        System.out.print("Entrez votre email: ");
        String email = validationController.validateEmail(scanner.nextLine());

        //prompt for username
        System.out.print("Entrez votre nom d'usager: ");
        String username = validationController.validateUsername(scanner.nextLine());

        // Prompt for phone number
        System.out.print("Entrez votre numero de telephone: ");
        String phoneNumber = validationController.validatePhonenum(scanner.nextLine());
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");

        System.out.print("Choisissez un mot de passe: ");
        String password = scanner.nextLine();

        User user = new User(email, username, phoneNumber, password);
        currUser = user;

        controller.add(user);
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
        System.out.println("Voulez-vous:\n0: participer a une activite ?\n1:creer une activite ?\n2. Suprimer une activite");
        ActivityView activityView = new ActivityView(currUser, controller);

        switch (validationController.takeValidInput(2)) {
            case 0 :
                activityView.participateActivity();
                break;
            case 1 :
                activityView.createActivity();
                break;
            case 2:
                activityView.cancelActivity();
        }
    }

}