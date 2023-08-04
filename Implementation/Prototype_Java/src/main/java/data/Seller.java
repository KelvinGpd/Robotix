package data;

import java.util.ArrayList;
import java.util.Collections;

public class Seller extends Client{
    String address;
    ArrayList<Component> components;

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