package me.choco.game.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import me.choco.game.utils.general.ImageUtils;
import me.choco.game.world.Level;
import me.choco.game.world.Location;
import me.choco.game.world.TileType;

public class LevelManager {
	
	private final List<Level> levels = new ArrayList<>();
	private Level currentLevel = null;
	
	public void loadLevel(String name, BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		
		Level level = new Level(name, width, height, Location.PIXEL_RATIO);
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				int pixel = image.getRGB(x, y);
				
				if (ImageUtils.isColor(pixel, 255, 255, 255)){
					level.setTile(new Location(x, y), TileType.DIRT);
				}
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