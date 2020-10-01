package wtf.choco.platformer.registry;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a {@link Registry} that will return null if a value with the fetched
 * key does not exist.
 *
 * @param <T> the registered object type
 */
public class SimpleRegistry<T> implements Registry<T> {

    protected final Map<String, T> values = new HashMap<>();
    private final Map<T, String> keys = new IdentityHashMap<>();

    SimpleRegistry() { }

    @Override
    public T register(String key, T value) {
        this.values.put(key, value);
        this.keys.put(value, key);

        return value;
    }

    @Override
    public T get(String key) {
        return values.get(key);
    }

    @Override
    public String getId(T value) {
        return keys.get(value);
    }

    @Override
    public void clear() {
        this.values.clear();
        this.keys.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return values.values().iterator();
    }

}
