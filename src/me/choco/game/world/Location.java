
package me.choco.game.world;

public class Location {
	
	/* =- Variables -=
	 *     screenX: X position on the screen
	 *     screenY: Y position on the screen
	 *     worldX: X position relative to the world
	 *     worldY: Y position relative to the world (Can exceed
	 */
	
	private int screenX, screenY, worldX, worldY;
	public Location(int x, int y){
		this.worldX = x;
		this.worldY = y;
		this.screenX = x;
		this.screenY = y;
	}
	
	public void setX(int x){
		setScreenX(screenX + (x - worldX));
		this.worldX = x;
	}
	
	public int getX(){
		return worldX;
	}
	
	public void setY(int y){
		setScreenY(screenY + (y - worldY));
		this.worldY = y;
	}
	
	public int getY(){
		return worldY;
	}
	
	public void setScreenX(int x){
		this.screenX = x;
	}
	
	public int getScreenX(){
		return screenX;
	}
	
	public void setScreenY(int y){
		this.screenY = y;
	}
	
	public int getScreenY(){
		return screenY;
	}
}