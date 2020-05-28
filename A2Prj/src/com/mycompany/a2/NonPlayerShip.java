package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
/**
 * @author Daniel Curtis and Curtis Botonis,
 * Instantiates a NonPLayerShip and MissileLauncher that lives/dies with NonPlayerShip
 *
 */

public class NonPlayerShip extends Ship implements IDrawable{

	private int size;
	private MissileLauncher missileLauncher;
	private boolean setRemove = false;
	
	/**
     * Instantiates a NonPLayerShip with random location, direction, and speed from super class.
     * sets color and random size and instantiates a <issileLauncher
     */
	public NonPlayerShip() {
		setMissileCount(4);
		missileLauncher = new MissileLauncher(this.getLocation(), this.getSpeed(), this.getDirection());
		setColor(ColorUtil.CYAN);
		size = 15 + GameObject.rand.nextInt(10);
		System.out.println("A NonPlayerShip has been created");
	}
	/**
     * @param newSize Int value for new size,
     * sets new size of NonPlayerShip
     */
	public void setSize(int newSize)	{
		if(newSize >= 15 && newSize <= 25) {
	        this.size = newSize;
	    	}
	    	else {
	    		System.out.println("Error: Invalid size");
	    	}
	}
	/**
     * @return Size of NonPLayerShip
     */
	public int getSize()	{
		return this.size;
	}
	public String toString()	{
		String topOne = super.toString();
		String thisOne = " size=" + getSize();
		return "NonPlayerShip: " + topOne + thisOne;
	}
	/**
	 *draw NonPlayerSHip
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		g.setColor(ColorUtil.MAGENTA);
		g.fillRoundRect(x - (this.getSize()/2 +10), y-(this.getSize()/2), this.getSize()+20, this.getSize(), 25, 50);
		//g.fillRoundRect(x, y, this.getSize()/2, this.getSize()/2, 0, 180);
		missileLauncher.setLocation(this.getLocation());
		missileLauncher.draw(g, pCmpRelPrnt);
	}
	/*
	 * @Override public boolean collidesWith(ICollider otherObj) { boolean collision
	 * = false; double thisX = this.getLocation().getX(); double thisY =
	 * this.getLocation().getY();
	 * 
	 * double thatX = ((GameObject)otherObj).getLocation().getX(); double thatY =
	 * ((GameObject)otherObj).getLocation().getY();
	 * 
	 * double dsqr = ((thisX - thatX)*(thisX - thatX)) + ((thisY - thatY)*(thisY -
	 * thatY));
	 * 
	 * 
	 * int rad1= this.getSize() / 2; int rad2= ((GameObject)otherObj).getSize() / 2;
	 * 
	 * int radSqr= ((rad1+rad2)*(rad1+rad2));
	 * 
	 * if (dsqr <= radSqr) { collision = true ; }
	 * 
	 * return collision; }
	 * 
	 * @Override public void handleCollision(ICollider otherObj) { if (otherObj
	 * instanceof Asteroid) { this.setRemove(); otherObj.setRemove();
	 * 
	 * } else if (otherObj instanceof PlayerShip) { this.setRemove();
	 * otherObj.setRemove(); } else if(otherObj instanceof Missile && ((Missile)
	 * otherObj).getMissileType()==true) { this.setRemove(); }
	 * 
	 * }
	 * 
	 * @Override public void setRemove() { setRemove = true;
	 * 
	 * }
	 * 
	 * @Override public boolean getRemove() { return setRemove; }
	 */
}
