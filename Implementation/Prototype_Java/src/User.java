import java.io.Serializable;

//Pour quon puisse l'envoyer online, l'object user est serializable
public class User implements Serializable{
    private String email;
    private String username;
    private String phoneNumber;

    public User(String email, String username, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}