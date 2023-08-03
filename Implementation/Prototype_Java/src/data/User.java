package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data.Robot;

//Pour quon puisse l'envoyer online, l'object user est serializable
public class User implements Serializable {
    private ArrayList<Robot> robots;
    private String email;
    private String username;
    private String phoneNumber;
    private String password;
    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<Activity> activities;
    private ArrayList<String> interests;

    public User(String email, String username, String phoneNumber, String password) {
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public ArrayList<String> getInterests(){
        return interests;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public String getEmail() {
        return email;
    }

    public void addFollower(String email) {
        followers.add(email);
    }

    public void addFollowing(String email) {
        following.add(email);
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