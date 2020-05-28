package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class TurnPSLeftCmd extends Command{
	private GameWorld gw;
	
	public TurnPSLeftCmd(GameWorld gw) {
		super("Turn PlayerShip Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.turnShipLeft();
	}

}
