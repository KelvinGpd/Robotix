public class Seller {
    String name, address, email, phone;
    String[] components;
    public Seller(String name, String address, String email, String phone, String[] components){
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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