package views;//UI for seller

import controllers.Controller;
import controllers.ValidationController;
import data.Component;
import data.Seller;

import java.util.Scanner;

/**
 * The user interface for sellers in the Robotix application.
 * This class allows sellers to register or login, and provides options
 * to sell components, view their listed components, and perform other actions.
 */
public class SellerView {
    ValidationController validationController;
    Controller controller;
    Seller currSeller;
    Scanner scanner;

    /**
     * Constructor to create a new views.SellerView instance with the specified controller.
     *
     * @param controller The Controller instance used for communication with the data and business logic.
     */
    public SellerView(Controller controller) {
        this.controller = controller;
        this.validationController = new ValidationController();
        this.scanner = new Scanner(System.in);
    }


    /**
     * Method to run the seller view, allowing sellers to register, login, and perform actions.
     * This method provides a menu for sellers to choose between different actions.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.register, or 2.login?");

        switch (scanner.nextLine()) {
            case "1" : register();
                break;
            case "2" : login();
                break;
            default:
                System.out.println("invalid option, please try again");
                run();
        }

        while (true && currSeller != null) {
            System.out.println("Bienvenue à Robotix " + currSeller.getName() + ". Veuillez choisir une option:");
            System.out.println("0. Vendre des composantes 1. Voir vos composantes");
            switch (validationController.takeValidInput(1)) {
                case 0:
                    sellComp();
                    break;
                case 1:
                    viewComp();
                    break;
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
     * Method to handle the seller login process.
     * It prompts the user to enter their email and password, and then attempts to authenticate the seller.
     * If the login is successful, the seller is assigned to the `seller` variable for further actions.
     */
    public void login() {

        System.out.print("Entrez votre email: ");

        String email = scanner.nextLine();
        System.out.print("Entrez votre password: ");
        String password = scanner.nextLine();

        currSeller = controller.authenticateSeller(email, password);
        if (currSeller == null) {
            System.out.println("Password ou email incorrect!");
            return;
        }

    }

    /**
     * Method to handle the seller registration process.
     * It prompts the user to enter their name, address, email, phone number, password, and components they produce.
     * A new seller instance is created and returned after successful registration.
     *
     * @return The newly registered Seller instance.
     */
    private void register() {
        String name, address, email, phone, password;
        Component[] components;

        System.out.println("Veuillez saisir les informations suivantes:");

        //username
        System.out.print("Nom: ");
        name = validationController.validateUsername(scanner.nextLine());

        //address
        System.out.print("Addresse: ");
        address = scanner.nextLine();

        System.out.print("Email: ");
        email = validationController.validateEmail(scanner.nextLine());

        System.out.print("Téléphone: ");
        phone = validationController.validatePhonenum(scanner.nextLine());

        System.out.print("Choisissez un password: ");
        password = scanner.nextLine();

        System.out.print("Entrez Le Nombre de composantes differents que vous produissez: ");
        System.out.println("(Vous pouvez rentrer 0 et rajouter vos composantes plus tard)");

        int numComp = Integer.parseInt(scanner.nextLine());

        components = new Component[numComp];
        for(int i=0;i<numComp;i++){
            components[i] = newComp();
        }

        currSeller = new Seller(name, address, email, phone, components, password);
        controller.add(currSeller);
    }


    /**
     * Method to handle the process of selling a new component.
     * The seller is prompted to enter information about the component,
     * and the component is added to the seller's list of components.
     */
    private void sellComp() {
        System.out.println("Veuillez saisir les informations suivantes sur la composante que vous voulez vendre:");
        Component component = newComp();
        currSeller.addComponent(component);
        controller.update(currSeller);
        System.out.println("La composante \"" + component.getName() + "\" a été listé sur le marché.");
    }

    /**
     * Method to view the components that the seller is currently selling.
     * It displays the details of each listed component.
     */
    private void viewComp() {
        System.out.println("Vous vendez les composantes suivantes:");
        for (Component component : currSeller.getComponents()) {
            System.out.println("Nom: " + component.getName());
            System.out.println("    type: " + component.getType());
            System.out.println("    desc: " + component.getDesc());
            System.out.println("    prix: " + component.getPrice());
        }
    }

    /**
     * Method to create a new Component instance based on user input.
     * It prompts the user to enter information about the new component
     * and returns the newly created Component instance.
     *
     * @return The newly created Component instance.
     */
    private Component newComp() {
        System.out.println("Entrez les informations pour votre composante: ");

        String nameComp, type, desc;
        int price;
        System.out.print("Nom du composante: ");
        nameComp = scanner.nextLine();
        System.out.print("Type: ");
        type = scanner.nextLine();
        System.out.print("Description: ");
        desc = scanner.nextLine();
        System.out.print("Prix: ");
        price = Integer.parseInt(scanner.nextLine());
        return new Component(nameComp, type, desc, price);
    }
}