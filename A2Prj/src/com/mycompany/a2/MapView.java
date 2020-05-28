package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer    {
    GameWorldProxy igw;
    
    /**
     * Constructor to add MapView Observer
     */
    public MapView(GameWorld gw)    {
        setLayout(new BorderLayout());
        Container centerContainer = new Container();
		centerContainer.getAllStyles().setBgTransparency(255);
		centerContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		add(BorderLayout.CENTER,centerContainer);
       
    }
    /**
     * Function to print current Map Data
     * @param obs 
     * @param obj - stores Collection of GameObjects
     */
    public void update(Observable obs, Object obj)    {
        igw = (GameWorldProxy) obj;
    	//igw.printMap();
    	this.repaint();
    }
    
    public void paint(Graphics g)	{
    	if(igw != null) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iter = igw.getIterator();
		GameObject x = null;
		while(iter.hasNext())	{
			x = iter.getNext();
			if(x instanceof IDrawable)	{
				((IDrawable) x).draw(g, pCmpRelPrnt);
				}
		}
			}
		}
    }
