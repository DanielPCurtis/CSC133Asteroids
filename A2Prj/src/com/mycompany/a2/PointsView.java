package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class PointsView extends Container implements Observer {
	
	private Label pointsValue;
	private Label livesValue;
	private Label clockValue;
	private Label soundValue;
	private Label missileValue;
	
	
	/**
	 *  Constructor to add build PointsView observer
	 * @param gw 
	 */
	public PointsView(GameWorld gw)	{
	
	Container myContainer = new Container();
	myContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
	
	pointsValue = new Label("XXX");
	Label pointsText = new Label("Points: ");
	pointsValue.getAllStyles().setPadding(RIGHT, 3);
	pointsText.getStyle().set3DText(true, true);
	
	livesValue = new Label("X");
	livesValue.getAllStyles().setPadding(RIGHT, 3);
	Label livesText = new Label("Lives: ");
	livesText.getStyle().set3DText(true, true);
	
	clockValue = new Label("XXX");
	clockValue.getAllStyles().setPadding(RIGHT, 3);
	Label clockText = new Label("Time: ");
	clockText.getStyle().set3DText(true, true);

	soundValue = new Label("XXX");
	soundValue.getAllStyles().setPadding(RIGHT, 3);
	Label soundText = new Label("Sound: ");
	soundText.getStyle().set3DText(true, true);
	
	missileValue = new Label("XX");
	missileValue.getAllStyles().setPadding(RIGHT, 3);
	Label missileText = new Label("Missiles: ");
	missileText.getStyle().set3DText(true, true);
	
	myContainer.add(pointsText);
	myContainer.add(pointsValue);
	myContainer.add(livesText);
	myContainer.add(livesValue);
	myContainer.add(clockText);
	myContainer.add(clockValue);
	myContainer.add(soundText);
	myContainer.add(soundValue);
	myContainer.add(missileText);
	myContainer.add(missileValue);
	
	this.add(myContainer);
		 
		 
	}
	/**
	 * updated PointsView Observer
	 */
	@Override
	public void update(Observable observable, Object data) {
		GameWorldProxy myProxy = (GameWorldProxy) data;
		pointsValue.setText("" + myProxy.getPlayerScore());
		livesValue.setText("" + myProxy.getLivesRemaining());
		clockValue.setText("" + myProxy.getElapsedTime());
		soundValue.setText("" + myProxy.getSound());
		missileValue.setText("" + myProxy.getMissileCount());
		
		this.repaint();
		
	}
}
	