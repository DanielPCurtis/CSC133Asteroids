package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/**
 * @author Daniel Curtis and Curtis Botonis
 * This class Inherits from GameObject and MoveableObject,
 * Instantiates a Missile with location from Player/NonPlayerShip and color from GameObject,
 * speed and direction determined by Player/NonPlayerShip.
 */

public class Missile extends MoveableObject	implements IDrawable{
	private int fuelLevel;
	private boolean playerShip;
	private int size;
	private boolean setRemove = false;
	private int points = 0;
	
	/**
	 * Constructor creates a Missile.
	 * @param location location of Ship creating Missile
	 * @param speed speed of Ship creating Missile
	 * @param dir direction of Ship creating Missile
	 * @param ps true = PlayerShip, false = NonPlayerShip
	 */
	public Missile(Point2D location, int speed, int dir, boolean ps, int size )    {
        final int MAX_FUEL = 50;
        this.fuelLevel = MAX_FUEL;
        this.setLocation(location);
        this.setColor(ColorUtil.MAGENTA);
        this.setSpeed(speed + 20);
        this.setDirection(dir);
        playerShip = ps;
        this.size = 6;
        if(ps) { System.out.println("PlayerShip shot a missile!");}
        else { System.out.println("NonPlayerShip shot a missile!");}
	}
	/**
	 * @return True = PlayerShip Missile or  False = NonPlayerShip Missile.
	 */
	public boolean getMissileType()	{
		if(playerShip)	return true;
		
		return false;
	}
	/**
	 * Decrements Missile Fuel level
	 */
	public void decrementFuelLevel()	{
		--this.fuelLevel;
	}
	/**
	 * @return Int value for fuel level
	 */
	public int getFuelLevel()	{
		return fuelLevel;
	}
	/**
	 *@return Size of missile
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @return String representation of Missile in format 
	 * loc: location color:[R,G,B] speed: direction: fuel:
	 */
	public String toString()	{
		String topOne;
		String thisOne = " fuel=" + this.getFuelLevel();
		if(playerShip)	
			topOne = "PS's Missile: " + super.toString();
		else 
			topOne = "NPS's Missile: " + super.toString();
		
		return topOne + thisOne;
	}
	/**
	 *Draws a Misssile
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		if(playerShip) {
		double newDir = Math.toRadians(getDirection());
		double newX2 = Math.cos(newDir);
		double newY2 = Math.sin(newDir);
		x = (int)(x+(75*newX2));
		y = (int)(y+(75 * newY2));
		g.setColor(this.getColor());
		g.fillRect(x, y, 6, 6);
		}
		else {
			double newDir = Math.toRadians(getDirection());
			double newX2 = Math.cos(newDir);
			double newY2 = Math.sin(newDir);
			x = (int)(x+(25 * newX2));
			y = (int)(y+(25 * newY2));
			g.setColor(this.getColor());
			g.fillRect(x, y, 6, 6);
			
		}
	}
	/*
	 * public boolean collidesWith(ICollider other) {
	 * 
	 * boolean result = false; double thisX = this.getLocation().getX(); double
	 * thisY = this.getLocation().getY();
	 * 
	 * double thatX = ((GameObject)other).getLocation().getX(); double thatY =
	 * ((GameObject)other).getLocation().getY();
	 * 
	 * double dsqr = ((thisX - thatX)*(thisX - thatX)) + ((thisY - thatY)*(thisY -
	 * thatY));
	 * 
	 * 
	 * int rad1= this.getSize() / 2; int rad2= ((GameObject)other).getSize() / 2;
	 * 
	 * int radSqr= ((rad1+rad2)*(rad1+rad2));
	 * 
	 * if (dsqr <= radSqr) { result = true ; }
	 * 
	 * return result; } public void handleCollision(ICollider otherObj) { if
	 * (this.getMissileType() == true) { if (otherObj instanceof Asteroid) {
	 * this.setRemove(); otherObj.setRemove();
	 * 
	 * 
	 * } else if (otherObj instanceof NonPlayerShip) { this.setRemove();
	 * otherObj.setRemove(); setPoints(20);
	 * 
	 * } } else if (this.getMissileType() == false) { if (otherObj instanceof
	 * PlayerShip) { this.setRemove(); otherObj.setRemove(); } } }
	 * 
	 * public void setRemove() { setRemove = true; }
	 * 
	 * public boolean getRemove() { return setRemove; }
	 * 
	 * public void setPoints(int x) { points = x; }
	 * 
	 * public int getPoints() { return points; }
	 */
	
		
	
}
