package data.databases;

import data.Client;
import data.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The abstract Db class serves as a base class for specific database implementations.
 * It provides methods for reading, writing, adding, removing, and logging in users.
 *
 * @param <T> The generic type parameter representing the objects to be stored in the database.
 */
abstract class Db<T> {
    String path;
    JsonHandler jsonHandler;

    /**
     * Gets the Class object representing the array of objects to be read from JSON.
     *
     * @return The Class object representing the array of objects to be read from JSON.
     */
    protected abstract Class<T[]> getArrayClass();

    /**
     * Creates a new Db object with the specified file path.
     *
     * @param path The file path to the JSON data file.
     */
    public Db (String path) {
        this.path = path;

        //initiate handler
        jsonHandler = new JsonHandler();
    }

    /**
     * Reads the objects from the JSON data file and returns them as a List.
     *
     * @return A List containing the objects read from the JSON data file.
     */
    public List<T> read() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            T[] array = jsonHandler.jsonToObject(json, getArrayClass());
            if(array != null) {
                return new ArrayList<>(Arrays.asList(array));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * Writes the given list of objects to the JSON data file.
     *
     * @param objects The list of objects to be written to the JSON data file.
     */
    private void write(List<T> objects) {
        try(FileWriter fileWriter = new FileWriter(path)) {
            String json = jsonHandler.objectToJson(objects);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds an object to the database and writes it to the JSON data file.
     *
     * @param object The object to be added to the database.
     */
    public void add(T object) {
        List<T> objects = read();
        objects.add(object);
        write(objects);
    }

    public void removeClient(T object){
        List<T> objects = read();
        objects.remove(object);
        write(objects);
    }

    /**
     * Clears the database by writing a null value to the JSON data file.
     */
    public void clear(){
        write(null);
    }

    /**
     * Removes an object from the database and writes the updated list to the JSON data file.
     *
     * @param object The object to be removed from the database.
     */
    public void remove(T object) {
        List<Client> objects = (List<Client>) read();
        Client thisClient = (Client) object;

        for(Client client : objects){
            if(client.getEmail().equals(thisClient.getEmail())){
                objects.remove(client);
                objects.add(thisClient);
            }
        }

        write((List<T>) objects);
    }

    /**
     * Logs in a user based on the provided email and password.
     *
     * @param email The email of the user to be logged in.
     * @param password The password of the user to be logged in.
     * @return The logged-in user object if successful, or null if the login fails.
     */
    public Client login(String email, String password){
        List<Client> objects = (List<Client>) read();
        Client match = null;
        for(Client obj : objects){
            if(obj.getEmail().equals(email)){
                if(obj.getPassword().equals(password)){
                    return obj;
                }
            }
        }
        return match;
    }

    /**
     * Retrieves a specific object from the database based on the provided email.
     *
     * @param email The email of the object to retrieve.
     * @return The object with the specified email, or null if not found.
     */
    public T get(String email){
        return null;
    }

}
