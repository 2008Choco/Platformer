package wtf.choco.platformer.tile;

public class Tiles {

    public static final Tile AIR = register(new Tile("air"));

    public static final Tile GRASS = register(new Tile("grass"));
    public static final Tile DIRT = register(new Tile("dirt"));
    public static final Tile STONE = register(new Tile("stone"));
    public static final Tile BUSH = register(new TileBush("bush"));

    private static Tile register(Tile tile) {
        // TODO: Register the tile
        return tile;
    }

}
