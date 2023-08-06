package data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The JsonHandler class provides utility methods to serialize Java objects to JSON format
 * and deserialize JSON strings back to Java objects using the Jackson library's ObjectMapper.
 */
public class JsonHandler {
    private ObjectMapper objectMapper;

    /**
     * Creates a new JsonHandler with a default ObjectMapper instance.
     */
    public JsonHandler() {
        this.objectMapper = new ObjectMapper();
    }


    /**
     * Serializes a Java object to its JSON representation.
     *
     * @param o The object to be serialized.
     * @return The JSON representation of the object as a String, or null if serialization fails.
     */
    public String objectToJson(Object o){
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Deserializes a JSON string to a Java object of the specified class.
     *
     * @param json      The JSON string to be deserialized.
     * @param valueType The class of the Java object to be created from the JSON.
     * @param <T>       The type of the Java object.
     * @return The Java object created from the JSON string, or null if deserialization fails.
     */
    public <T> T jsonToObject(String json, Class<T> valueType) {
        try{
            if(json.equals("") || json.equals("[]")){
                return null;
            }
            else{
                return objectMapper.readValue(json, valueType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
