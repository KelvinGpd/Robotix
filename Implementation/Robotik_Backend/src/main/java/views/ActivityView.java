package views;

import controllers.ActionController;
import controllers.ServiceController;
import controllers.ValidationController;
import data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ActivityView {
    ValidationController validationController = new ValidationController();
    ServiceController serviceController;
    ActionController actionController = new ActionController();
    User currUser;

    public ActivityView(User currUser, ServiceController serviceController){
        this.serviceController = serviceController;
        this.actionController = actionController;
        this.currUser = currUser;
    }
    /**
     * Method to allow the user to participate in an existing activity.
     * It prompts the user to choose an activity to participate in, and then adds the user to the activity's participants list.
     */
    public void participateActivity() {
        System.out.println("veuillez choisir une activte a laquelle participer");
        List<Activity> activities = actionController.getActvities();
        for(int i = 0; i < activities.size(); i++) {
            System.out.println("Option " + i + ":");
            activities.get(i).getActivityinfo();
            System.out.println();
        }

        int choice = validationController.takeValidInput(activities.size());
        activities.get(choice).participate(currUser);
        serviceController.update(currUser);

    }

    /**
     * Method to create a new activity based on user input.
     * It prompts the user to enter information about the new activity, such as name, description, interests, and points.
     * The new activity is created and added to the system through the controller.
     */
    public void createActivity() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez l'activite a laquelle participer");
        ArrayList<String> robots = new ArrayList<>();
        System.out.println("0: faire un tache avec mes robots\n1: jouer/apprendre/eduquer avec un de mes robots \n2:autre");
        int choice = validationController.takeValidInput(2);
        if(choice == 0){
            System.out.println("Quelle tache souhaitez vous faire ?");
            for (int i = 0; i < currUser.getTasks().size(); i++) {
                System.out.println("Option " + i + ":");
                System.out.println("    Nom: " + currUser.getTasks().get(i).getName());
                System.out.println("    Continuelle: " + currUser.getTasks().get(i).getRepeats());
                System.out.println("    Heure: " + currUser.getTasks().get(i).getTimeFormat());
            }
            int otherChoice = validationController.takeValidInput(currUser.getTasks().size()-1);
            Tache tache = currUser.getTasks().get(otherChoice);
        }
            System.out.println("Quels robots souhaitez vous impliquer dans cette activite ?");
            ArrayList<Integer> implicatedRobots = new ArrayList<Integer>();
            int yes =0;
            while (yes == 0) {
                for (int i = 0; i < currUser.getRobots().size(); i++) {
                    if(!implicatedRobots.contains(i)){
                        System.out.println(i + "       " + currUser.getRobots().get(i).name);
                    }
                }
                int robotChoice = validationController.takeValidInput(currUser.getRobots().size());
                if(!implicatedRobots.contains(robotChoice)){
                    Robot robot = currUser.getRobots().get(robotChoice);
                    robots.add(robot.name);
                }
                else {
                    System.out.println("Choix impossible, ce robot est deja impliqué");
                }
                implicatedRobots.add(robotChoice);

                if(currUser.getRobots().size() == implicatedRobots.size()){
                    System.out.println("Tous vos robots sont impliqués");
                    break;
                }
                System.out.println("Souhaitez-vous ajouter un autre robot ?\n0:Oui\n1:Non");
                yes = validationController.takeValidInput(1);
            }
        if(choice == 2){
            System.out.println("Indiquer quel type d'activite vous souhaitez faire");
            scanner.nextLine();
        }
        System.out.println("Il n'a aucun conflit d'horaire avec vos robots, yay !");

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

        Activity activity = new Activity(name, description, interests, startDate, endDate, points, (ArrayList<String>) null);

        currUser.addActivity(activity);
        serviceController.update(currUser);

        System.out.println("l'activite est crée.");
    }
    public void cancelActivity(){
        System.out.println("Quelle activite voulez vous canceller ?");
        List<Activity> activities = currUser.getActvities();
        for(int i = 0; i < currUser.getActvities().size(); i++) {
            System.out.println("Option " + i + ":");
            activities.get(i).getActivityinfo();
            System.out.println();
        }
        int choice = validationController.takeValidInput(currUser.getActvities().size() - 1);
        currUser.getActvities().remove(choice);
        serviceController.update(currUser);
        System.out.println("Done !");
    }
}
