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
import java.util.Objects;

abstract class Db<T> {
    String path;
    JsonHandler jsonHandler;

    protected abstract Class<T[]> getArrayClass();

    public Db (String path) {
        this.path = path;

        //initiate handler
        jsonHandler = new JsonHandler();
    }

    //read method
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


    //write method
    private void write(List<T> objects) {
        try(FileWriter fileWriter = new FileWriter(path)) {
            String json = jsonHandler.objectToJson(objects);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //add method
    public void add(T object) {
        List<T> objects = read();
        objects.add(object);
        write(objects);
    }

    public void remove(T object) {
        List<Client> objects = (List<Client>) read();
        objects.remove((Client) object);
        write((List<T>) objects);
    }

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
    public T get(String email){
        return null;
    }



    //modify mehod
    //void modify(T object);

}
