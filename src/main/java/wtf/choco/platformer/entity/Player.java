package wtf.choco.platformer.entity;

import java.awt.Color;

import wtf.choco.platformer.utils.Location;

public class Player extends Entity {

	public Player(Location location) {
		super(location, 1, 1);
		this.setColour(new Color(0, 0, 255));
	}

}