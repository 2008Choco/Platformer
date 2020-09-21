package wtf.choco.platformer.engine.util;

import java.util.function.Supplier;

public final class LazySupplier<T> {

    private T value;
    private boolean fetched = false;

    private final Supplier<T> valueSupplier;

    public LazySupplier(Supplier<T> valueSupplier) {
        this.valueSupplier = valueSupplier;
    }

    public T get() {
        if (!fetched) {
            this.value = valueSupplier.get();
            this.fetched = true;
        }

        return value;
    }

}
