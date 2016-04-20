package me.choco.game.menus.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class GUIButton {
	
	protected boolean hovered = false;
	
	protected int x, y;
	protected final int width, height;
	private final String text;
	private Color backgroundColour, hoverColour, textColour;
	public GUIButton(int x, int y, int width, int height, String text){
		this.x = x; this.y = y;
		this.width = width; this.height = height;
		this.text = text;
		this.backgroundColour = Color.GRAY;
		this.hoverColour = Color.DARK_GRAY;
		this.textColour = Color.WHITE;
	}
	
	public GUIButton(int x, int y, int width, int height, String text, Color backgroundColour, Color hoverColour, Color textColour){
		this(x, y, width, height, text);
		this.backgroundColour = backgroundColour;
		this.hoverColour = hoverColour;
		this.textColour = textColour;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public abstract void hoverMouse(MouseEvent event);
	public abstract void clickMouse(MouseEvent event);
	
	public void render(Graphics g){
		g.setColor(hovered ? hoverColour : backgroundColour);
		g.fillRect(x, y, width, height);
		g.setColor(textColour);
		g.drawString(text, x + ((width / 2) - (g.getFontMetrics().stringWidth(text) / 2)), y + ((height / 2) + (int)(g.getFontMetrics().getStringBounds(text, g).getHeight() / 4)));
	}
}