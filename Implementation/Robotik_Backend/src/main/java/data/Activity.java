package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Activity class represents an activity with a name, description, interests, start date, end date, and points given.
 * It also contains a list of participants who are users that participate in the activity.
 */
public class Activity implements Serializable {
    private String name;
    private String description;
    private String[] interests;
    private String startDate;
    private String endDate;
    private int pointsGiven;

    private ArrayList<String> robots = new ArrayList<>();
    private List<User> participants;

    /**
     * Creates a new Activity with the specified attributes.
     *
     * @param name The name of the activity.
     * @param description The description of the activity.
     * @param interests The interests associated with the activity.
     * @param startDate The start date of the activity.
     * @param endDate The end date of the activity.
     * @param pointsGiven The points given for participating in the activity.
     */
    public Activity(String name, String description, String[] interests, String startDate, String endDate, int pointsGiven, ArrayList<String> robots){
        this.name = name;
        this.description = description;
        this.interests = interests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pointsGiven = pointsGiven;

        participants = new ArrayList<>();
    }

    /**
     * Creates a new Activity with the specified attributes.
     *
     * @param name The name of the activity.
     * @param description The description of the activity.
     * @param interests The interests associated with the activity.
     * @param startDate The start date of the activity.
     * @param endDate The end date of the activity.
     * @param pointsGiven The points given for participating in the activity.
     * @param participants The list of participants who are users that participate in the activity.
     */
    @JsonCreator
    public Activity(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("interests") String[] interests, @JsonProperty("startDate") String startDate
            , @JsonProperty("endDate") String endDate, @JsonProperty("pointsGiven") int pointsGiven, @JsonProperty("participants") List<User> participants) {
        this.name = name;
        this.description = description;
        this.interests = interests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pointsGiven = pointsGiven;
    }

    /**
     * Adds a user as a participant in the activity.
     *
     * @param user The user to be added as a participant.
     */
    public void participate(User user) {
        System.out.println("L'activité est schedulée!");
    }
    

    /**
     * Prints the information about the activity, including its name, description, start date, end date, and points given.
     */
    public void getActivityinfo() {
        System.out.println(name);
        System.out.println(description);
        System.out.println("    start date: " +startDate+ " end Date: " + endDate);
        System.out.println("    pointsGiven: " + pointsGiven);
    }

    //getters and setters for attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPointsGiven() {
        return pointsGiven;
    }

    public void setPointsGiven(int pointsGiven) {
        this.pointsGiven = pointsGiven;
    }
}


