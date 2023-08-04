//UI for seller

import java.util.Scanner;

import data.Seller;
import data.User;
import data.Component;

public class SellerView {
    Controller controller;
    Seller seller;

    public SellerView(Controller controller) {
        this.controller = controller;
    }

    public void run(boolean isNew) {
        if (isNew) {
            seller = register();
            controller.add(seller);
        } else {
            String email, password;
            System.out.print("Entrez votre email: ");
            Scanner scanner = new Scanner(System.in);
            email = scanner.nextLine();
            System.out.print("Entrez votre password: ");
            password = scanner.nextLine();
            seller = controller.authenticateSeller(email, password);
            if (seller == null) {
                System.out.println("Password ou email incorrect!");
                return;
            }
        }

        while (true) {
            System.out.println("Bienvenue à Robotix " + seller.getName() + ". Veuillez choisir une option:");
            System.out.println("1. Vendre des composantes 2. Voir vos composantes");
            switch (controller.choice(2)) {
                case "1":
                    sellComp();
                    break;
                case "2":
                    viewComp();
                    break;
            }
            System.out.println("Voulez vous: \n1. Retourner au menu principal\n2. Quitter l'application");
            switch (controller.choice(2)) {
                case "1":
                    continue;
                case "2":
                    System.exit(0);
                    break;
            }
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