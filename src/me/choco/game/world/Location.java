
package me.choco.game.world;

public class Location {
	
	/* =- Variables -=
	 *     x: X position relative to the world
	 *     y: Y position relative to the world (Can exceed
	 */
	
	private int x, y;
	public Location(int x, int y){
		this.x = x;
		this.y = y;
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