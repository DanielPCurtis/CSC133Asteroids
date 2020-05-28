package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class TurnMLRightCmd extends Command{
	private GameWorld gw;
	
	public TurnMLRightCmd(GameWorld gw) {
		super("Turn MissileLauncher Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.turnLauncherRight();
	}

}
