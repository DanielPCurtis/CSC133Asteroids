package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class DecreaseSpeedCmd extends Command{
	private GameWorld gw;
	
	public DecreaseSpeedCmd(GameWorld gw) {
		super("Decrease Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)	{
		gw.decreaseSpeed();
	}
	

}
