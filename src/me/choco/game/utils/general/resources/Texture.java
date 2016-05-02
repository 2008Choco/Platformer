package me.choco.game.utils.general.resources;

import java.awt.image.BufferedImage;

import me.choco.game.utils.general.ImageLoader;

public enum Texture {
	GUI_BACKGROUND_MAIN(ImageLoader.loadImage("/gui/background/background.png")),
	GUI_BUTTON_BACKGROUND(ImageLoader.loadImage("/gui/button/woodButton.png")),
	GUI_BUTTON_HOVERED(ImageLoader.loadImage("/gui/button/woodButtonHovered.png")),
	GENERIC_ICON(ImageLoader.loadImage("/generic/icon.png"));
	
	BufferedImage texture;
	Texture(BufferedImage texture){
		this.texture = texture;
	}
	
	public BufferedImage getTexture(){
		if (texture == null)
			throw new IllegalStateException("Cannot provide null texture");
		return texture;
	}
}