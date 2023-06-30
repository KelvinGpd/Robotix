package data;

import java.util.ArrayList;

import javax.xml.crypto.Data;

public class Database {
    public ArrayList<User> users;
    public ArrayList<Seller> sellers;

    public Database() {
        this.users = new ArrayList<User>();
        this.sellers = new ArrayList<Seller>();
    }

    public void add(User u) {
        users.add(u);
    }

    public void add(Seller s) {
        sellers.add(s);
    }
}
