package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;

public class Seller extends Client{
    String address;
    ArrayList<Component> components;

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
    public void setName(String name){
        this.name = name;
    }


    public Seller(String name, String address, String email, String phone, Component[] components, String password) {
        super(name, email, phone, password);
        this.address = address;

        this.components = new ArrayList<>();
        Collections.addAll(this.components, components);
    }


    public String getAddress() {
        return address;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void addComponent(Component comp){
        components.add(comp);
    }
}