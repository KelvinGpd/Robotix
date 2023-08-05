package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Action class represents an action with a specific type, name, and a list of tasks.
 */
public class Action {

    private String name;
    private String type;

    private String value;

    private double[] vector;

    /**
     * Creates a new Action with the specified type and name.
     *
     * @param type The type of the action.
     * @param name The name of the action.
     */
    @JsonCreator
    public Action (@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("value") String value,@JsonProperty("position") double[] vector) {
        this.type = type;
        this.name = name;
        this.vector = vector;
    }

    public String getValue() {
        return value;
    }

    public double[] getVector() {
        return vector;
    }

    public String getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
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
