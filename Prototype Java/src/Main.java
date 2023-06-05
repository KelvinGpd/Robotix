//the user interface
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserView userView = new UserView();
        SellerView sellerView = new SellerView();

        System.out.println("*** Robotix ***");
        System.out.println(
                "Veuillez choisir une option:\n1. S'enregistrer comme utilisateur\n2. S'enregistrer comme fournisseur");
        switch (controller.choice(2)) {
            case "1": 
                userView.run();
                break;
            case "2":
                sellerView.run();
                break;
        }
    }
}