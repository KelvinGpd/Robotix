package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHandler {
    private ObjectMapper objectMapper;

    public JsonHandler() {
        this.objectMapper = new ObjectMapper();
    }

    //serializing methods for objects
    public String objectToJson(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //deserializing method for objects
    public <T> T jsonToObject(String json, Class<T> valueType) {
        try{
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
