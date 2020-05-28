package com.mycompany.a2;

import java.util.Observable;
/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class GameWorldProxy extends Observable implements IGameWorld {
	private GameWorld gw;
	/**
	 *	passing a GameWorldProxy to the Observers
	 * @param gw 
	 */
	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
	}
	/**
	 * 
	 */
	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return gw.getPlayerScore();
	}
	/**
	 * 
	 */
	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		return gw.getMissileCount();
	}
	/**
	 * 
	 */
	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return gw.getIterator();
	}
	/**
	 * 
	 */
	@Override
	public int getElapsedTime() {
		// TODO Auto-generated method stub
		return gw.getElapsedTime();
	}
	/**
	 * 
	 */
	@Override
	public boolean getSoundToggle() {

		return gw.getSoundToggle();
	}
	/**
	 * 
	 */
	public String getSound()	{
		
		return gw.getSound();
	}
	/**
	 * 
	 */
	@Override
	public int getLivesRemaining() {

		return gw.getLivesRemaining();
	}

}
