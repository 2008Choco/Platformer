package me.choco.game.utils.general;

import java.awt.Font;

public enum GameFont {
	ARIAL_BOLD_16(new Font("Arial", Font.BOLD, 16)),
	COMICSANSMS_BOLD_29(new Font("Comic Sans MS", Font.BOLD, 29));
	
	private Font font;
	private GameFont(Font font){
		this.font = font;
	}
	
	public Font getFont(){
		return font;
	}
	
	public Font getModifiedFont(int style){
		return font.deriveFont(style, font.getSize());
	}
	
	public Font getModifiedFont(float size){
		return font.deriveFont(font.getStyle(), size);
	}
	
	public Font getModifiedFont(int style, int size){
		return font.deriveFont(style, size);
	}
}