package wtf.choco.platformer.engine.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A set of utilities to do with image IO and colours.
 */
public final class ImageUtils {

    private ImageUtils() { }

    /**
     * Load an image from the classpath given the provided path.
     *
     * @param path the path from which to load an image
     *
     * @return the loaded image. null if none at the given path
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageUtils.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Check whether or not the given RGB integer is equivalent to the provided red,
     * green and blue values.
     *
     * @param pixel the rgb pixel
     * @param red the red value for which to check
     * @param green the green value for which to check
     * @param blue the blue value for which to check
     *
     * @return true if the colours are equal, false otherwise
     */
    public static boolean isColor(int pixel, int red, int green, int blue) {
        int pixelRed = (pixel >> 16) & 0xFF;
        int pixelGreen = (pixel >> 8) & 0xFF;
        int pixelBlue = (pixel) & 0xFF;

        return pixelRed == red && pixelGreen == green && pixelBlue == blue;
    }

}
