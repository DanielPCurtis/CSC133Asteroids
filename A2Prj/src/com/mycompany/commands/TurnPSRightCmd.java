package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class TurnPSRightCmd extends Command{
	private GameWorld gw;
	
	public TurnPSRightCmd(GameWorld gw) {
		super("Turn PlayerShip Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.turnShipRight();
	}

}
