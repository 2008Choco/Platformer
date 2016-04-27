
package me.choco.game.utils.tilemaps;

public class Location {
	
	private int x, y;
	public Location(int x, int y){
		this.x = x; this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
}