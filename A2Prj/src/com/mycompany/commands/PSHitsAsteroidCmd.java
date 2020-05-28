package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class PSHitsAsteroidCmd extends Command {
	private GameWorld gw;
	
	public PSHitsAsteroidCmd(GameWorld gw) {
		super("PlayerShip Hits Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.psHitAsteroid();
	}

}
