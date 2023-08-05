package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * The Action class represents an action with a specific type, name, and a list of tasks.
 */
public class Action {

    private String name;
    private String type;

    private String value;
    private ArrayList<Tache> tasks;

    /**
     * Creates a new Action with the specified type and name.
     *
     * @param type The type of the action.
     * @param name The name of the action.
     */
    @JsonCreator
    public Action (@JsonProperty("type") String type, @JsonProperty("name") String name) {
        this.type = type;
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the list of tasks associated with this action.
     *
     * @return An ArrayList containing the tasks associated with this action.
     */
    public ArrayList<Tache> getTasks(){
        return tasks;
    }

    /**
     * Adds a task to the list of tasks associated with this action.
     *
     * @param tache The task to be added.
     */
    public void addTask(Tache tache) {
        tasks.add(tache);
    }

    /**
     * Gets the name of this action.
     *
     * @return The name of this action.
     */
    public String getName() {
        return name;
    }

}
