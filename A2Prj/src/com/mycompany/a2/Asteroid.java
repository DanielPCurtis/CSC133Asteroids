package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * @author Daniel Curtis and Curtis Botonis
 * This class Inherits from GameObject and MoveableObject,
 * Instantiates an Asteroid with random location and color from GameObject,
 * gets random speed and direction from MoveableObject
 */
public class Asteroid extends MoveableObject implements IDrawable{
	/**
	 * int value for size of Asteroid
	 */
	private int size;
	private boolean setRemove = false;
	private int points = 0;
	
	/**
	 * Default Asteroid constructor, gets location, speed, and direction from parent
	 * Classes, assigns a random value for size, overrides parents COLOR
	 */
	public Asteroid()	{
		final int MAX_SIZE = 30;
		final int MIN_SIZE = 6;
		size = rand.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
		this.setColor(ColorUtil.BLUE);
		System.out.println("An Asteroid has been created");
	}
	/**
	 * @return Int value for size of Asteroid
	 */
	public int getSize()	{
		return size;
	}
	/**
	 * @param newSize Int value for new size
	 */
	public void setSize(int newSize)	{
		if (newSize >= 6 && newSize <= 30) {
			size = newSize;
			}
			else {System.out.println("Error: Invalid size");}
	}
	/**
	 * @return String representation of Asteroid in format 
	 * loc: location color:[R,G,B] speed: direction: size:
	 */
	public String toString()	{
		String topOne = super.toString();
		String thisOne = " size=" + getSize();
		return "Asteroid: " + topOne + thisOne;
	}
	
	/**
	 * Draws an astreoid
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		g.setColor(this.getColor());
		g.fillRect(x, y, this.getSize(), this.getSize());
		
	}
	/*
	 * @Override public boolean collidesWith(ICollider otherObj) {
	 * 
	 * boolean collision = false; double thisX = this.getLocation().getX(); double
	 * thisY = this.getLocation().getY();
	 * 
	 * double thatX = ((GameObject)otherObj).getLocation().getX(); double thatY =
	 * ((GameObject)otherObj).getLocation().getY();
	 * 
	 * double dsqr = ((thisX - thatX)*(thisX - thatX)) + ((thisY - thatY)*(thisY -
	 * thatY));
	 * 
	 * 
	 * int rad1= this.getSize() ; int rad2= ((GameObject)otherObj).getSize() ;
	 * 
	 * int radSqr= ((rad1+rad2)*(rad1+rad2));
	 * 
	 * if (dsqr <= radSqr) { collision = true ; }
	 * 
	 * return collision; }
	 * 
	 * @Override public void handleCollision(ICollider otherObj) {
	 * 
	 * if (otherObj instanceof Asteroid) { this.setRemove(); otherObj.setRemove();
	 * 
	 * } else if (otherObj instanceof NonPlayerShip) { this.setRemove();
	 * otherObj.setRemove(); } else if (otherObj instanceof PlayerShip) {
	 * this.setRemove(); otherObj.setRemove(); } else if (otherObj instanceof
	 * Missile && ((Missile) otherObj).getMissileType() == true) { this.setRemove();
	 * otherObj.setRemove(); setPoints(10); } }
	 * 
	 * @Override public void setRemove() { setRemove = true;
	 * 
	 * }
	 * 
	 * @Override public boolean getRemove() { return setRemove; }
	 * 
	 * public void setPoints(int x) {points += x;} public int getPoints() {return
	 * points;}
	 */
	
}
