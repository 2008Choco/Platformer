package wtf.choco.platformer.entity;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.render.RenderManager;

public class EntityHandler {

	private Game game;

	public EntityHandler(Game game) {
		this.game = game;
	}

	private final Set<Entity> entities = new HashSet<>();

	public void tick() {
		for (Entity entity : entities) {
			entity.tick();
			entity.setVelY(entity.getVelY() + entity.getGravity());

			// TODO: COLLISION
		}
	}

	public void render(Graphics graphics) {
        if (game.getLevelManager().hasCurrentLevel()) {
            this.entities.forEach(e -> RenderManager.getRenderer(e.getClass()).render(graphics, e));
        }
	}

	public Set<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity object) {
		this.entities.add(object);
	}

	public void removeEntity(Entity object) {
	    this.entities.remove(object);
	}

	public <T extends Entity> T getFirst(Class<T> type) {
	    for (Entity entity : entities) {
	        if (entity.getClass() == type) {
	            return type.cast(entity);
	        }
	    }

	    return null;
	}

}