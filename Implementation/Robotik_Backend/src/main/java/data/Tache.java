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

    /**
     * Gets the list of Actions of the Task.
     *
     * @return The list of actions as an ArrayList of Actions.
     */
    public ArrayList<Action> getActions() {
        return actions;
    }


    /**
     * Sets the task's repetition status.
     *
     * @param reapats A boolean indicating whether the task should be repeated.
     */
    public void setReapats(boolean reapats) {
        this.reapats = reapats;
    }

    /**
     * Sets the time format in which the task is scheduled to be executed (HH/MM).
     *
     * @param timeFormat The time format of the task.
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }


    /**
     * Gets the repetition status of the task.
     *
     * @return A boolean indicating whether the task should be repeated daily or not.
     */
    public boolean getRepeats(){
        return reapats;
    }


    /**
     * Gets the description of the task.
     *
     * @return The description of the task as a String.
     */
    public String getTask() {
        return task;
    }

    /**
     * Gets the time format in which the task is scheduled to be executed (HH/MM).
     *
     * @return The time format of the task.
     */
    public String getTimeFormat(){
        return timeFormat;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task as a String.
     */
    public String getName() {
        return task;
    }
}
