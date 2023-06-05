//the user interface

<<<<<<< HEAD
public class Main {
    public static void main(String[] args){
=======
import java.util.Scanner;

public class Main {
    public void main(String[] args) {
>>>>>>> 19419e188f66d79922188c671b2996fab37584e2
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