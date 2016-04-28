package me.choco.game.utils.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.choco.game.Game;
import me.choco.game.menus.GameMenu;
import me.choco.game.menus.MenuManager;

public class ClickListener implements MouseListener{
	
	Game game;
	MenuManager manager;
	public ClickListener(Game game){
		this.game = game;
		this.manager = game.getMenuManager();
	}

	@Override /* Pressed and unpressed */
	public void mouseClicked(MouseEvent e){
		for (GameMenu menu : manager.getMenus()){
			if (game.getState().equals(menu.getRequiredGameState())){
				menu.onClickMouse(e);
				break;
			}
		}
	}

	@Override /* Mouse is pressed down */
	public void mousePressed(MouseEvent e) { 
		
	}

	@Override /* Mouse is released */
	public void mouseReleased(MouseEvent e) { 
		
	}
	
	
	/* - - - - - UNUSED METHODS - - - - - */
	public void mouseEntered(MouseEvent e) {} /* Mouse enters a JFrame component */
	public void mouseExited(MouseEvent e) {} /* Mouse exits a JFrame component */
}