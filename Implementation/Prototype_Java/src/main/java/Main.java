//the user interface
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserView userView = new UserView(controller);
        SellerView sellerView = new SellerView(controller);

        while (true) {
            System.out.println("*** Robotix ***");
            System.out.println(
                    "Veuillez choisir une option:\n1. S'enregistrer comme utilisateur\n2. S'enregistrer comme fournisseur \n3. Login user \n4. Login seller");

            switch (controller.choice(4)) {
                case "1":
                    userView.run(true);
                    break;
                case "2":
                    sellerView.run(true);
                    break;
                case "3":
                    userView.run(false);
                    break;
                case "4":
                    sellerView.run(false);
                    break;
            }
        }
    }
}