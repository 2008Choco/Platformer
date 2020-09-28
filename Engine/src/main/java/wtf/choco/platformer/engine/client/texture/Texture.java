package wtf.choco.platformer.engine.client.texture;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import wtf.choco.platformer.engine.util.ImageUtils;
import wtf.choco.platformer.engine.util.LazyValue;

/**
 * Represents a localized texture file.
 */
public class Texture {

    private static final Map<String, Texture> TEXTURE_BUFFER = new HashMap<>();

    private final LazyValue<BufferedImage> textureSupplier;

	private Texture(String path) {
	    this.textureSupplier = new LazyValue<>(() -> ImageUtils.loadImage(path));
	}

	/**
	 * Get this texture as a {@link BufferedImage}.
	 *
	 * @return the image
	 */
	public BufferedImage asImage() {
		return textureSupplier.get();
	}

	/**
	 * Fetch a {@link Texture} from the buffer or create a new one.
	 *
	 * @param path the texture path
	 *
	 * @return the texture
	 */
	public static Texture fetch(String path) {
	    return TEXTURE_BUFFER.computeIfAbsent(path, Texture::new);
	}

}