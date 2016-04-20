package me.choco.game.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.choco.game.Game;
import me.choco.game.Game.GameState;
import me.choco.game.utils.general.GameFont;
import me.choco.game.utils.general.ImageLoader;

public class OptionsMenu extends GameMenu{
	
	private static BufferedImage image = ImageLoader.loadImage("/bunny.png");

	@Override
	public void tick(){
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.setFont(GameFont.COMICSANSMS_BOLD_29.getFont());
		g.drawString("OPTIONS SCREEN", (Game.WIDTH / 2) - (g.getFontMetrics().stringWidth("OPTIONS SCREEN") / 2), (Game.HEIGHT/2) - 50);
		g.drawString("WORK IN PROGRESS", (Game.WIDTH / 2) - (g.getFontMetrics().stringWidth("WORK IN PROGRESS") / 2), (Game.HEIGHT/2));
		g.drawString("ESC TO RETURN", (Game.WIDTH / 2) - (g.getFontMetrics().stringWidth("ESC TO RETURN") / 2), (Game.HEIGHT/2) + 50);
		g.drawImage(image, (Game.WIDTH / 2) - (image.getWidth() / 2), (Game.HEIGHT/2) + 75, image.getWidth(), image.getHeight(), null);
	}
	
	@Override
	public GameState getRequiredGameState() {
		return GameState.OPTIONS_MENU;
	}
}