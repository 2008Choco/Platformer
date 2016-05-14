package me.choco.game.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import me.choco.game.Game;
import me.choco.game.entity.Enemy;
import me.choco.game.entity.Player;
import me.choco.game.utils.general.ImageUtils;
import me.choco.game.world.Level;
import me.choco.game.world.Location;
import me.choco.game.world.TileType;

public class LevelManager {
	
	private final List<Level> levels = new ArrayList<>();
	private Level currentLevel = null;
	
	private Game game;
	public LevelManager(Game game){
		this.game = game;
	}
	
	public void loadLevel(String name, BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		
		Level level = new Level(name, width, height, Location.PIXEL_RATIO);
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				int pixel = image.getRGB(x, y);
				
				/* Tile Colours (Format: "R,G,B")
				 *     Player: 0,0,255
				 *     Enemy: 255,0,0
				 *     Dirt: 165,85,0
				 *     Grass: 103,163,0
				 *     Stone: 97,93,89
				 */
				if (ImageUtils.isColor(pixel, 0, 0, 255)) game.getEntityHandler().addEntity(new Player(new Location(x, y)));
				else if (ImageUtils.isColor(pixel, 255, 0, 0)) game.getEntityHandler().addEntity(new Enemy(new Location(x, y)));
				else if (ImageUtils.isColor(pixel, 165, 85, 0)) level.setTile(new Location(x, y), TileType.DIRT);
				else if (ImageUtils.isColor(pixel, 103, 163, 0)) level.setTile(new Location(x, y), TileType.GRASS);
				else if (ImageUtils.isColor(pixel, 97, 93, 89)) level.setTile(new Location(x, y), TileType.STONE);
			}
		}
		levels.add(level);
	}
	
	public Level getLevel(int index){
		return levels.get(index);
	}
	
	public void setCurrentLevel(int index){
		setCurrentLevel(getLevel(index));
	}
	
	public void setCurrentLevel(Level level){
		this.currentLevel = level;
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}
	
	public void tick(){
		if (currentLevel == null) return;
		currentLevel.tick();
	}
	
	public void render(Graphics g){
		if (currentLevel == null) return;
		currentLevel.render(g);
	}
}