//UI for seller

import java.util.Scanner;

import controllers.Controller;
import controllers.ValidationController;
import data.Seller;
import data.Component;

public class SellerView {
    ValidationController validationController;
    Controller controller;
    Seller seller;

    public SellerView(Controller controller) {
        this.controller = controller;
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("register, or login?");

        switch (scanner.nextLine()) {
            case "register" : register();
            case "login" : login();
            default: run();
        }

        while (true) {
            System.out.println("Bienvenue à Robotix " + seller.getName() + ". Veuillez choisir une option:");
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

    public void login() {

        System.out.print("Entrez votre email: ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.print("Entrez votre password: ");
        String password = scanner.nextLine();

        seller = controller.authenticateSeller(email, password);
        if (seller == null) {
            System.out.println("Password ou email incorrect!");
            return;
        }

    }

    private Seller register() {
        String name, address, email, phone, password;
        Component[] components;
        Scanner reader = new Scanner(System.in);
        System.out.println("Veuillez saisir les informations suivantes:");
        System.out.print("Nom: ");
        name = reader.nextLine();
        System.out.print("Addresse: ");
        address = reader.nextLine();
        System.out.print("Email: ");
        email = reader.nextLine();
        System.out.print("Téléphone: ");
        phone = reader.nextLine();
        System.out.print("Choisissez un password: ");
        password = reader.nextLine();

        System.out.print("Nombre de composantes differents que vous produissez: ");
        int numComp = Integer.parseInt(reader.nextLine());
        components = new Component[numComp];
        for(int i=0;i<numComp;i++){
            components[i] = newComp();
        }
        
        return new Seller(name, address, email, phone, components, password);
    }

    private void sellComp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir les informations suivantes sur la composante que vous voulez vendre:");
        Component component = newComp();
        seller.addComponent(component);
        controller.update(seller);
        System.out.println("La composante \"" + component.getName() + "\" a été listé sur le marché.");
    }
    private void viewComp() {
        System.out.println("Vous vendez les composantes suivantes:");
        for (Component component : seller.getComponents()) {
            System.out.println(component + "\n");
        }
    }

    private Component newComp() {
        Scanner reader = new Scanner(System.in);
        String nameComp, type, desc;
        int price;
        System.out.print("Nom du composante: ");
        nameComp = reader.nextLine();
        System.out.print("Type: ");
        type = reader.nextLine();
        System.out.print("Description: ");
        desc = reader.nextLine();
        System.out.print("Prix: ");
        price = Integer.parseInt(reader.nextLine());
        return new Component(nameComp, type, desc, price);
    }
}