package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class PSMissileHitNPSCmd extends Command{
	private GameWorld gw;
	
	public PSMissileHitNPSCmd(GameWorld gw) {
		super("PlayerShip Missile Hit NonPlayerShip");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.psMissileHitNPS();
	}

}
