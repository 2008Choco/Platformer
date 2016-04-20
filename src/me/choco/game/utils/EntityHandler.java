package me.choco.game.utils;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import me.choco.game.entity.Entity;
import me.choco.game.entity.GameObject;

public class EntityHandler {
	
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
}