package me.choco.game.utils.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import me.choco.game.Game;
import me.choco.game.menus.GameMenu;
import me.choco.game.menus.MenuManager;

public class MovementListener implements MouseMotionListener{
	
	Game game;
	MenuManager manager;
	public MovementListener(Game game){
		this.game = game;
		this.manager = game.getMenuManager();
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		for (GameMenu menu : manager.getMenus()){
			if (game.getState().equals(menu.getRequiredGameState())){
				menu.onMoveMouse(event);
			}
		}
	}
	
	
	/* - - - - - UNUSED METHODS - - - - - */
	public void mouseDragged(MouseEvent event) {}
}