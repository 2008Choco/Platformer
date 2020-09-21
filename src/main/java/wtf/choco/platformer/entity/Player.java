package wtf.choco.platformer.entity;

import java.awt.Color;

import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.utils.Location;

public class Player extends Entity {

	public Player(Level level, Location location) {
		super(level, location, 1, 1);
		this.setColour(new Color(0, 0, 255));
	}

}