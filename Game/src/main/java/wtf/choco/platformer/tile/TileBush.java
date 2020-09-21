package wtf.choco.platformer.tile;

import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.sound.Sound;
import wtf.choco.platformer.utils.TilePos;

public class TileBush extends Tile {

    public TileBush(String id) {
        super(id);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public void onCollide(Level level, TilePos pos, Entity entity) {
        Sound.TILE_BUSH_WALK.play();
    }

}
