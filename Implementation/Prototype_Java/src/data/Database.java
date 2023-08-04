package data;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

public class Database {

    private String usersPath, sellersPath, activitiesPath;

    public Database(String usersPath, String sellersPath, String activitiesPath) {
        this.usersPath = usersPath;
        this.sellersPath = sellersPath;
        this.activitiesPath = activitiesPath;
    }

    public List<String[]> readCsvFile(String path) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        }
        return data;
    }

    // Method to transform data and write it back to a new CSV file.
    public void transformAndWriteCsvFile(List<String[]> data, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            for (String[] row : data) {
                String line = String.join(",", row);
                writer.write(line + "\n");
            }
            writer.flush();
        }
    }

    public void add(Seller e) {
        try {
            List<String[]> currData = readCsvFile(sellersPath);
            String[] userString = { e.name, e.email, e.password, e.phone, e.address, e.components.toString() };
            currData.add(userString);
            transformAndWriteCsvFile(currData, sellersPath);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void add(User u) {
        try {
            List<String[]> currData = readCsvFile(sellersPath);
            String[] userString = { u.getName(), u.getEmail(), u.getPassword(), u.getPhone(),
                    u.getActivities().toString(), u.getFollowers().toString(), u.getActivities().toString() };
            currData.add(userString);
            transformAndWriteCsvFile(currData, sellersPath);

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<User>();
        try {
            List<String[]> currData = readCsvFile(sellersPath);
            for (String[] line : currData) {
                users.add(lineToUser(line));
            }

        } catch (Exception e) {

        }
        return users;
    }

    // On doit faire un nouveau constructor pour User, pour allow la recreation de
    // l'object
    private User lineToUser(String[] line) {
        User user = new User(line[1], line[0], line[3], line[2]);
        return user;
    }

    public List<Seller> loadSellers() {
        List<Seller> sellers = new ArrayList<Seller>();
        try {
            List<String[]> currData = readCsvFile(sellersPath);
            for (String[] line : currData) {
                sellers.add(lineToSeller(line));
            }

        } catch (Exception e) {

        }
        return sellers;
    }

    // MARCHE PAS
    private Seller lineToSeller(String[] line) {
        List<Component> components = null;
        Seller seller = new Seller(line[1], line[0], line[3], line[2], null, line[2]);
        return seller;
    }

    // Todo
    public void delete(User u) {

    }

    // Todo
    public void delete(Seller s) {

    }

}
