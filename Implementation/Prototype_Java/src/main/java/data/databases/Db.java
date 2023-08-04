package data.databases;

import data.JsonHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<T> read() {
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
        List<T> objects = read();
        objects.remove(object);
        write(objects);
    }

    //modify mehod
    //void modify(T object);

}
