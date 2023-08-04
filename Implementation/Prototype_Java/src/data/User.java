package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import data.Robot;

//Pour quon puisse l'envoyer online, l'object user est serializable
public class User extends Client implements Serializable {
    private ArrayList<Robot> robots;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Activity> activities;
    private ArrayList<String> interests;
    private int points;

    public User(String email, String username, String phoneNumber, String password){
        super(username, email, phoneNumber, password);
    }

    //Interests
    public ArrayList<String> getInterests(){
        return interests;
    }
    public void addInterest(String interest) {
        interests.add(interest);
    }

    //Activities
    public List<Activity> getActivities() {
        return activities;
    }
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
    public int getPoints(){
        return points;
    }
    public void addPoints(int points) {
        this.points += points;
    }

    //Followers
    public void addFollower(User user) {
        followers.add(user);
    }
    public void addFollowing(User user) {
        following.add(user);
    }
    public List<User> getFollowers() {
        return followers;
    }
    public List<User> getFollowing() {
        return following;
    }

    //Robots
    public void add(Robot r) {
        robots.add(r);
    }
    public ArrayList<Robot> getRobots() {
        return robots;
    }


}