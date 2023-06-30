package data;

public class Seller {
    String name, address, email, phone, password;
    String[] components;

    public Seller(String name, String address, String email, String phone, String[] components, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.components = components;
        this.password = password;
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

    public String[] getComponents() {
        return components;
    }
}