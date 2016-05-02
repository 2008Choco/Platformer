package me.choco.game.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import me.choco.game.Game;
import me.choco.game.entity.Entity;
import me.choco.game.entity.GameObject;
import me.choco.game.entity.ObjectType;
import me.choco.game.entity.Player;
import me.choco.game.utils.general.GameFont;

public class EntityHandler {
	
	private Game game;
	public EntityHandler(Game game){
		this.game = game;
	}
	
	private Set<GameObject> objects = new HashSet<>();
	
	public void tick(){
		Iterator<GameObject> it = objects.iterator();
		while (it.hasNext()){
			GameObject object = it.next();
			object.tick();
			
			if (object instanceof Entity){
				Entity entity = (Entity) object;
				for (GameObject ob : objects){
					if (!(ob instanceof Entity)) return;
					if (entity.collidesWith(((Entity) ob))){
						//TODO: Collision detected between entities
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		objects.forEach(o -> o.render(g));
		
		if (game.isInDebugMode()){
			Player player = (Player) getFirst(ObjectType.PLAYER);
			if (player != null){
				g.setFont(GameFont.ARIAL_BOLD_16.getFont());
				g.setColor(Color.YELLOW);
				
				 String x = "World X: " + player.getLocation().getX(),
						y = "World Y: " + player.getLocation().getY(),
						scX = "Screen X: " + player.getLocation().getScreenX(),
						scY = "Screen Y: " + player.getLocation().getScreenY();
				
				g.drawString(x, Game.WIDTH - (g.getFontMetrics().stringWidth(x) + 20), 15);
				g.drawString(y, Game.WIDTH - (g.getFontMetrics().stringWidth(y) + 20), 30);
				g.drawString(scX, Game.WIDTH - (g.getFontMetrics().stringWidth(scX) + 20), 45);
				g.drawString(scY, Game.WIDTH - (g.getFontMetrics().stringWidth(scY) + 20), 60);
			}
		}
	}
	
	public Set<GameObject> getObjects(){
		return objects;
	}
	
	public void addObject(GameObject object){
		objects.add(object);
	}
	
	public void removeObject(GameObject object){
		objects.remove(object);
	}
	
	public GameObject getFirst(ObjectType type){
		Iterator<GameObject> it = objects.iterator();
		while (it.hasNext()){
			GameObject object = it.next();
			if (object.getType().equals(type)) return object;
		}
		return null;
	}
}