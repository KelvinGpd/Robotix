package data;

import java.io.Serializable;
import java.util.ArrayList;
import data.Robot;

//Pour quon puisse l'envoyer online, l'object user est serializable
public class User implements Serializable {
    private ArrayList<Robot> robots;
    private String email;
    private String username;
    private String phoneNumber;
    private String password;

    public User(String email, String username, String phoneNumber, String password) {
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void add(Robot r) {
        robots.add(r);
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }
}