package data;

import java.util.List;

public class Activity {
    private int pointsGiven;
    private int expiryDate;
    private String description;
    private List<String> interests;
    private List<User> participants;

    public Activity(List<String> interests, int pointsGiven, int expiryDate, String description ){
        this.interests = interests;
        this.pointsGiven = pointsGiven;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    public void participate(User user) {
        participants.add(user);
    }

}
