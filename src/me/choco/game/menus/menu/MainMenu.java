package me.choco.game.menus.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import me.choco.game.Game;
import me.choco.game.Game.GameState;
import me.choco.game.menus.GameMenu;
import me.choco.game.menus.utils.BackgroundHolder;
import me.choco.game.menus.utils.GraphicGUIButton;
import me.choco.game.utils.Background;
import me.choco.game.utils.general.GameFont;
import me.choco.game.utils.general.NumUtils;
import me.choco.game.utils.general.resources.Sound;
import me.choco.game.utils.general.resources.Texture;

public class MainMenu extends GameMenu implements BackgroundHolder{
	
	private final Background background;
	
	public MainMenu(Game game, Background background){
		this.background = background;
		
		this.registerButtons(
			new GraphicGUIButton(260, 150, 200, 50, "Play", Texture.GUI_BUTTON_BACKGROUND.getTexture(), Texture.GUI_BUTTON_HOVERED.getTexture()){
				public void moveMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						if (!this.hovered) Sound.playSound(Sound.BUTTON_HOVER);
						this.hovered = true;
					}else{ this.hovered = false; }
				}
				public void clickMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						game.setState(GameState.GAME);
						this.hovered = false;
					}
				}
				public void onUpdateWindow(ComponentEvent event) {
					this.setX((Game.WIDTH / 2) - (this.getWidth() / 2));
					this.setY(((Game.HEIGHT / 2) - (this.getHeight() / 2)) - 75);
				}
			},
			
			new GraphicGUIButton(260, 225, 200, 50, "Options", Texture.GUI_BUTTON_BACKGROUND.getTexture(), Texture.GUI_BUTTON_HOVERED.getTexture()){
				public void moveMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						if (!this.hovered) Sound.playSound(Sound.BUTTON_HOVER);
						this.hovered = true;
					}else{ this.hovered = false; }
				}
				public void clickMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						game.setState(GameState.OPTIONS_MENU);
						this.hovered = false;
					}
				}
				public void onUpdateWindow(ComponentEvent event) {
					this.setX((Game.WIDTH / 2) - (this.getWidth() / 2));
					this.setY((Game.HEIGHT / 2) - (this.getHeight() / 2));
				}
			},
			
			new GraphicGUIButton(260, 300, 200, 50, "Quit", Texture.GUI_BUTTON_BACKGROUND.getTexture(), Texture.GUI_BUTTON_HOVERED.getTexture()){
				public void moveMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						if (!this.hovered) Sound.playSound(Sound.BUTTON_HOVER);
						this.hovered = true;
					}else{ this.hovered = false; }
				}
				public void clickMouse(MouseEvent e) {
					if (NumUtils.isWithin(e.getX(), e.getY(), x, y, x + width, y + height)){
						System.exit(0);
					}
				}
				public void onUpdateWindow(ComponentEvent event) {
					this.setX((Game.WIDTH / 2) - (this.getWidth() / 2));
					this.setY(((Game.HEIGHT / 2) - (this.getHeight() / 2)) + 75);
				}
			}
		);
	}
	
	public Background getBackground(){
		return background;
	}
	
	@Override
	public void tick(){
		background.tick();
	}
	
	@Override
	public void render(Graphics g){
		background.render(g);
		g.setFont(GameFont.COMICSANSMS_BOLD_29.getFont()); g.setColor(Color.WHITE);
		g.drawString(Game.title, (Game.WIDTH / 2) - NumUtils.center(g, Game.title), (Game.HEIGHT / 2) - 125);
		
		buttons.forEach(button -> button.render(g));
	}
	
	@Override
	public GameState getRequiredGameState(){
		return GameState.MAIN_MENU; 
	}
}