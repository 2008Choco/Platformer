package wtf.choco.platformer.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import wtf.choco.platformer.client.render.RenderManager;
import wtf.choco.platformer.entity.Enemy;
import wtf.choco.platformer.entity.Entity;
import wtf.choco.platformer.entity.Player;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.tile.Tiles;
import wtf.choco.platformer.utils.ImageUtils;
import wtf.choco.platformer.utils.Location;
import wtf.choco.platformer.utils.TilePos;

public class Level {

    private static final Map<String, Level> LEVELS = new HashMap<>();

    public final Random random;

	private final String name;
	private final Map<TilePos, Tile> tiles = new HashMap<>();
	private final LevelEntityTracker entityTracker;

	public Level(String name) {
		this.name = name;
		this.random = new Random();
		this.entityTracker = new LevelEntityTracker(this);
	}

    public String getName() {
        return name;
    }

    public LevelEntityTracker getEntityTracker() {
        return entityTracker;
    }

    public void setTile(TilePos pos, Tile tile) {
        this.tiles.put(pos, tile);
    }

    public void setTile(int x, int y, Tile tile) {
        this.setTile(new TilePos(x, y), tile);
    }

	public Tile getTileAt(TilePos pos) {
		return tiles.getOrDefault(pos, Tiles.AIR);
	}

	public Tile getTileAt(int x, int y) {
	    return getTileAt(new TilePos(x, y));
	}

    public void render(Graphics graphics) {
        this.tiles.forEach((pos, tile) -> RenderManager.getRenderer(tile).render(graphics, pos));
        this.entityTracker.forEach(e -> RenderManager.getRenderer(e.getClass()).render(graphics, e));
    }

	public void tick() {
	    this.tiles.forEach((pos, tile) -> tile.tick(this, pos));

	    for (Entity entity : entityTracker) {
            entity.tick();
            entity.setVelocityY(entity.getVelocityY() + entity.getGravity());

            // TODO: COLLISION
            //    - if collision is not available, offset the player according to their velocity
	    }
	}

	public static Level create(String name, BufferedImage levelImage) {
	    Level level = new Level(name);

        for (int x = 0; x < levelImage.getWidth(); x++) {
            for (int y = 0; y < levelImage.getHeight(); y++) {
                int pixel = levelImage.getRGB(x, y);

                /*
                 * Tile Colours Player: (0, 0, 255) Enemy: (255, 0, 0)
                 * Dirt: (165, 85, 0) Grass: (103, 163, 0) Stone: (97, 93, 89) Bush: (0, 105, 5)
                 */
                if (ImageUtils.isColor(pixel, 0, 0, 255)) {
                    level.entityTracker.addEntity(new Player(new Location(x, y)));
                }
                else if (ImageUtils.isColor(pixel, 255, 0, 0)) {
                    level.entityTracker.addEntity(new Enemy(new Location(x, y)));
                }
                else if (ImageUtils.isColor(pixel, 165, 85, 0)) {
                    level.setTile(x, y, Tiles.DIRT);
                }
                else if (ImageUtils.isColor(pixel, 103, 163, 0)) {
                    level.setTile(x, y, Tiles.GRASS);
                }
                else if (ImageUtils.isColor(pixel, 97, 93, 89)) {
                    level.setTile(x, y, Tiles.STONE);
                }
                else if (ImageUtils.isColor(pixel, 0, 105, 5)) {
                    level.setTile(x, y, Tiles.BUSH);
                }
            }
        }

        LEVELS.put(name, level);
        return level;
	}

	public static Level getByName(String name) {
	    return LEVELS.get(name);
	}

}