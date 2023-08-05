package views;

import controllers.Controller;
import controllers.ValidationController;
import data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RobotManagerView {
    ValidationController validationController = new ValidationController();
    Controller controller;
    User currUser;
    public RobotManagerView(User currUser, Controller controller){
        this.controller = controller;
        this.currUser = currUser;
    }
    /**
     * Method to display information about the user's robots.
     * It lists the details of each robot, including its name, type, and components.
     */
    public void robotInfo() {
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
                System.out.println("      " + task.getName());
                System.out.println("        Continuelle: " + task.getRepeats());
                System.out.println("        Heure: " + task.getTimeFormat());
            }
        }
    }
    /**
     * Method to add a new robot to the user's fleet.
     * The user is prompted to enter information about the new robot, such as its type, name, and CPU generation.
     * A new robot instance is created and added to the user's robot list.
     */
    public void addRobotToFleet() {
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
        robot.addPart("Cpu gen " + gen, "Robotix");

        currUser.addRobot(robot);
        controller.update(currUser);
        System.out.println("Robot cree !");
    }
    /**
     * Method to allow the user to buy components for their robots.
     * It prompts the user to select a robot from their fleet and then choose a component to purchase from the market.
     * The purchased component is added to the selected robot's parts list.
     */
    public void buyComponents() {
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
}
