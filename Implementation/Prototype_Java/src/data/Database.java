package data;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

public class Database {

    private String usersPath, sellersPath, activitiesPath ;

    public Database(String usersPath, String sellersPath, String activitiesPath) {
        this.usersPath = usersPath;
        this.sellersPath = sellersPath;
        this.activitiesPath = activitiesPath;
    }

    //serializing data methods
    private void saveToFile(User user){
        ArrayList<User> users = loadUsers();
        if (users == null ) {
            users = new ArrayList<>();
        }
        users.add(user);
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(usersPath))) {
            outputStream.writeObject(users);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(Seller seller){
        ArrayList<Seller> sellers = loadSellers();
        if (sellers == null ) {
            sellers = new ArrayList<>();
        }
        sellers.add(seller);
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(sellersPath))) {
            outputStream.writeObject(sellers);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> loadUsers() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(usersPath))){
            return (ArrayList<User>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Seller> loadSellers() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(usersPath))){
            return (ArrayList<Seller>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void add(User u) {
        saveToFile(u);
    }

    public void add(Seller s) {
        saveToFile(s);
    }

    public void delete(User userToRemove) {
        ArrayList<User> users = loadUsers();
        if(users == null) {
            return;
        }

        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getEmail().equals(userToRemove.getEmail())) {
                iterator.remove();
                break;
            }

        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(usersPath))) {
            outputStream.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Seller sellerToRemove) {
        ArrayList<Seller> sellers = loadSellers();
        if(sellers == null) {
            return;
        }

        Iterator<Seller> iterator = sellers.iterator();
        while(iterator.hasNext()) {
            Seller seller = iterator.next();
            if(seller.getEmail().equals(sellerToRemove.getEmail())) {
                iterator.remove();
                break;
            }

        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(sellersPath))) {
            outputStream.writeObject(sellers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
