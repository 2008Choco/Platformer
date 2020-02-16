package wtf.choco.platformer.level;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import wtf.choco.platformer.client.render.RenderManager;
import wtf.choco.platformer.tile.Tile;
import wtf.choco.platformer.tile.Tiles;
import wtf.choco.platformer.utils.TilePos;

public class Level {

    public final Random random;

	private final String name;
	private final Map<TilePos, Tile> tiles = new HashMap<>();

	public Level(String name) {
		this.name = name;
		this.random = new Random();
	}

	public Tile getTileAt(TilePos location) {
		return tiles.getOrDefault(location, Tiles.AIR);
	}

	public Tile getTileAt(int x, int y) {
	    return getTileAt(new TilePos(x, y));
	}

	public void setTile(int x, int y, Tile tile) {
	    this.tiles.put(new TilePos(x, y), tile);
	}

	public String getName() {
		return name;
	}

	public void tick() {
	    this.tiles.forEach((pos, tile) -> tile.tick(this, pos));
	}

	public void render(Graphics graphics) {
	    this.tiles.forEach((pos, tile) -> RenderManager.getRenderer(tile).render(graphics, pos));
	}

}