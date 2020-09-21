package wtf.choco.platformer.utils;

import java.awt.Graphics;

public final class NumberUtils {

    private NumberUtils() { }

	public static int clamp(int value, int min, int max) {
		if (value >= max) return max;
		if (value <= min) return min;
		return value;
	}

	public static boolean isIn(int x, int y, int minX, int minY, int maxX, int maxY) {
		return x > minX && x < maxX && y > minY && y < maxY;
	}

	public static int center(Graphics graphics, String text) {
		return graphics.getFontMetrics().stringWidth(text) / 2;
	}

}