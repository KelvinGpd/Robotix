package views;

import controllers.Controller;
import controllers.ValidationController;
import data.Activity;
import data.User;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ActivityView {
    ValidationController validationController;
    Controller controller;
    User currUser;

    public ActivityView(User currUser, Controller controller){
        this.controller = controller;
        this.currUser = currUser;
    }
    /**
     * Method to allow the user to participate in an existing activity.
     * It prompts the user to choose an activity to participate in, and then adds the user to the activity's participants list.
     */
    public void participateActivity() {
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
    public void createActivity() {
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
