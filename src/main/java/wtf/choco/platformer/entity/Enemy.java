package wtf.choco.platformer.entity;

import java.awt.Color;

import wtf.choco.platformer.level.Level;
import wtf.choco.platformer.utils.Location;

public class Enemy extends Entity {

	public Enemy(Level level, Location location) {
		super(level, location, 1, 1);
        this.setColour(new Color(255, 0, 0));
	}

}