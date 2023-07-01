import java.util.Scanner;

import data.Database;
import data.Seller;
import data.User;

public class Controller {
    private Database database;

    public Controller() {
        database = new Database();
        String[] components = { "wheels", "cpu" };
        this.add(new Seller("Companie A", "1234 street", "Company@email.com", "111-1111-1111", components, "qwerty"));
        String[] components2 = { "helice", "batteries" };
        this.add(new Seller("Companie B", "5678 street", "Company2@email.com", "222-2222-2222", components2, "qwerty"));
        String[] components3 = { "Arms", "wings" };
        this.add(new Seller("Companie C", "12313 street", "Companysdasd@email.com", "333-1111-1111", components3,
                "qwerty"));
        String[] components4 = { "Gpu", "Paint" };
        this.add(new Seller("Companie D", "1234123 street", "Company222@email.com", "333-4567-1111", components4,
                "qwerty"));
        String[] components5 = { "Lithium", "Robot food" };
        this.add(new User("randomUser@email.com", "randomUser1", "111-1111-1111", "qwerty"));
        this.add(new Seller("Companie E", "1234 street", "Company@email.com", "111-1111-1111", components5, "qwerty"));
        this.add(new User("randomUser2@email.com", "randomUser2", "121-1111-1111", "qwerty"));
        this.add(new User("randomUser3@email.com", "randomUser3", "131-1111-1111", "qwerty"));
        this.add(new User("randomUser4@email.com", "randomUser4", "141-1111-1111", "qwerty"));
        this.add(new User("randomUser5@email.com", "randomUser5", "151-1111-1111", "qwerty"));
        this.add(new User("randomUser6@email.com", "randomUser6", "161-1111-1111", "qwerty"));
        this.add(new User("randomUser7@email.com", "randomUser7", "171-1111-1111", "qwerty"));
        this.add(new User("randomUser8@email.com", "randomUser8", "181-1111-1111", "qwerty"));
        this.add(new User("randomUser9@email.com", "randomUser9", "191-1111-1111", "qwerty"));
        this.add(new User("randomUser10@email.com", "randomUser10", "221-1111-1111", "qwerty"));

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