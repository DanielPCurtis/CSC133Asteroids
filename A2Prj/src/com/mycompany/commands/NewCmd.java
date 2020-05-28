package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class NewCmd extends Command {
	private GameWorld gw;
	
	public NewCmd(GameWorld gw) {
		super("New Game");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt)	{
		System.out.println("New Game has been pressed.");
		Boolean bOk= Dialog.show("Confirm New Game", "Are you sure you want to start a new game?", "Ok", "Cancel");
	     if (bOk) {
	    	 System.out.println("Restart the Simulator on Eclipse if you want to play again...");
	    	 System.exit(0);
	    }
		
	}

}
