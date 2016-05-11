package me.choco.game.utils.general.resources;

import java.awt.image.BufferedImage;

import me.choco.game.utils.general.ImageUtils;

public enum Texture {
	GUI_BACKGROUND_MAIN(ImageUtils.loadImage("/gui/background/background.png")),
	GUI_BUTTON_BACKGROUND(ImageUtils.loadImage("/gui/button/woodButton.png")),
	GUI_BUTTON_HOVERED(ImageUtils.loadImage("/gui/button/woodButtonHovered.png")),
	
	GENERIC_ICON(ImageUtils.loadImage("/generic/icon.png")),
	
	GAME_TILE_DIRT(ImageUtils.loadImage("/game/tiles/dirt.png"));
	
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