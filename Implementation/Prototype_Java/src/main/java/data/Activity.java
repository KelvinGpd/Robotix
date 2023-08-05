package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity implements Serializable {
    private String name;
    private String description;
    private String[] interests;
    private String startDate;
    private String endDate;
    private int pointsGiven;

    //participants
    private List<User> participants;

    public Activity(String name, String description, String[] interests, String startDate, String endDate, int pointsGiven){
        this.name = name;
        this.description = description;
        this.interests = interests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pointsGiven = pointsGiven;

        participants = new ArrayList<>();
    }

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

    public void participate(User user) {
        participants.add(user);
    }

    public void getActivityinfo() {
        System.out.println(name);
        System.out.println(description);
        System.out.println("start date: " +startDate+ " end Date: " + endDate);
        System.out.println("pointsGiven: " + pointsGiven);
    }

}
