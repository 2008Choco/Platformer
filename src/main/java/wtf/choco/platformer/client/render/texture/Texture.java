package wtf.choco.platformer.client.render.texture;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import wtf.choco.platformer.utils.ImageUtils;
import wtf.choco.platformer.utils.LazySupplier;

public class Texture {

    private static final Map<String, Texture> TEXTURE_BUFFER = new HashMap<>();


	public static final Texture GUI_BACKGROUND_MAIN = fetch("/textures/gui/background/background.png");
	public static final Texture GUI_BUTTON_BACKGROUND = fetch("/textures/gui/button/woodButton.png");
	public static final Texture GUI_BUTTON_HOVERED = fetch("/textures/gui/button/woodButtonHovered.png");

	public static final Texture GENERIC_ICON = fetch("/textures/generic/icon.png");


    private final LazySupplier<BufferedImage> textureSupplier;

	private Texture(String path) {
	    this.textureSupplier = new LazySupplier<>(() -> ImageUtils.loadImage(path));
	}

	public BufferedImage asImage() {
		return textureSupplier.get();
	}

	public static Texture fetch(String path) {
	    return TEXTURE_BUFFER.computeIfAbsent(path, Texture::new);
	}

}