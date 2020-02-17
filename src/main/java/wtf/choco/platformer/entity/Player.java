package wtf.choco.platformer.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import wtf.choco.platformer.client.listener.KeyboardListener;
import wtf.choco.platformer.entity.variants.Controllable;
import wtf.choco.platformer.utils.Location;

public class Player extends Entity implements Controllable {

	private int speed = 3;
	private float jumpPower = 1.75f;

//	private static Animation walking = new Animation(5, new SpriteSheet(ImageUtils.loadImage("Sprite-Sheet-Directory.png"), 32, 32)),
// 							standing = new Animation(5, new SpriteSheet(ImageUtils.loadImage("Sprite-Sheet-Directory.png"), 32, 32));

	public Player(Location location) {
		super(location, 1, 1);
		this.setColour(new Color(0, 0, 255));
	}

	// TODO: Move elsewhere
	@Override
	public void onPressKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyPressed(KeyEvent.VK_W) && !isAirborn()){
			this.setVelocityY(-speed * jumpPower);
			this.setAirborn(true);
		}

		if (listener.isKeyPressed(KeyEvent.VK_A)){
			this.setVelocityX(-speed);
		}
		if (listener.isKeyPressed(KeyEvent.VK_D)){
			this.setVelocityX(speed);
		}
	}

	@Override
	public void onReleaseKey(KeyEvent event, KeyboardListener listener) {
		if (listener.isKeyReleased(KeyEvent.VK_A)){
			this.setVelocityX(0);
			onPressKey(event, listener);
		}
		if (listener.isKeyReleased(KeyEvent.VK_D)){
			this.setVelocityX(0);
			onPressKey(event, listener);
		}
	}

}