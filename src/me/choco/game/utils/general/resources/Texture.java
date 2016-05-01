package me.choco.game.utils.general.resources;

import java.awt.image.BufferedImage;

import me.choco.game.utils.general.ImageLoader;

public enum Texture {
	GUI_BACKGROUND_MAIN(ImageLoader.loadImage("/background.png")),
	GUI_BUTTON_BACKGROUND(ImageLoader.loadImage("/woodButton.png")),
	GUI_BUTTON_HOVERED(ImageLoader.loadImage("/woodButtonHovered.png")),
	GENERIC_ICON(ImageLoader.loadImage("/icon.png"));
	
	BufferedImage texture;
	Texture(BufferedImage texture){
		this.texture = texture;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
}