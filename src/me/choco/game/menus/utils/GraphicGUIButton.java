package me.choco.game.menus.utils;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class GraphicGUIButton extends GUIButton {

	private final BufferedImage display, hoveredDisplay;
	public GraphicGUIButton(int x, int y, int width, int height, BufferedImage display, BufferedImage hoveredDisplay){
		super(x, y, width, height, null);
		if (display == null) throw new IllegalArgumentException("Display icon cannot be null");
		this.display = display;
		this.hoveredDisplay = (hoveredDisplay != null ? hoveredDisplay : display);
	}
	
	public GraphicGUIButton(int x, int y, int width, int height, String text, BufferedImage display, BufferedImage hoveredDisplay){
		super(x, y, width, height, text);
		if (display == null) throw new IllegalArgumentException("Display icon cannot be null");
		this.display = display;
		this.hoveredDisplay = (hoveredDisplay != null ? hoveredDisplay : display);
	}
	
	public BufferedImage getDisplayImage(){
		return display;
	}
	
	public BufferedImage getHoveredDisplayImage(){
		return hoveredDisplay;
	}

	public abstract void moveMouse(MouseEvent event);
	public abstract void clickMouse(MouseEvent event);
	public abstract void onUpdateWindow(ComponentEvent event);
	
	@Override
	public void render(Graphics g) {
		g.drawImage((hovered ? hoveredDisplay : display), x, y, width, height, null);
		
		if (text != null){
			g.setColor(textColour);
			g.drawString(text, x + ((width / 2) - (g.getFontMetrics().stringWidth(text) / 2)), y + ((height / 2) + (int)(g.getFontMetrics().getStringBounds(text, g).getHeight() / 4)));
		}
	}
}
