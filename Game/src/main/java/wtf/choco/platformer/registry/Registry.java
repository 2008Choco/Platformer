package wtf.choco.platformer.registry;

import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.tile.Tiles;

/**
 * Represents a typed registry from which objects may be registered and fetched according
 * to a unique String key.
 *
 * @param <T> the registered object type
 */
public interface Registry<T> extends Iterable<T> {

    /**
     * The {@link Tile} registry.
     */
    public static final Registry<Tile> TILE = new DefaultedRegistry<>(() -> Tiles.AIR);


    /**
     * Register an object and associate it with the given key.
     *
     * @param key the key to which the value should be registered
     * @param value the value to register
     *
     * @return the registered value for convenience
     */
    public T register(String key, T value);

    /**
     * Get an object by its id. The ID is case sensitive.
     *
     * @param key the key of the object to fetch
     *
     * @return the value. Whether or not this value is nullable depends on the implementation
     * at hand. There is no guarantee of nullability.
     */
    public T get(String key);

    /**
     * Get the ID of an object in this registry.
     *
     * @param value the value whose id to fetch
     *
     * @return the object id
     */
    public String getId(T value);

    /**
     * Clear this registry.
     */
    public void clear();

}
