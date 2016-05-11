package me.choco.game.utils.general;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static BufferedImage loadImage(String path){
		try{
			return ImageIO.read(ImageUtils.class.getResource(path));
		}catch (IOException e){ e.printStackTrace(); }
		return null;
	}
	
	public static Color getPixelColor(int pixel){
	    int red = (pixel >> 16) & 0xff;
	    int green = (pixel >> 8) & 0xff;
	    int blue = (pixel) & 0xff;
	    return new Color(red, green, blue);
	}
	
	public static boolean isColor(int pixel, int red, int green, int blue){
		Color color = getPixelColor(pixel);
		return (color.getRed() == red && color.getGreen() == green && color.getBlue() == blue);
	}
	
	public static boolean isColor(int pixel, Color color){
		return isColor(pixel, color.getRed(), color.getGreen(), color.getBlue());
	}
}