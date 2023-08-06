package views;

import controllers.ServiceController;
import controllers.ValidationController;
import data.Action;
import data.Robot;
import data.Tache;
import data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The TaskView class is responsible for handling user interactions related to tasks.
 * It allows users to create, modify, and assign tasks to robots.
 */
public class TaskView {
    ServiceController serviceController;
    User currUser;
    ValidationController validationController = new ValidationController();

    /**
     * Constructs a new TaskView object with the provided User and ServiceController instances.
     *
     * @param currUser The currently logged-in User for whom tasks are managed.
     * @param serviceController The ServiceController for data manipulation.
     */
    public TaskView(User currUser, ServiceController serviceController){
        this.serviceController = serviceController;
        this.currUser = currUser;
    }

    /**
     * Creates a new task by taking input from the user for task name, actions, and schedule.
     * The task is then associated with the current user and added to their task list.
     */
    public void createTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez nommer cette tache:");
        String name = scanner.nextLine();
        ArrayList<Action> taskActions = new ArrayList<>();

        int choice = 1;
        while (choice == 1){
            System.out.println("Quelle action voulez vous assigner cette tache ?");
            List<Action> actions = currUser.getActions();
            for (int i = 0; i < actions.size(); i++) {
                System.out.println("Option " + i + ":");
                System.out.println("    " + actions.get(i).getName());
                System.out.println("    type: " + actions.get(i).getType());
                if(actions.get(i).getType().equals("mouvement")){
                    double[] vector = actions.get(i).getVector();
                    System.out.println("        x: " + vector[0]);
                    System.out.println("        y: " + vector[1]);
                    System.out.println("        z: " + vector[2]);
                }
                else{
                    System.out.println("        " + actions.get(i).getValue());
                }
            }
            choice = validationController.takeValidInput(actions.size()-1);
            taskActions.add(actions.get(choice));
            System.out.println("Souhaitez-vous ajouter une autre action a cette tache ?\n" +
                        "(Les actions seront executee une apres l'autre)"
                    );
            System.out.println("Option 1: Oui");
            System.out.println("Option 0: Non");
            choice = validationController.takeValidInput(1);
        }

        System.out.println("A quelle heure cette tache doit etre executee (HH/MM)?");
        String time = scanner.nextLine();
        System.out.println("Cette tache doit elle etre executee tous les jours ?");
        System.out.println("Option 1: Oui");
        System.out.println("Option 0: Non");
        choice = validationController.takeValidInput(1);
        boolean repeats = false;
        if(choice == 1){
            repeats = true;
        }
        Tache newTask = new Tache(name, time, taskActions, repeats);
        currUser.getTasks().add(newTask);
        serviceController.update(currUser);


        System.out.println("Done !");
    }


    /**
     * Modifies an existing task selected by the user.
     * The user can choose to add/remove actions or change the schedule of the task.
     */
    public void modifyTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle tache souhaitez vous modifier ?");
        for (int i = 0; i < currUser.getTasks().size(); i++) {
            System.out.println("Option " + i + ":");
            System.out.println("    Nom: " + currUser.getTasks().get(i).getName());
            System.out.println("    Continuelle: " + currUser.getTasks().get(i).getRepeats());
            System.out.println("    Heure: " + currUser.getTasks().get(i).getTimeFormat());
        }
        int choice = validationController.takeValidInput(currUser.getTasks().size()-1);
        Tache tache = currUser.getTasks().get(choice);
        System.out.println("Souhaitez vous:\n0: Retirer/ajouter une action\n1: Changer l'heure ou la repetition ?");
        choice = validationController.takeValidInput(1);
        if(choice == 0){
            System.out.println("Souhaitez vous:\n0: ajouter une action\n1: retirer une action ?");
            choice = validationController.takeValidInput(1);
            if(choice == 0){
                System.out.println("Quelle action voulez vous ajouter cette tache ?");
                List<Action> actions = currUser.getActions();
                for (int i = 0; i < actions.size(); i++) {
                    System.out.println("Option " + i + ":");
                    System.out.println(actions.get(i).getName());
                }
                choice = validationController.takeValidInput(actions.size()-1);
                tache.getActions().add(currUser.getActions().get(choice));
            }
            else {
                System.out.println("Quelle action voulez vous retirer ?");
                List<Action> actions = tache.getActions();
                for (int i = 0; i < actions.size(); i++) {
                    System.out.println("Option " + i + ":");
                    System.out.println("    " + actions.get(i).getName());
                    System.out.println("    " + actions.get(i).getValue());
                }
                choice = validationController.takeValidInput(actions.size()-1);
                tache.getActions().remove(choice);
            }
        }
        else {

            System.out.println("A quelle heure cette tache doit etre executee (HH/MM)?");
            String time = scanner.nextLine();
            System.out.println("Cette tache doit elle etre executee tous les jours ?");
            System.out.println("Option 1: Oui");
            System.out.println("Option 0: Non");
            choice = validationController.takeValidInput(1);
            boolean repeats = false;
            if(choice == 1){
                repeats = true;
            }
            tache.setReapats(repeats);
            tache.setTimeFormat(time);
        }
        System.out.println("Done !");
        serviceController.update(currUser);
    }


    /**
     * Assigns a task to a selected robot from the current user's robot list.
     * The user can choose the task to assign from their task list.
     */
    public void assignTask(){
        System.out.println("Pour quel robot voulez-vous assigner une tache ?");
        for (int i = 0; i < currUser.getRobots().size(); i++) {
            System.out.println(i + "       " + currUser.getRobots().get(i).name);
        }
        int robotChoice  = validationController.takeValidInput(currUser.getRobots().size());
        Robot robot = currUser.getRobots().get(robotChoice);

        System.out.println("Quelle tache assigner a ce robot ?");
        List<Tache> actions = currUser.getTasks();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("Option " + i + ":");
            System.out.println(actions.get(i).getName());
        }
        int choice = validationController.takeValidInput(actions.size()-1);
        robot.tasks.add(currUser.getTasks().get(choice));
        serviceController.update(currUser);
        System.out.println("Done !");
    }

}
