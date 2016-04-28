package me.choco.game.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import me.choco.game.Game;
import me.choco.game.Game.GameState;
import me.choco.game.menus.utils.GUIButton;
import me.choco.game.utils.general.GameFont;
import me.choco.game.utils.general.NumUtils;
import me.choco.game.utils.general.sound.Sound;

public class OptionsMenu extends GameMenu{
	
	public OptionsMenu(Game game){
		registerButtons(
			new GUIButton(260, 225, 200, 50, "Return"){
				public void moveMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						if (!this.hovered) Sound.playSound(Sound.BUTTON_HOVER);
						this.hovered = true;
					}else{ this.hovered = false; }
				}
				public void clickMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						game.setState(GameState.MAIN_MENU);
						this.hovered = false;
					}
				}
				public void onUpdateWindow(ComponentEvent event) {
					this.setX((Game.WIDTH / 2) - (this.getWidth() / 2));
					this.setY((Game.HEIGHT / 2) - (this.getHeight() / 2));
				}
			}
		);
	}
	
	@Override
	public void tick(){}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.setFont(GameFont.ARIAL_BOLD_16.getModifiedFont(24F));
		g.drawString("Options are a work in progress", (Game.WIDTH / 2) - (g.getFontMetrics().stringWidth("Options are a work in progress") / 2), (Game.HEIGHT / 2) - 50);
		
		g.setFont(GameFont.COMICSANSMS_BOLD_29.getFont());
		buttons.forEach(button -> button.render(g));
	}
	
	@Override
	public GameState getRequiredGameState() {
		return GameState.OPTIONS_MENU;
	}
}