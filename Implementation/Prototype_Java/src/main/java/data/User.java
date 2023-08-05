package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user in the system, extending the Client class.
 * It contains information about the user's robots, followers, following,
 * activities, interests, points, and actions.
 */
public class User extends Client {
    private ArrayList<Robot> robots;
    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<Activity> activities;
    private ArrayList<String> interests;

    private ArrayList<Tache> tasks;
    private ArrayList<Action> actions;
    private int points;

    /**
     * Constructs a new User object with the provided basic information.
     *
     * @param email       The email of the user.
     * @param username    The username of the user.
     * @param phoneNumber The phone number of the user.
     * @param password    The password of the user.
     */
    public User(String email, String username, String phoneNumber, String password){
        super(username, email, phoneNumber, password);
        this.robots = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }


    /**
     * Constructs a new User object with the provided data for deserialization.
     *
     * @param name      The name of the user.
     * @param email     The email of the user.
     * @param phoneNumber The phone number of the user.
     * @param password The password of the user.
     * @param following The list of users being followed by the user.
     * @param robots    The list of robots owned by the user.
     * @param activities The list of activities associated with the user.
     * @param interests The list of interests of the user.
     * @param followers The list of followers of the user.
     * @param points    The points accumulated by the user.
     */
    //Pour recoustruire l'object deserialisé
    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("email") String email,@JsonProperty("phone") String phoneNumber,
                @JsonProperty("password") String password,@JsonProperty("following") ArrayList<String> following,
                @JsonProperty("robots") ArrayList<Robot> robots,
                @JsonProperty("activities") ArrayList<Activity> activities,
                @JsonProperty("interests") ArrayList<String> interests,
                @JsonProperty("followers") ArrayList<String> followers,
                @JsonProperty("points") int points,
                @JsonProperty("tasks") ArrayList<Tache> tasks
                ) {

        super(name, email, phoneNumber, password);
        this.followers = followers;

        this.following = following;
        this.robots = robots;
        this.activities = activities;
        this.interests = interests;
        this.points = points;
        this.tasks = tasks;

    }

    public ArrayList<Tache> getTasks() {
        return tasks;
    }


    /**
     * Gets the list of interests of the user.
     *
     * @return The list of interests as an ArrayList of strings.
     */

    public void addRobot(Robot r){
        this.robots.add(r);
    }
    //Interests

    public ArrayList<String> getInterests(){
        return interests;
    }

    /**
     * Adds a new interest to the user's list of interests.
     *
     * @param interest The interest to be added.
     */
    public void addInterest(String interest) {
        interests.add(interest);
    }

    /**
     * Gets the list of activities associated with the user.
     *
     * @return The list of activities as a List of Activity objects.
     */
    public List<Activity> getActivities() {
        return activities;
    }


    /**
     * Adds a new activity to the user's list of activities.
     *
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }


    /**
     * Gets the points accumulated by the user.
     *
     * @return The total points as an integer value.
     */
    public int getPoints(){
        return points;
    }

    /**
     * Adds points to the user's total points.
     *
     * @param points The points to be added.
     */
    public void addPoints(int points) {
        this.points += points;
    }


    /**
     * Adds a new user to the list of followers.
     *
     * @param user The user to be added as a follower.
     */
    public void addFollower(String user) {
        followers.add(user);
    }


    /**
     * Adds a new user to the list of users being followed by this user.
     *
     * @param user The user to be followed.
     */
    public void addFollowing(String user) {
        following.add(user);
    }

    /**
     * Gets the list of followers of the user.
     *
     * @return The list of followers as a List of strings representing user names.
     */
    public List<String> getFollowers() {
        return followers;
    }

    /**
     * Gets the list of users being followed by this user.
     *
     * @return The list of users being followed as a List of strings representing user names.
     */
    public List<String> getFollowing() {
        return following;
    }

    /**
     * Adds a new robot to the list of robots owned by the user.
     *
     * @param r The robot to be added.
     */
    public void add(Robot r) {
        robots.add(r);
    }

    /**
     * Gets the list of robots owned by the user.
     *
     * @return The list of robots as an ArrayList of Robot objects.
     */
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    /**
     * Gets the list of actions associated with the user.
     *
     * @return The list of actions as a List of Action objects.
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Adds a new action to the user's list of actions.
     *
     * @param action The action to be added.
     */
    public void add(Action action) {
        actions.add(action);
    }


}