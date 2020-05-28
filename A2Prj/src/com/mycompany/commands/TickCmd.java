package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class TickCmd extends Command {
	private GameWorld gw;
	
	public TickCmd(GameWorld gw) {
		super("GameClock Tick");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)	{
		gw.clockTicked();
	}

}
