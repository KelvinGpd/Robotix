package views;

import controllers.Controller;
import controllers.ValidationController;
import data.Action;
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("selectionnez une de vos actions a modifier / delete: ");
        List<Action> actions = currUser.getActions();
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("Option " + i + ":");
            System.out.println(actions.get(i).getName());
        }

        int choice = validationController.takeValidInput(actions.size()-1);
        Action action = currUser.getActions().get(choice);
        System.out.println("Souhaitez vous:\n0: modifier\n1: delete cette action ?");
        int choice2 = validationController.takeValidInput(1);
        if(choice2 == 1){
            currUser.getActions().remove(choice);
        }
        else {
            System.out.println(action.getType());
            if (action.getType().equals("mouvement")) {
                System.out.println("Choisissez la nouvelle direction du movement comme un vecteur:");
                System.out.println("Ecrivez la composante en x: (accepte les points)");
                String x = scanner.nextLine();
                System.out.println("Ecrivez la composante en y: (accepte les points)");
                String y = scanner.nextLine();
                System.out.println("Ecrivez la composante en x: (accepte les points)");
                String z = scanner.nextLine();
                try{
                    double xd = Double.parseDouble(x);
                    double yd = Double.parseDouble(x);
                    double zd = Double.parseDouble(x);
                    action.getVector()[0] = xd;
                    action.getVector()[1] = yd;
                    action.getVector()[2] = zd;
                }
                catch (Exception e){
                    System.out.println("Pas le bon format, par exemple nous voulons 2.1");
                }
            } else {
                System.out.println("Ecrivez le nouveau path du fichier que vous voulez utiliser");
                String value = scanner.nextLine();
                action.setValue(value);
            }
        }
        System.out.println("done!");
        controller.update(currUser);
    }

    /**
     * Method to create a new action based on user input.
     * It prompts the user to choose a type for the action and enter a name for the action.
     * The new action is created and added to the user's action list.
     */
    public void createAction() {
        ArrayList<String> valableTypes = new ArrayList<>(Arrays.asList("mouvement", "son", "affichage"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez quel type d'action vous voulez créer, voici une liste exhaustive de types...");
        System.out.println("Les options sont: mouvement, son, affichage");

        String value = "";
        double[] vector = {0,0,0};
        boolean fail = false;
        String type = validationController.validateActionType(valableTypes);
        switch (type){
            case "mouvement":
                System.out.println("Choisissez la direction du movement comme un vecteur:");
                System.out.println("Ecrivez la composante en x: (accepte les points)");
                String x = scanner.nextLine();
                System.out.println("Ecrivez la composante en y: (accepte les points)");
                String y = scanner.nextLine();
                System.out.println("Ecrivez la composante en x: (accepte les points)");
                String z = scanner.nextLine();
                try{
                    double xd = Double.parseDouble(x);
                    double yd = Double.parseDouble(x);
                    double zd = Double.parseDouble(x);
                    vector[0] = xd;
                    vector[1] = yd;
                    vector[2] = zd;
                }
                catch (Exception e){
                    System.out.println("Pas le bon format, par exemple nous voulons 2.1");
                    fail = true;
                }
                break;
            default:
                System.out.println("Ecrivez le path du fichier que vous voulez utiliser");
                value = scanner.nextLine();

        }

        if(!fail){
            System.out.println("donnez un nom a votre action");
            String name = scanner.nextLine();

            Action action = new Action(type, name, value, vector);
            currUser.add(action);
            controller.update(currUser);

            System.out.println("action cree!");
        }

    }
}
