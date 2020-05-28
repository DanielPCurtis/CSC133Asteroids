package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class AddStationCmd extends Command {
	private GameWorld gw;
	
	public AddStationCmd(GameWorld gw)	{
		super("Add SpaceStation");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)	{
		gw.addSpaceStation();
	}
}
