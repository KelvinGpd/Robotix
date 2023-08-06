package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * The Component class represents a component with a name, type, description, and price.
 * It implements the Serializable interface to support serialization.
 */
public class Component implements Serializable {
    String name;
    String type;
    String desc;
    int price;

    /**
     * Creates a new Component with the specified attributes.
     *
     * @param name  The name of the component.
     * @param type  The type of the component.
     * @param desc  The description of the component.
     * @param price The price of the component.
     */
    public Component(String name, String type, String desc, int price){
        this.name=name;
        this.type=type;
        this.desc=desc;
        this.price=price;
    }

    /**
     * Creates a new Component with the specified name and type.
     * This constructor is used during JSON deserialization with Jackson.
     *
     * @param name The name of the component.
     * @param type The type of the component.
     */
    @JsonCreator
    public Component(@JsonProperty("name") String name, @JsonProperty("type") String type){
        this.name=name;
        this.type=type;
    }

    /**
     * Gets the price of the component.
     *
     * @return The price of the component as an integer.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the description of the component.
     *
     * @return The description of the component as a String.
     */
    public String getDesc() {
        return desc;
    }


    /**
     * Gets the name of the component.
     *
     * @return The name of the component as a String.
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the type of the component.
     *
     * @return The type of the component as a String.
     */
    public String getType() {
        return type;
    }

}


