
import controllers.LoginController;
import controllers.ServiceController;
import controllers.ValidationController;
import views.SellerView;
import views.UserView;

/**
 * The main class that serves as the entry point for the Robotix application.
 * This class handles the user interface and provides options for users to register
 * and login as either a regular user or a seller (provider).
 */
public class Main {
    /**
     * The main method that starts the Robotix application.
     * It creates instances of the necessary controllers and views, then provides a loop
     * for users to choose between registering and logging in as a user or a seller.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ServiceController serviceController = new ServiceController();
        ValidationController validationController = new ValidationController();
        LoginController loginController = new LoginController();

        UserView userView = new UserView(serviceController, loginController);
        SellerView sellerView = new SellerView(serviceController, loginController);

        while (true) {
            System.out.println("*** Robotix ***");
            System.out.println(
                    "Veuillez choisir une option:\n1. S'enregistrer/login comme utilisateur\n2. S'enregistrer/login comme fournisseur");
            try {
                switch (validationController.takeValidInput(2)) {
                    case 1:
                        userView.run();
                        break;
                    case 2:
                        sellerView.run();
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Input non correct!");
            }
        }

    }
}