package com.mycompany.a2;
/**
 * @author Daniel Curtis and Curtis Botonis
 *
 */

public abstract class FixedObject extends GameObject{
	private int idNumber;
	private static int nextId = 100;
	
	/**
	 * Adds an ID number to a GameObject
	 */
	public FixedObject()	{
		this.idNumber = nextId;
		++nextId;
		
	}
	/**
	 * @return An ID number
	 */
	public static int getNextId()	{
		return nextId;
	}
	
	public String toString()	{
		String topOne = super.toString();
		return topOne;
	}
	
}
