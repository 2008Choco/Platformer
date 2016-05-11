package me.choco.game.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import me.choco.game.Game;
import me.choco.game.entity.Entity;
import me.choco.game.entity.GameObject;
import me.choco.game.entity.ObjectType;
import me.choco.game.entity.Player;
import me.choco.game.utils.general.GameFont;
import me.choco.game.world.Level;
import me.choco.game.world.Tile;

public class EntityHandler {
	
	private static final DecimalFormat coordFormat = new DecimalFormat("0.00");
	
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
				
				// TODO: Entity collision
				for (GameObject ob : objects){
					if (!(ob instanceof Entity) || object.equals(ob)) continue;
					if (entity.collidesWith(((Entity) ob))){
						/* THIS IS A PROOF OF CONCEPT!!!! DELETE FOR ACTUAL ENTITY COLLISION LATER */
						entity.getLocation().setX(entity.getLocation().getX() - entity.getVelX());
						entity.getLocation().setY(entity.getLocation().getY() - entity.getVelY());
						
						entity.setVelX(0); entity.setVelY(0);
					}
				}
				
				// TODO: Tile collision
				Level level = game.getLevelManager().getCurrentLevel();
				if (level != null){
					Player player = (Player) getFirst(ObjectType.PLAYER);
					for (Tile tile : level.getTiles()){
						if (tile.collidesWith(player)){
							player.setAirborn(false);
							player.getLocation().setY(tile.getLocation().getTileY() - 1);
							player.setVelY(0);
						}
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
				
				String x = "World X: " + coordFormat.format(player.getLocation().getX()),
						y = "World Y: " + coordFormat.format(player.getLocation().getY());
				
				g.drawString(x, Game.WIDTH - (g.getFontMetrics().stringWidth(x) + 20), 15);
				g.drawString(y, Game.WIDTH - (g.getFontMetrics().stringWidth(y) + 20), 30);
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