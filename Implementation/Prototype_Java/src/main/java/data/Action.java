package data;

import java.util.ArrayList;

public class Action {

    private String name;
    private String type;
    private ArrayList<Tache> tasks;

    public Action (String type, String name) {
        this.type = type;
        this.name = name;
        tasks = new ArrayList<>();
    }

    public ArrayList<Tache> getTasks(){
        return tasks;
    }

    public void addTask(Tache tache) {
        tasks.add(tache);
    }

    public String getName() {
        return name;
    }

}
