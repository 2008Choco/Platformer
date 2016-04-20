package me.choco.game.menus;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import me.choco.game.Game.GameState;

public class MenuManager {
	
	private Set<GameMenu> menus = new HashSet<>();
	
	public MenuManager(GameMenu... menus){
		for (GameMenu menu : menus)
			this.menus.add(menu);
	}
	
	public void tickForState(GameState requiredState){
		for (GameMenu menu : menus){
			if (menu.getRequiredGameState().equals(requiredState)){
				menu.tick();
			}
		}
	}
	
	public void renderForState(GameState requiredState, Graphics g){
		for (GameMenu menu : menus){
			if (menu.getRequiredGameState().equals(requiredState)) menu.render(g);
		}
	}
	
	public Set<GameMenu> getMenus(){
		return menus;
	}
	
	public void addMenu(GameMenu menu){
		this.menus.add(menu);
	}
}