package wtf.choco.platformer.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import wtf.choco.platformer.engine.client.render.RenderingContext;
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

    private Player player;

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

	public Player getPlayer() {
        return player;
    }

    public void render(Graphics graphics, RenderingContext context) {
        this.tiles.forEach((pos, tile) -> context.getRenderer(tile).render(graphics, pos));
        this.entityTracker.forEach(e -> context.getRenderer(e.getClass()).render(graphics, e));
    }

	public void tick() {
	    this.tiles.forEach((pos, tile) -> tile.tick(this, pos));

	    for (Entity entity : entityTracker) {
	        entity.setVelocityY(entity.getVelocityY() + entity.getGravity());
            entity.tick();

            Location currentLocation = entity.getLocation();
            Location futureLocation = currentLocation.offset(entity.getVelocityX() / 32.0F, entity.getVelocityY()  / 32.0F);
            TilePos topLeft = futureLocation.toTilePos();
            TilePos topRight = new TilePos((int) (futureLocation.getX() + 1.0 - (1 / 32.0)), (int) futureLocation.getY());
            TilePos bottomLeft = new TilePos((int) futureLocation.getX(), (int) (futureLocation.getY() + 1.0 - (1 / 32.0)));
            TilePos bottomRight = new TilePos((int) (futureLocation.getX() + 1.0 - (1 / 32.0)), (int) (futureLocation.getY() + 1.0 - (1 / 32.0)));
            Tile topLeftTile = getTileAt(topLeft);
            Tile topRightTile = getTileAt(topRight);
            Tile bottomLeftTile = getTileAt(bottomLeft);
            Tile bottomRightTile = getTileAt(bottomRight);

            if ((bottomLeftTile.isCollidable() || bottomRightTile.isCollidable())) { // Downwards collision
                futureLocation.setY((float) Math.min(bottomLeftTile.getBounds(bottomLeft).getY(), bottomRightTile.getBounds(bottomRight).getY()) - 1F);
                entity.setAirborn(false);
            } else {
                entity.setAirborn(true);
            }

            if (entity.isAirborn() && (topLeftTile.isCollidable() || topRightTile.isCollidable())) { // Upwards collision
                futureLocation.setY((float) Math.max(topLeftTile.getBounds(topLeft).getY(), topRightTile.getBounds(topLeft).getY()) + 1F);
            } else {
                if ((entity.isAirborn() && bottomLeftTile.isCollidable()) || topLeftTile.isCollidable()) { // Leftwards collision
                    futureLocation.setX((float) Math.max(topLeftTile.getBounds(topLeft).getX(), bottomLeftTile.getBounds(bottomLeft).getX()) + 1);
                } else if ((entity.isAirborn() && bottomRightTile.isCollidable()) || topRightTile.isCollidable()) { // Rightwards collision
                    futureLocation.setX((float) Math.max(topRightTile.getBounds(topRight).getX(), bottomRightTile.getBounds(bottomRight).getX()) - 1);
                }
            }

            entity.setLocation(futureLocation);
            entity.setVelocity(0, 0);
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
                    level.entityTracker.addEntity(level.player = new Player(level, new Location(x, y)));
                }
                else if (ImageUtils.isColor(pixel, 255, 0, 0)) {
                    level.entityTracker.addEntity(new Enemy(level, new Location(x, y)));
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