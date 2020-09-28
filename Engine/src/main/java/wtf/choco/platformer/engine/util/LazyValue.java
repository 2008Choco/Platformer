package wtf.choco.platformer.engine.util;

import java.util.function.Supplier;

/**
 * A value that is initialized only when initially fetched.
 *
 * @param <T> the type of value
 */
public final class LazyValue<T> {

    private T value;
    private boolean fetched = false;

    private final Supplier<T> valueSupplier;

    /**
     * Construct a new lazy value initialized by the given supplier.
     *
     * @param valueSupplier the value supplier
     */
    public LazyValue(Supplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    /**
     * Get the value.
     *
     * @return the value
     */
    public T get() {
        if (!fetched) {
            this.value = valueSupplier.get();
            this.fetched = true;
        }

        return value;
    }

}
