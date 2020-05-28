package com.mycompany.a2;

import com.codename1.ui.geom.Point2D;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;


/**
 * @author Daniel Curtis and Curtis Botonis
 * This class generates a GameObject with random Point2D location with GRAY color
 */
public abstract class GameObject	{
	/**
	 * Point2D value to store the location values (x,y)
	 */
	private Point2D location;
	/**
	 * Int value to store the color in the format [int,int,int]
	 */
	private int color;
	/**
	 * Static Random value to generate random integers
	 */
	protected static Random rand = new Random();
	
	
	/**
	 * Default constructor
	 *  set the location to a random Double within the board space
	 *  set the default color of a gameObject to Gray.
	 */
	public GameObject()	{
		final double MAX_WIDTH = GameWorld.getGameWidth();
		final double MAX_HEIGHT = GameWorld.getGameHeight();
		double myVal1 = Math.round((MAX_WIDTH*rand.nextDouble())*10) /10.0;
		double myVal2 = Math.round((MAX_HEIGHT*rand.nextDouble())*10) /10.0;
		location = new Point2D(myVal1,myVal2);
		color = ColorUtil.GRAY;
		
	}
	/**
	 * @return Point2D location
	 */
	public Point2D getLocation()	{
		return location;
	}
	/**
	 * @return X coordinate of point2D location
	 */
	public double getXCoord()	{
		return location.getX();
	}
	/**
	 * @return Y coordinate of point2D location
	 */
	public double getYCoord()	{
		return location.getY();
	}
	/**
	 * @return the color in [R,G,B] format
	 */
	public int getColor()	{
		return color; 
	}
	/**
	 * @param newCoord Point2D location
	 * sets the location of the object
	 */
	public void setLocation(Point2D newCoord)	{
		location = newCoord;
	}
	/**
	 * @param colorNum ColorUtil color 
	 * sets the COLOR of the object 
	 */
	public void setColor(int colorNum)	{
		color = colorNum;
	}
	/**
	 * @return Returns a string with the location and color of the object
	 */
	public String toString()	{
		String topOne = ("loc=" + getLocation() + " color=" + "[" + ColorUtil.red(color) + "," + 
				ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]");
		return topOne;
	}
	public abstract int getSize();
}
