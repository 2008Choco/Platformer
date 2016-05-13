
package me.choco.game.world;

public class Location {
	
	// Every "x" pixels is 1 coordinate
	public static final int PIXEL_RATIO = 32;
	
	/* =- Variables -=
	 *     x: X position relative to the world
	 *     y: Y position relative to the world
	 */
	
	private float x, y;
	public Location(float x, float y){
		this.x = x * PIXEL_RATIO;
		this.y = y * PIXEL_RATIO;
	}
	
	public void setX(float x){
		this.x = x * PIXEL_RATIO;
	}
	
	public float getX(){
		return x / PIXEL_RATIO;
	}
	
	public void setRawX(int x){
		this.x = x;
	}
	
	public int getRawX(){
		return (int) x;
	}
	
	public int getTileX(){
		return (int) Math.floor(getX());
	}
	
	public void setY(float y){
		this.y = y * PIXEL_RATIO;
	}
	
	public float getY(){
		return y / PIXEL_RATIO;
	}
	
	public void setRawY(int y){
		this.y = y;
	}
	
	public int getRawY(){
		return (int) y;
	}
	
	public int getTileY(){
		return (int) Math.floor(getY());
	}
	
	public Location clone(){
		return new Location(x, y);
	}
	
	public double distance(Location location){
		return Math.sqrt(Math.pow((location.y - y), 2) + Math.pow((location.x - x), 2));
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Location)) return false;
		Location loc = (Location) object;
		return (x == loc.getX() && y == loc.getY());
	}
	
	@Override
	public String toString() {
		return "Location:{x:" + getX() + ",y:" + getY() + ",rawX:" + getRawX() + ",rawY:" + getRawY() + "}";
	}
}