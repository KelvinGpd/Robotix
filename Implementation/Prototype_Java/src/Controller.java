import java.util.Scanner;

import data.Database;
import data.Seller;
import data.User;

public class Controller {
    private Database database;

    public Controller() {
        database = new Database();
        System.out.println(database.sellers);
    }

    public String choice(int numChoices) {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String choice = reader.next();
            if (isValid(choice, numChoices)) {
                return choice;
            }
            System.out.print("Veuillez saisir un choix valide: ");
        }
    }

    public void add(User e) {
        database.users.add(e);
    }

    public void add(Seller e) {
        database.sellers.add(e);
    }

    public Seller authenticateSeller(String email, String password) {
        for (int i = 0; i < database.sellers.size(); i++) {
            if (database.sellers.get(i).getEmail() == email && database.sellers.get(i).getPassword() == password) {
                return database.sellers.get(i);
            }
        }
        return null;
    }

    public User authenticateUser(String email, String password) {
        for (int i = 0; i < database.users.size(); i++) {
            if (database.users.get(i).getEmail() == email && database.users.get(i).getPassword() == password) {
                return database.users.get(i);
            }
        }
        return null;
    }

    private boolean isValid(String input, int max) {
        int n;
        if (input == null) {
            return false;
        }
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return n > 0 && n <= max;
    }
}