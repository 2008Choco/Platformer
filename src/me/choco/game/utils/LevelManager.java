package me.choco.game.utils;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.choco.game.world.Level;

public class LevelManager {
	
	private final List<Level> levels = new ArrayList<>();
	private Level currentLevel = null;
	
	public void loadLevel(File file){
		// TODO: Parse level tile data from file
	}
	
	public Level getLevel(int index){
		return levels.get(index);
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}
	
	public void saveLevel(){
		// TODO: Serialize level tile data to file
	}
	
	public void tick(){
		if (currentLevel == null) return;
		currentLevel.tick();
	}
	
	public void render(Graphics g){
		if (currentLevel == null) return;
		currentLevel.render(g);
	}
	
	/* Level.lvl Tile data format:
	 * tile-0,0:10,10;0
	 * tile-0,10:10,10;0
	 * tile-10,20:10,10;1
	 * 
	 * This is an example .lvl tile data format. The format is as follows
	 * DATA_TYPE-x,y;width,height;typeId
	 */
}