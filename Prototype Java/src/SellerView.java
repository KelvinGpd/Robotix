//UI for seller

import java.util.Scanner;

public class SellerView {
    Controller controller;
    Seller seller;
    public SellerView() {
        controller = new Controller();
    }

    public void run(){
        seller = register();

        System.out.println("Bienvenue à Robotix "+ seller.getName() +". Veuillez choisir une option:");
        System.out.println("1. Vendre des composantes 2. Voir vos composantes");
        switch (controller.choice(2)){
            case "1":
                sellComp();
                break;
            case "2":
                viewComp();
                break;
        }
        System.out.println("Voulez vous: \n1. Retourner au menu principal\n2. Quitter l'application");
        switch (controller.choice(2)){
            case "1":
                run();
                break;
            case "2":
                System.exit(0);
                break;
        }
    }

    private Seller register(){
        String name, address, email, phone;
        String[] components;
        Scanner reader = new Scanner(System.in);
        System.out.println("Veuillez saisir les informations suivantes:");
        System.out.print("Nom: ");
        name = reader.next();
        System.out.println("Addresse ");
        address = reader.next();
        System.out.println("Email: ");
        email = reader.next();
        System.out.println("Téléphone: ");
        phone = reader.next();
        System.out.println("Composantes que vous produissez (séparés par des virgules): ");
        components = reader.next().split(",");
        return new Seller(name, address, email, phone, components);
    }

    private void sellComp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de la composante que vous voulez vendre:");
        String component = scanner.next();

        System.out.println("La composante \"" + component + "\" a été listé sur le marché.");

    }
    private void viewComp(){
        System.out.println("Vous vendez les composantes suivantes:");
        for(String component : seller.getComponents()){
            System.out.println(component);
        }
    }
}