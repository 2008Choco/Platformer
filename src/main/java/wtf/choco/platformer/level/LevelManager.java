package wtf.choco.platformer.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.entity.Enemy;
import wtf.choco.platformer.entity.EntityHandler;
import wtf.choco.platformer.entity.Player;
import wtf.choco.platformer.tile.Tiles;
import wtf.choco.platformer.utils.ImageUtils;
import wtf.choco.platformer.utils.Location;

public class LevelManager {

    private final List<Level> levels = new ArrayList<>();
    private Level currentLevel = null;

    private final Map<Level, Location> playerLocations = new HashMap<>();
    private final Map<Level, Set<Location>> enemiesToAdd = new HashMap<>();

    private final Game game;
    public LevelManager(Game game) {
        this.game = game;
    }

    public Level loadLevel(String name, BufferedImage image) {
        Level level = new Level(name);
        this.enemiesToAdd.put(level, new HashSet<>());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);

                /*
                 * Tile Colours (Format: "R,G,B") Player: 0,0,255 Enemy: 255,0,0
                 * Dirt: 165,85,0 Grass: 103,163,0 Stone: 97,93,89
                 */
                if (ImageUtils.isColor(pixel, 0, 0, 255)) {
                    this.playerLocations.put(level, new Location(x, y));
                }
                else if (ImageUtils.isColor(pixel, 255, 0, 0)) {
                    this.enemiesToAdd.get(level).add(new Location(x, y));
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
            }
        }

        this.levels.add(level);
        return level;
    }

    public Level getLevel(String name) {
        for (Level level : levels) {
            if (level.getName().equals(name)) {
                return level;
            }
        }

        return null;
    }

    public void setCurrentLevel(Level level) {
        EntityHandler entityHandler = game.getEntityHandler();
        entityHandler.getEntities().clear();

        if (level != null) {
            this.game.player = new Player(playerLocations.get(level));
            entityHandler.addEntity(game.player);

            for (Location location : enemiesToAdd.get(level)) {
                entityHandler.addEntity(new Enemy(location));
            }
        } else {
            this.game.player = null;
        }

        this.currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public boolean hasCurrentLevel() {
        return currentLevel != null;
    }

    public void tick() {
        if (currentLevel == null) {
            return;
        }

        this.currentLevel.tick();
    }

    public void render(Graphics graphics) {
        if (currentLevel == null) {
            return;
        }

        this.currentLevel.render(graphics);
    }
}
