//UI for seller

import java.util.Scanner;

import data.Seller;
import data.User;

public class SellerView {
    Controller controller;
    Seller seller;

    public SellerView(Controller controller) {
        this.controller = controller;
    }

    public void run(boolean isNew) {
        Seller seller;
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
        String[] components;
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

        System.out.println("Composantes que vous produissez (séparés par des virgules): ");
        components = reader.next().split(",");
        return new Seller(name, address, email, phone, components, password);
    }

    private void sellComp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de la composante que vous voulez vendre:");
        String component = scanner.next();

        System.out.println("La composante \"" + component + "\" a été listé sur le marché.");

    }

    private void viewComp() {
        System.out.println("Vous vendez les composantes suivantes:");
        for (String component : seller.getComponents()) {
            System.out.println(component);
        }
    }
}