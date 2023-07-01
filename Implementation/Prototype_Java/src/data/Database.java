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

    public void delete(User u) {
        for (int i = 0; i < users.size(); i++) {
            if (u.getEmail() == this.users.get(i).getEmail()) {
                users.remove(i);
                break;
            }
        }
    }

    public void delete(Seller s) {
        for (int i = 0; i < sellers.size(); i++) {
            if (s.getEmail() == this.sellers.get(i).getEmail()) {
                sellers.remove(i);
                break;
            }
        }
    }
}
