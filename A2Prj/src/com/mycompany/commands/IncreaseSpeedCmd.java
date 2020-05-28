package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class IncreaseSpeedCmd extends Command {
	private GameWorld gw;
	
	public IncreaseSpeedCmd(GameWorld gw) {
		super("Increase Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.increaseSpeed();
	}

}
