package wtf.choco.platformer.tile;

import java.util.Random;

import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.utils.TilePos;

public interface ITickableTile {

    /**
     * Tick this tile. This method is called once every tick.
     *
     * @param level the level on which the ticking occurs
     * @param pos the tile's position
     * @param random an instance of random
     */
    public void tick(Level level, TilePos pos, Random random);

}
