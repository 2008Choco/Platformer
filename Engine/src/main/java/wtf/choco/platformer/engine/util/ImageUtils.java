package wtf.choco.platformer.engine.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageUtils.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isColor(int pixel, int red, int green, int blue) {
        int pixelRed = (pixel >> 16) & 0xFF;
        int pixelGreen = (pixel >> 8) & 0xFF;
        int pixelBlue = (pixel) & 0xFF;

        return pixelRed == red && pixelGreen == green && pixelBlue == blue;
    }

}
