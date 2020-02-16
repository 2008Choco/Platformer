package wtf.choco.platformer.entity;

import java.awt.Color;

import wtf.choco.platformer.utils.Location;

public class Enemy extends Entity {

	public Enemy(Location location) {
		super(location, 1, 1);
        this.setColour(new Color(255, 0, 0));
	}

}