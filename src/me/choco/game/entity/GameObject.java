package me.choco.game.entity;

import java.awt.Graphics;

public abstract class GameObject {
	
	protected int x, y;
	protected ObjectType type;
	
	protected GameObject(int x, int y, ObjectType type){
		this(x, y);
		this.type = type;
	}
	
	protected GameObject(int x, int y){
		this.x = x;
		this.y = y;
		this.type = ObjectType.UNKNOWN;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ObjectType getType() {
		return type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}