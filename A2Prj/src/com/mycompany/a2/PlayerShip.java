package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
/**
 * @author Daniel Curtis and Curtis Botonis
 * @This class Inherits from GameObject, MoveableObject and Ship
 * Instantiates a Player with location in center of map and color from GameObject,
 * speed and direction are 0 and 90. Also instantiates a PlayerMissileLauncher that lives/dies with the Ship.
 */
public class PlayerShip extends Ship implements ISteer, IDrawable, ICollider	{
	private PlayerMissileLauncher playerLauncher;
	private double h = GameWorld.getGameHeight()/2;
	private double w = GameWorld.getGameWidth()/2;
	private boolean setRemove = false;
	private boolean psAlive = true;
	/**
	 * Constructs a PlayerShip
	 */
	public PlayerShip()	{
		Point2D center = new Point2D(w,h);
		this.setLocation(center);
		this.setColor(ColorUtil.GREEN);
		this.setDirection(90);
		this.setSpeed(0);
		this.setMissileCount(10);
		playerLauncher = new PlayerMissileLauncher(center, this.getSpeed(), this.getDirection());
		System.out.println("A PlayerShip has been created");
	}
	/**
	 * Increases speed of Ship by 1
	 */
	public void increaseSpeed()	{
		if(this.getSpeed() == 40) {
			setSpeed(40);
			System.out.println("Error: Ship is at full speed");
			
		}
		else {
			setSpeed(this.getSpeed() + 1);
		}
		playerLauncher.setSpeed(this.getSpeed());	
	}
	/**
	 * Decreases speed of Ship by 1
	 */
	public void decreaseSpeed()	{
		if(this.getSpeed() == 0) {
			setSpeed(0);
		System.out.println("Error: If you went any slower you would be going backwards");
		}
		else
			setSpeed(this.getSpeed() - 1);
		System.out.println("PlayerShip is going slower");
	}
	/**
	 * Reloads ships Missiles to a max value of 10
	 */
	public void reloadMissiles()	{
		final int MAX_MISSILES = 10;
		this.setMissileCount(MAX_MISSILES);
		System.out.println("Missiles have been reloaded");
	}
	/**
	 * Turns ship left 2 degrees
	 */
	public void steerLeft() {
		if(this.getDirection() >= 10) 
		{
			this.setDirection(getDirection() - 10);
			if(getLauncherDirection() >= 10) 
			{
				playerLauncher.setDirection(getLauncherDirection() - 10);
			}
			else {playerLauncher.setDirection(getLauncherDirection() + 350);}
		}
		else 
		{
			this.setDirection(this.getDirection() + 350);
			if(getLauncherDirection() >= 10) 
			{
				playerLauncher.setDirection(getLauncherDirection() - 10);
			}
				else {playerLauncher.setDirection(getLauncherDirection() + 350);}
		}
		System.out.println("The PlayerShip has turned left");
	}
	/**
	 * Turns ship right 2 degrees
	 */
	public void steerRight() {
		if (this.getDirection() <= 349) 
		{
			this.setDirection(getDirection() + 10);
			if (getLauncherDirection() <= 349) 
			{
				playerLauncher.setDirection(getLauncherDirection() + 10);
			}
			else {playerLauncher.setDirection(getLauncherDirection() - 350);}
		}
		else {
			this.setDirection(this.getDirection() - 350);
			if (getLauncherDirection() <= 349) 
			{
				playerLauncher.setDirection(getLauncherDirection() + 10);
			}
			else {playerLauncher.setDirection(getLauncherDirection() - 350);}
		}
		System.out.println("The PlayerShip has turned right");
	}
	/**
	 * @return Int direction of PlayerMissileLauncher
	 */
	public int getLauncherDirection()	{
		return playerLauncher.getDirection();
	}
	/**
	 * Turns PlayerMissileLauncher 2 degrees clockwise
	 */
	public void revolveLauncher()	{
		playerLauncher.steerRight();
	}
	
	/**
	 * Turns PlayerMissile Launcher 2 degrees counterclockwise
	 */
	public void revolveLauncherLeft()	{
		playerLauncher.steerLeft();
	}
	/**
	 * Return PlayerShip to Center of the screen
	 */
	public void returnToCenter()	{
		Point2D center = new Point2D(w,h);
		this.setLocation(center);
	}
	
	public String toString()	{
		String topOne = super.toString();
		String thisOne = " Missile launcher dir=" + getLauncherDirection();
		return "PlayerShip: " + topOne+ thisOne;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		g.setColor(this.getColor());
		g.fillArc(x - 25, y - 25, 50, 50, 0, 360);
		playerLauncher.setLocation(this.getLocation());
		playerLauncher.draw(g, pCmpRelPrnt);
	}
	@Override
	public boolean collidesWith(ICollider otherObj) {
		
		boolean collision = false;
		double thisX = this.getLocation().getX();
		double thisY = this.getLocation().getY();
		
		double thatX = ((GameObject)otherObj).getLocation().getX();
		double thatY = ((GameObject)otherObj).getLocation().getY();
		
		double dsqr = ((thisX - thatX)*(thisX - thatX))  + ((thisY - thatY)*(thisY - thatY));
		
		
		int rad1= this.getSize() / 2;
		int rad2= ((GameObject)otherObj).getSize() / 2;
		
		int radSqr= ((rad1+rad2)*(rad1+rad2));
		
		if (dsqr <= radSqr) { collision = true ; }
		
		return collision;
	}
	
	/**
	 * reloads PS missiles if it comes in contact with Space station
	 */
	@Override
	public void handleCollision(ICollider otherObj){
		
		/*	if (otherObj instanceof Asteroid)
			{
				this.setRemove();
				otherObj.setRemove();
				setDead();
				
			}
			else if (otherObj instanceof NonPlayerShip)
			{
				this.setRemove();
				otherObj.setRemove();
				setDead();
			}
			else if( otherObj instanceof Missile && ((Missile) otherObj).getMissileType()==false) {
				this.setRemove();
				otherObj.setRemove();
				setDead();
			}*/
			
			 if (otherObj instanceof SpaceStation)
			{
				this.reloadMissiles();
				
			}
			
		
		}
	

	@Override
	public void setRemove() {
		setRemove = true;
		
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 100;
	}
	@Override
	public boolean getRemove() {
		return setRemove;
	}
	
	public void setDead() {
		psAlive = false;
	}
	public boolean getDead() {
		return psAlive;
	}
}
