package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Seller class represents a seller in the system, which is a subclass of the Client class.
 * It contains information about the seller's address and the list of components they provide.
 */
public class Seller extends Client{
    String address;
    ArrayList<Component> components;

    /**
     * Constructs a new Seller object using the JSON properties provided.
     *
     * @param name      The name of the seller.
     * @param email     The email of the seller.
     * @param phone     The phone number of the seller.
     * @param components The list of components provided by the seller.
     * @param password  The password of the seller.
     * @param address   The address of the seller.
     */
    @JsonCreator
    public Seller(@JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("components") ArrayList<Component> components,
                  @JsonProperty("password") String password,
                  @JsonProperty("adress") String address)
    {
        super(name,email,phone,password);
        this.address = address;
        this.components = components;
    }

    /**
     * Sets the name of the seller.
     *
     * @param name The new name of the seller.
     */
    public void setName(String name){
        this.name = name;
    }


    /**
     * Constructs a new Seller object with the provided attributes.
     *
     * @param name       The name of the seller.
     * @param address    The address of the seller.
     * @param email      The email of the seller.
     * @param phone      The phone number of the seller.
     * @param components The array of components provided by the seller.
     * @param password   The password of the seller.
     */
    public Seller(String name, String address, String email, String phone, Component[] components, String password) {
        super(name, email, phone, password);
        this.address = address;

        this.components = new ArrayList<>();
        Collections.addAll(this.components, components);
    }


    /**
     * Gets the address of the seller.
     *
     * @return The address of the seller.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the list of components provided by the seller.
     *
     * @return The list of components as an ArrayList.
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Adds a new component to the list of components provided by the seller.
     *
     * @param comp The component to be added.
     */
    public void addComponent(Component comp){
        components.add(comp);
    }
}