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
import me.choco.game.entity.variants.Gravitational;
import me.choco.game.utils.general.GameFont;
import me.choco.game.world.Level;
import me.choco.game.world.Tile;

public class EntityHandler {
	
	private static final DecimalFormat coordFormat = new DecimalFormat("0.00");
	
	private Game game;
	public EntityHandler(Game game){
		this.game = game;
	}
	
	private final Set<Entity> entities = new HashSet<>();
	
	public void tick(){
		for (Entity entity : entities){
			entity.tick();
			
			// Gravity abstraction
			if (entity instanceof Gravitational)
				entity.setVelY(entity.getVelY() + ((Gravitational) entity).getGravityValue());
			
			// Entity Collision
			for (Entity collidableEntity : entities){
				if (collidableEntity.equals(entity) || !entity.collidesWith(collidableEntity)) continue;
				
				if (collidableEntity.collidesTop(entity)){
					entity.setAirborn(false);
					entity.getLocation().setY(collidableEntity.getLocation().getY() - 1);
					entity.setVelY(0);
				}else if (collidableEntity.collidesDown(entity)){
					entity.getLocation().setY(collidableEntity.getLocation().getY() + 1);
					entity.setVelY(0);
				}else if (collidableEntity.collidesLeft(entity)){
					entity.getLocation().setX(collidableEntity.getLocation().getX() - 1);
				}else if (collidableEntity.collidesRight(entity)){
					entity.getLocation().setX(collidableEntity.getLocation().getX() + 1);
				}
			}
			
			// Tile collision
			Level level = game.getLevelManager().getCurrentLevel();
			if (level != null){
				for (Tile tile : level.getTiles()){
					if (!tile.getTileType().isSolid()) continue;
					
					if (tile.collidesTop(entity)){
						entity.setAirborn(false);
						entity.getLocation().setY(tile.getLocation().getTileY() - 1);
						entity.setVelY(0);
					}else if (tile.collidesDown(entity)){
						entity.getLocation().setY(tile.getLocation().getTileY() + 1);
						entity.setVelY(0);
					}else if (tile.collidesLeft(entity)){
						entity.getLocation().setX(tile.getLocation().getTileX() - 1);
					}else if (tile.collidesRight(entity)){
						entity.getLocation().setX(tile.getLocation().getTileX() + 1);
					}
				}
			}
		}
	}
	
	public void render(Graphics g){
		entities.forEach(o -> o.render(g));
		
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
	
	public Set<Entity> getEntities(){
		return entities;
	}
	
	public void addEntity(Entity object){
		entities.add(object);
	}
	
	public void removeEntity(Entity object){
		entities.remove(object);
	}
	
	public GameObject getFirst(ObjectType type){
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()){
			Entity object = it.next();
			if (object.getType().equals(type)) return object;
		}
		return null;
	}
}