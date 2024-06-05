import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiValueMap<K, V> {
    private Map<K, List<V>> map;

    public MultiValueMap() {
        map = new HashMap<>();
    }
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    public List<V> get(K key) {
        return map.getOrDefault(key, new ArrayList<>());
    }

    public void remove(K key, V value) {
        if (map.containsKey(key)) {
            map.get(key).remove(value);
            if (map.get(key).isEmpty()) {
                map.remove(key);
            }
        }
    }
    public void removeAll(K key) {
        map.remove(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public boolean containsValue(K key, V value) {
        return map.containsKey(key) && map.get(key).contains(value);
    }

    public List<K> keys() {
        return new ArrayList<>(map.keySet());
    }

    public List<V> values() {
        List<V> allValues = new ArrayList<>();
        for (List<V> valueList : map.values()) {
            allValues.addAll(valueList);
        }
        return allValues;
    }

    public void clear() {
        map.clear();
    }

    public void print() {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " Values: " + entry.getValue());
        }
    }
}