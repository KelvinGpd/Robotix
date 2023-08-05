import controllers.Controller;

//the user interface
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserView userView = new UserView(controller);
        SellerView sellerView = new SellerView(controller);

        while (true) {
            System.out.println("*** Robotix ***");
            System.out.println(
                    "Veuillez choisir une option:\n1. S'enregistrer/login comme utilisateur\n2. S'enregistrer/login comme fournisseur");

            switch (controller.choice(4)) {
                case "1":
                    userView.run();
                    break;
                case "2":
                    sellerView.run();
                    break;
            }
        }
    }
}