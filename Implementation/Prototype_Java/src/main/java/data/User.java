package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User extends Client {
    private ArrayList<Robot> robots;
    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<Activity> activities;
    private ArrayList<String> interests;

    private int points;

    //Constructeur normal
    public User(String email, String username, String phoneNumber, String password){
        super(username, email, phoneNumber, password);
        this.robots = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.interests = new ArrayList<>();
    }
    //Pour recoustruire l'object deserialisé
    public User(String username, String email, String phoneNumber, String password, ArrayList<Robot> robots, String[] followers, String[] following,Activity[] activities, String[] interests, int points){
        super(username, email, phoneNumber, password);
        this.robots = robots;
        this.points = points;
        this.followers = (ArrayList<String>) Arrays.asList(followers);
        this.following = (ArrayList<String>) Arrays.asList(following);
        this.activities = (ArrayList<Activity>) Arrays.asList(activities);
        this.interests = (ArrayList<String>) Arrays.asList(interests);
    }
    //Pour recoustruire l'object deserialisé
    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("email") String email,@JsonProperty("phone") String phoneNumber,
                @JsonProperty("password") String password,@JsonProperty("following") ArrayList<String> following,
                @JsonProperty("robots") ArrayList<Robot> robots,
                @JsonProperty("activities") ArrayList<Activity> activities,
                @JsonProperty("interests") ArrayList<String> interests,
                @JsonProperty("followers") ArrayList<String> followers,
                @JsonProperty("points") int points
                ) {

        super(name, email, phoneNumber, password);
        this.followers = followers;

        this.following = following;
        this.robots = robots;
        this.activities = activities;
        this.interests = interests;
        this.points = points;


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
    public void addFollower(String user) {
        followers.add(user);
    }
    public void addFollowing(String user) {
        following.add(user);
    }
    public List<String> getFollowers() {
        return followers;
    }
    public List<String> getFollowing() {
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