package com.mycompany.a2;

public interface IGameWorld {
	public int getPlayerScore();
	public int getMissileCount();
	public IIterator getIterator();
	public int getElapsedTime();
	public boolean getSoundToggle();
	public String getSound();
	public int getLivesRemaining();
}
