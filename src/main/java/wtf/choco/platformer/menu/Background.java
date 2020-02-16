package wtf.choco.platformer.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import wtf.choco.platformer.Game;
import wtf.choco.platformer.client.Window;

public class Background {

	private double x, y;

	private final BufferedImage backgroundImage;
	private final double deltaX, deltaY;
	private final Game game;

	public Background(Game game, BufferedImage backgroundImage, double xVector, double yVector) {
	    this.game = game;
		this.backgroundImage = backgroundImage;
		this.deltaX = xVector;
		this.deltaY = yVector;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		this.x += deltaX;
		this.y += deltaY;

		Window window = game.getWindow();
		if (x >= window.getWidth() || x <= -window.getWidth()) {
		    this.x = 0;
		}
		if (y >= window.getHeight() || y <= -window.getHeight()) {
		    this.y = 0;
		}
	}

	public void render(Graphics graphics) {
        Window window = game.getWindow();
        int width = window.getWidth(), height = window.getHeight();

		graphics.drawImage(backgroundImage, (int) x, (int) y, width, height, null);
		if (x < 0) {
		    graphics.drawImage(backgroundImage, (int) x + width, (int) y, width, height, null);
		} else if (x > 0) {
		    graphics.drawImage(backgroundImage, (int) x - width, (int) y, width, height, null);
		}

		if (y < 0) {
		    graphics.drawImage(backgroundImage, (int) x, (int) y + height, width, height, null);
		} else if (y > 0) {
		    graphics.drawImage(backgroundImage, (int) x, (int) y - height, width, height, null);
		}
	}
}