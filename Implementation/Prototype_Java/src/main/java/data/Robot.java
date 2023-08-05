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
    public ArrayList<String> taches = new ArrayList<String>();

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
        ,@JsonProperty("uuid") UUID uuid
    ){
        this.type = type;
        this.name = name;
        this.uuid = uuid;
        this.parts = parts;
        this.position = position;
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


    public static class Pair<K, V> {
        private  K key;
        private  V value;

        @JsonCreator
        public Pair(@JsonProperty("key") K key, @JsonProperty("value") V value) {

            this.key = key;
            this.value = value;
        }


        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}