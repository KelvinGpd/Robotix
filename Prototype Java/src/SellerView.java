//UI for seller

import java.util.Scanner;

public class SellerView {
    Controller controller;
    public SellerView() {
        controller = new Controller();
    }

    public void run(){
        Seller seller = register();

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
        //TODO
    }
    private void viewComp(){
        //TODO
    }
}