package wtf.choco.platformer.tile;

import java.awt.Rectangle;

import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.utils.TilePos;

public class Tile {

    private static final Rectangle CUBOID = new Rectangle(1, 1);

    public final boolean isEmpty() {
        return this == Tiles.AIR;
    }

    public boolean isCollidable() {
        return this != Tiles.AIR;
    }

    public Rectangle getBounds() {
        return CUBOID;
    }

    public Rectangle getBounds(int xOffset, int yOffset) {
        Rectangle bounds = (Rectangle) getBounds().clone();
        bounds.x += xOffset;
        bounds.y += yOffset;
        return bounds;
    }

    public Rectangle getBounds(TilePos location) {
        return getBounds(location.getX(), location.getY());
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

}
