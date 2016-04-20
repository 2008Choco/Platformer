package me.choco.game.utils.general;

public class NumUtils {
	public static int clamp(int value, int min, int max){
		if (value >= max) return max;
		if (value <= min) return min;
		return value;
	}
	
	public static boolean isWithin(int x, int y, int minX, int minY, int maxX, int maxY){
		return ((x > minX && x < maxX) && (y > minY && y < maxY));
	}
}