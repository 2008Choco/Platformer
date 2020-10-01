package wtf.choco.platformer.tile;

import wtf.choco.platformer.registry.Registry;

public final class Tiles {

    public static final Tile AIR = register("air", new Tile());

    public static final Tile GRASS = register("grass", new Tile());
    public static final Tile DIRT = register("dirt", new Tile());
    public static final Tile STONE = register("stone", new Tile());
    public static final Tile BUSH = register("bush", new TileBush());

    private Tiles() { }

    private static Tile register(String key, Tile tile) {
        return Registry.TILE.register(key, tile);
    }

}
