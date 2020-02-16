package wtf.choco.platformer.tile;

import java.awt.Rectangle;

import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.utils.TilePos;

public class Tile {

    private static final Rectangle CUBOID = new Rectangle(1, 1);

    private final String id;

    public Tile(String id) {
        this.id = id;
    }

    public final String getId() {
        return id;
    }

    public boolean isCollidable() {
        return true;
    }

    public Rectangle getBounds() {
        return CUBOID;
    }

    /**
     * Called when an entity collides with an entity. This will be called every tick such
     * that the entity and tile collide.
     *
     * @param level the level on which the collision occurred
     * @param pos the tile's position
     * @param entity the entity with which this tile collided
     */
    public void onCollide(Level level, TilePos pos, Entity entity) { }

    /**
     * Tick this tile. This method is called once every tick.
     *
     * @param level the level on which the ticking occurs
     * @param pos the tile's position
     */
    public void tick(Level level, TilePos pos) { }

}
