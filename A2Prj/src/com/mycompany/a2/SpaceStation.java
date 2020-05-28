package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * @author Daniel Curtis and Curtis Botonis
 *
 */
@SuppressWarnings("unused")

public class SpaceStation extends FixedObject implements IDrawable, ICollider{
	private int blinkRate;
	private boolean lightOn;
	private int size = 16;
	private boolean setRemove = false;
	
	/**
	 * Instantiates a SpaceStation with super's ID and adds random blinkRate of light
	 */
	public SpaceStation()	{
		final int MAX_BLINK_RATE = 6;
		blinkRate = GameObject.rand.nextInt(MAX_BLINK_RATE) + 1;
		lightOn = false;
		setColor(ColorUtil.WHITE);
		System.out.println("Space Station has been added");
	}
	/**
	 * @return Int value for SpaceStation's blinkRate
	 */	
	public int getBlinkRate() {
		return blinkRate;
	}
	/**
	 * @param newBlinkRate Int value for new blink rate of SpaceStation
	 */
	public void setBlinkRate(int newBlinkRate)	{
		if (newBlinkRate >= 1 && newBlinkRate <=6 ) {
			blinkRate = newBlinkRate;
			}
			else {System.out.println("Error: Invalid BlinkRate");}
	}
	/**
	 * Turns blinkingLight on/off
	 */
	public void switchLightOn() {
		lightOn = true;
	}
	
	public void switchLightOff() {
		lightOn = false;
	}
	public String toString()	{
		String topOne = super.toString(); 
		String thisOne = " rate=" + blinkRate;
		return "Station: " + topOne + thisOne; 
	}
	/**
	 * Draws a Space Station
	 */
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		int xpoints[] =  {x-8, x-16, x-8, x+8, x+16, x+8};
		int ypoints[] = {y+8, y, y-8, y-8, y, y+8};
		int npoints = 6;
		g.setColor(this.getColor());
		if (lightOn) {
			g.fillPolygon(xpoints, ypoints, npoints);
		}
		else {
			g.drawPolygon(xpoints, ypoints, npoints);
		}
	}
	@Override
	public boolean collidesWith(ICollider otherObj)  {
		
		boolean result = false;
		double thisX = this.getLocation().getX();
		double thisY = this.getLocation().getY();
		
		double thatX = ((GameObject)otherObj).getLocation().getX();
		double thatY = ((GameObject)otherObj).getLocation().getY();
		
		double dsqr = ((thisX - thatX)*(thisX - thatX))  + ((thisY - thatY)*(thisY - thatY));
		
		
		int rad1= this.getSize() / 2;
		int rad2= ((GameObject)otherObj).getSize() / 2;
		
		int radSqr= ((rad1+rad2)*(rad1+rad2));
		
		if (dsqr <= radSqr) { result = true ; }
		
		return result;
	}
	@Override
	public void handleCollision(ICollider otherObj) {
		// TODO Auto-generated method stub
		Sound reloadShip = new Sound("reload.wav");
		Sound reloadGrunt = new Sound("tagrunt.wav");
		reloadShip.play();
		reloadGrunt.play();
	}
	@Override
	public void setRemove() {
		setRemove = true;
		
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean getRemove() {
		return setRemove;
	}
}
