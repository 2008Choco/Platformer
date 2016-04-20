package me.choco.game.utils.tilemaps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.choco.game.Game;

public class Background {
	
	private double x, y, dx, dy;
	private BufferedImage backgroundImage;
	
	public Background(BufferedImage backgroundImage, double xVector, double yVector){
		this.backgroundImage = backgroundImage;
		this.dx = xVector; this.dy = yVector;
	}
	
	public void tick(){
		this.x += dx;
		this.y += dy;
		
		if (x == Game.WIDTH || x == Game.WIDTH * -1) x = 0;
		if (y == Game.HEIGHT || y == Game.HEIGHT * -1) y = 0;
	}
	
	public void render(Graphics g){
		g.drawImage(backgroundImage, (int) x, (int) y, Game.WIDTH, Game.HEIGHT, null);
		
		if (x < 0) g.drawImage(backgroundImage, (int) x + Game.WIDTH, (int) y, Game.WIDTH, Game.HEIGHT, null);
		else if (x > 0) g.drawImage(backgroundImage, (int) x - Game.WIDTH, (int) y, Game.WIDTH, Game.HEIGHT, null);
		if (y < 0) g.drawImage(backgroundImage, (int) x, (int) y + Game.HEIGHT, Game.WIDTH, Game.HEIGHT, null);
		else if (y > 0) g.drawImage(backgroundImage, (int) x, (int) y - Game.HEIGHT, Game.WIDTH, Game.HEIGHT, null);
	}
}