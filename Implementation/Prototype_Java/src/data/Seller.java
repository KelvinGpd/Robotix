package data;

import java.util.ArrayList;
import java.util.Collections;

public class Seller {
    String name, address, email, phone, password;
    ArrayList<Component> components;

    public Seller(String name, String address, String email, String phone, Component[] components, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;

        this.components = new ArrayList<>();
        Collections.addAll(this.components, components);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void addComponent(Component comp){
        components.add(comp);
    }
}