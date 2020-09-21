package wtf.choco.platformer.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wtf.choco.platformer.Game;

public class Background {

	private double x, y;

	private final Game game;
	private final BufferedImage backgroundImage;
	private final double deltaX, deltaY;

	public Background(Game game, BufferedImage backgroundImage, double xVector, double yVector) {
	    this.game = game;
		this.backgroundImage = backgroundImage;
		this.deltaX = xVector;
		this.deltaY = yVector;
	}

	public void reset() {
	    this.x = y = 0;
	}

	public void tick() {
		this.x += deltaX;
		this.y += deltaY;

		var window = game.getWindow();
		if (x >= window.getWidth() || x <= -window.getWidth()) {
		    this.x = 0;
		}
		if (y >= window.getHeight() || y <= -window.getHeight()) {
		    this.y = 0;
		}
	}

	public void render(Graphics graphics) {
        var window = game.getWindow();
        int width = window.getWidth(), height = window.getHeight();

		graphics.drawImage(backgroundImage, (int) x, (int) y, width, height, null);
		if (x != 0) {
		    graphics.drawImage(backgroundImage, (int) (x < 0 ? x + width : x - width), (int) y, width, height, null);
		}
		if (x != 0) {
		    graphics.drawImage(backgroundImage, (int) x, (int) (y < 0 ? y + height : y - height), width, height, null);
		}
	}
}