package data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Robot class represents a robot with various attributes like UUID, name, type, position, and a list of parts.
 * Each robot can have multiple parts associated with it, where each part is represented by a Pair object
 * containing the part name and the provider's name.
 */
public class Robot {
    private UUID uuid;
    private List<Pair<String, String>> parts;
    public String type;
    public int[] position = new int[] { 0, 0, 0 };
    public String name;
    public ArrayList<Tache> tasks = new ArrayList<>();

    /**
     * Constructs a new Robot with the given type and name. The UUID is generated automatically.
     *
     * @param type The type of the robot.
     * @param name The name of the robot.
     */
    public Robot(String type, String name) {
        this.uuid = UUID.randomUUID();
        this.parts = new ArrayList<>();
        this.type = type;
        this.name = name;

    }

    /**
     * Constructs a Robot object using the JSON properties provided.
     *
     * @param type     The type of the robot.
     * @param name     The name of the robot.
     * @param parts    The list of parts associated with the robot.
     * @param position The position of the robot represented by an integer array of size 3 (x, y, z).
     * @param uuid     The UUID of the robot.
     */
    @JsonCreator
    public Robot(@JsonProperty("type") String type, @JsonProperty("name") String name, @JsonProperty("parts") List<Pair<String, String>> parts, @JsonProperty("position") int[] position
        ,@JsonProperty("uuid") UUID uuid, @JsonProperty("taches") ArrayList<Tache> tasks
    ){
        this.type = type;
        this.name = name;
        this.uuid = uuid;
        this.parts = parts;
        this.position = position;
        this.tasks = tasks;
    }

    /**
     * Adds a new part to the robot.
     *
     * @param partName The name of the part to be added.
     * @param provider The name of the provider providing the part.
     */
    public void addPart(String partName, String provider) {
        parts.add(new Pair<>(partName, provider));
    }


    /**
     * Gets the UUID of the robot.
     *
     * @return The UUID of the robot.
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Gets the list of parts associated with the robot.
     *
     * @return The list of parts as a List of Pair objects.
     */
    public List<Pair<String, String>> getParts() {
        return parts;
    }


    /**
     * The Pair class represents a simple key-value pair data structure.
     * It is a static nested class used within other classes for storing and retrieving
     * key-value associations.
     *
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     */
    public static class Pair<K, V> {
        private  K key;
        private  V value;

        /**
         * Constructs a new Pair object with the given key and value.
         *
         * @param key   The key of the key-value pair.
         * @param value The value associated with the key.
         */
        @JsonCreator
        public Pair(@JsonProperty("key") K key, @JsonProperty("value") V value) {

            this.key = key;
            this.value = value;
        }


        /**
         * Gets the key of the key-value pair.
         *
         * @return The key of the pair.
         */
        public K getKey() {
            return key;
        }

        /**
         * Gets the value associated with the key-value pair.
         *
         * @return The value of the pair.
         */
        public V getValue() {
            return value;
        }
    }
}