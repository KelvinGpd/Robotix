import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Robot {
    private UUID uuid;
    private List<Pair<String, String>> parts;
    private String type;

    public Robot(String type) {
        this.uuid = UUID.randomUUID();
        this.parts = new ArrayList<>();
        this.type = type;
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

    // Pair class for holding part name and provider
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