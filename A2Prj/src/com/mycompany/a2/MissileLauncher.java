package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
/**
 * @author Daniel Curtis and Curtis Botonis
 * @This class Inherits from GameObject, and MoveableObject
 * Instantiates a MissileLauncher with location, direction and speed of a Ship
 * 
 */

public class MissileLauncher extends MoveableObject implements IDrawable{
	
	/**
	 * @param location location of a Ship (passed from method call in GameWorld)
	 * @param speed speed of a Ship (passed from method call in GameWorld)
	 * @param dir direction of a Ship (passed from method call in GameWorld)
	 */
    public MissileLauncher(Point2D location, int speed, int dir)    {
        this.setLocation(location);
        this.setSpeed(speed);
        this.setDirection(dir);
    }
    
	public String toString()	{
		String topOne = super.toString();
		return topOne;
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(pCmpRelPrnt.getX() + this.getXCoord());
		int y = (int)(pCmpRelPrnt.getY() + this.getYCoord());
		double newDir = Math.toRadians(getDirection());
		double newX2 = Math.cos(newDir);
		double newY2 = Math.sin(newDir);
		g.setColor(ColorUtil.BLACK);
		g.drawLine(x, y, (int)(x+(25*newX2)), (int)(y+(25 * newY2)));
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
