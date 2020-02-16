package wtf.choco.platformer.level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wtf.choco.platformer.entity.Entity;

public class LevelEntityTracker implements Iterable<Entity> {

    private final Level level;
    private final List<Entity> entities = new ArrayList<>();

	public LevelEntityTracker(Level level) {
	    this.level = level;
	}

	public Level getLevel() {
        return level;
    }

	public void addEntity(Entity object) {
		this.entities.add(object);
	}

	public void removeEntity(Entity object) {
	    this.entities.remove(object);
	}

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public Iterator<Entity> iterator() {
        return entities.iterator();
    }

}