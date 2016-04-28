package me.choco.game.menus;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import me.choco.game.Game.GameState;
import me.choco.game.menus.utils.GUIButton;

public abstract class GameMenu {
	
	protected GameState requiredState;
	protected List<GUIButton> buttons = new ArrayList<>();
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void onClickMouse(MouseEvent e){
		for (GUIButton button : buttons)
			button.clickMouse(e);
	}
	
	public void onMoveMouse(MouseEvent e){
		for (GUIButton button : buttons)
			button.moveMouse(e);
	}
	
	public GameState getRequiredGameState(){
		return GameState.MAIN_MENU;
	}
	
	public List<GUIButton> getButtons(){
		return buttons;
	}
	
	public void registerButtons(GUIButton... buttons){
		for (GUIButton button : buttons)
			this.buttons.add(button);
	}
}