package data;

import java.util.List;

public class Activity {
    private List<String> interests;
    private int pointsGiven;
    private int expiryDate;
    private String description;

    public Activity(List<String> interests, int pointsGiven, int expiryDate, String description ){
        this.interests = interests;
        this.pointsGiven = pointsGiven;
        this.expiryDate = expiryDate;
        this.description = description;
    }
}
