package views;

import controllers.Controller;
import controllers.ValidationController;
import data.Action;
import data.Tache;
import data.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ActionView {
    Controller controller;
    User currUser;
    public ActionView(User currUser, Controller controller){
        this.controller = controller;
        this.currUser = currUser;
    }
    ValidationController validationController = new ValidationController();
    /**
     * Method to modify an existing action's tasks based on user input.
     * It allows the user to choose an action and then select a task to modify or replace with a new one.
     */
    public void modifyAction() {
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
        //Tache task = new Tache(scanner.nextLine());

//        tasks.remove(taskChoice);
//        tasks.add(taskChoice, task);


        System.out.println("done!");
    }

    /**
     * Method to create a new action based on user input.
     * It prompts the user to choose a type for the action and enter a name for the action.
     * The new action is created and added to the user's action list.
     */
    public void createAction() {
        ArrayList<String> valableTypes = new ArrayList<>(Arrays.asList("mouvement", "son", "affichage"));

        System.out.println("Choisissez quel type d'action vous voulez créer, voici une liste exhaustive de types...");
        System.out.println("Les options sont: mouvement, son, affichage");

        String type = validationController.validateActionType(valableTypes);

        Scanner scanner = new Scanner(System.in);
        System.out.println("donnez un nom a votre action");

        String name = scanner.nextLine();

        Action action = new Action(type, name);
        currUser.add(action);
        controller.update(currUser);

        System.out.println("action cree!");
    }
}
