package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * The Tache class represents a task in the system.
 * It contains information about the task itself.
 */
public class Tache {
    private String task;

    private ArrayList<Action> actions;
    private String timeFormat;

    private boolean reapats;

    /**
     * Constructs a new Tache object with the provided task.
     *
     * @param task The task description.
     */
    @JsonCreator
    public Tache (@JsonProperty("name") String task, @JsonProperty("timeFormat") String timeFormat, @JsonProperty("actions") ArrayList<Action> actions, @JsonProperty("repeats") boolean reapats){
        this.task = task;
        this.timeFormat = timeFormat;
        this.actions = actions;
        this.reapats = reapats;
    }

    public boolean getRepeats(){
        return reapats;
    }

    /**
     * Gets the task description.
     *
     * @return The task description as a string.
     */
    public String getTask() {
        return task;
    }

    public String getTimeFormat(){
        return timeFormat;
    }

    public String getName() {
        return task;
    }
}
