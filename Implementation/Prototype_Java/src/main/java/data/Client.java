package data;

/**
 * The Client class represents a client with a name, email, phone number, and password.
 */
public class Client {
    String name, email, phone, password;

    /**
     * Creates a new Client with the specified attributes.
     *
     * @param name     The name of the client.
     * @param email    The email address of the client.
     * @param phone    The phone number of the client.
     * @param password The password of the client.
     */
    public Client(String name, String email, String phone, String password){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.password=password;
    }


    public Client() {

    }


    /**
     * Gets the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the client.
     *
     * @return The email address of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the password of the client.
     *
     * @return The password of the client.
     */
    public String getPassword() {
        return password;
    }
}
