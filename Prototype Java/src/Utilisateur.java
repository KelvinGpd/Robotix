public class Utilisateur {
    String name, lastname, username, email, phone, company;


    public Utilisateur (String name, String lastname, String username, String email, String phone, String company) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }
}