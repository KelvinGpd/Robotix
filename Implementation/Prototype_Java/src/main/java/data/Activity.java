package data;

import java.util.ArrayList;
import java.util.List;

public class Activity {
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
