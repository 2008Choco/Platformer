package me.choco.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import me.choco.game.entity.variants.Controllable;
import me.choco.game.entity.variants.Direction;
import me.choco.game.entity.variants.Gravitational;
import me.choco.game.utils.listeners.KeyboardListener;
import me.choco.game.world.Location;

public class Player extends Entity implements Controllable, Gravitational{
	
	private int speed = 3;
	private float jumpPower = 1.75f;
	
//	private static Animation walking = new Animation(5, new SpriteSheet(ImageUtils.loadImage("Sprite-Sheet-Directory.png"), 32, 32)),
// 							standing = new Animation(5, new SpriteSheet(ImageUtils.loadImage("Sprite-Sheet-Directory.png"), 32, 32));
	
	public Player(Location location){
		super(location, Location.PIXEL_RATIO, Location.PIXEL_RATIO, ObjectType.PLAYER);
	}
	
	@Override
	public void tick(){
		location.setRawX(location.getRawX() + (int) velX);
		location.setRawY(location.getRawY() + (int) velY);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.getRawX() - camera.getXOffset(), location.getRawY() - camera.getYOffset(), width, height);
		//TODO Render animation
	}
	
	@Override
	public void onPressKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyPressed(KeyEvent.VK_W) && !airborn){ 
			this.setVelY(-speed * jumpPower);
			airborn = true;
		}
		
		if (listener.isKeyPressed(KeyEvent.VK_A)){ 
			this.setVelX(-speed); 
			this.direction = Direction.LEFT;
		}
		if (listener.isKeyPressed(KeyEvent.VK_D)){ 
			this.setVelX(speed); 
			this.direction = Direction.RIGHT;
		}
	}
	
	@Override
	public void onReleaseKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyReleased(KeyEvent.VK_A)){ 
			this.setVelX(0); 
			this.direction = Direction.NEITHER;
			onPressKey(event, listener);
		}
		if (listener.isKeyReleased(KeyEvent.VK_D)){ 
			this.setVelX(0); 
			this.direction = Direction.NEITHER;
			onPressKey(event, listener); 
		}
	}
	
	@Override
	public float getGravityValue() {
		return 0.25f;
	}
}