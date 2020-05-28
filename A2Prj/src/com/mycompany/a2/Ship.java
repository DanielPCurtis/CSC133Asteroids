package com.mycompany.a2;

/**
 * @author Daniel Curtis and Curtis Botonis
 * 
 *
 */

public abstract class Ship extends MoveableObject{
	
	private int missileCount;
	/**
	 * Adds missiles to a MoveableObject
	 */
	public Ship()	{
		super();
	}
	/**
	 * Reduces ships missile count by 1
	 */
	public int decrementMissileCount()	{
		return --missileCount;
	}
	/**
	 * @return The number of Missile's a Ship has
	 */
	public int getMissileCount()	{
		return missileCount;
	}
	/**
	 * @param newMissileCount Int value for new missile count
	 */
	public void setMissileCount(int newMissileCount)	{
		this.missileCount = newMissileCount;
	}
	
	public String toString()	{
		String topOne = super.toString();
		String thisOne = " missiles=" + getMissileCount();
		return topOne + thisOne;
	}
}
