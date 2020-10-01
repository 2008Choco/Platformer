package wtf.choco.platformer.registry;

import java.util.function.Supplier;

/**
 * Represents a {@link Registry} capable of returning a default value if a value with
 * the fetched key does not exist.
 *
 * @param <T> the registered object type
 */
public final class DefaultedRegistry<T> extends SimpleRegistry<T> {

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
    public T get(String key) {
        return values.getOrDefault(key, defaultSupplier.get());
    }

}
