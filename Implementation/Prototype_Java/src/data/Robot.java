package data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Robot {
    private UUID uuid;
    private List<Pair<String, String>> parts;
    public String type;
    public int[] position = new int[] { 0, 0, 0 };
    public String name;

    public Robot(String type, String name) {
        this.uuid = UUID.randomUUID();
        this.parts = new ArrayList<>();
        this.type = type;
        this.name = name;

    }

    public void addPart(String partName, String provider) {
        parts.add(new Pair<>(partName, provider));
    }

    public UUID getUUID() {
        return uuid;
    }

    public List<Pair<String, String>> getParts() {
        return parts;
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
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