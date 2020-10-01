package wtf.choco.platformer.registry;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Represents a {@link Registry} capable of returning a default value if a value with
 * the fetched key does not exist.
 *
 * @param <T> the registered object type
 */
public final class DefaultedRegistry<T> implements Registry<T> {

    private final Map<String, T> values = new HashMap<>();
    private final Map<T, String> keys = new IdentityHashMap<>();

    private final Supplier<T> defaultSupplier;

    /**
     * Construct a new defaulted registry that will invoke the given {@link Supplier}
     * if a value is not present.
     *
     * @param defaultSupplier the default value supplier
     */
    DefaultedRegistry(Supplier<T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
    }

    @Override
    public T register(String key, T value) {
        this.values.put(key, value);
        this.keys.put(value, key);

        return value;
    }

    @Override
    public T get(String key) {
        return values.getOrDefault(key, defaultSupplier.get());
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
