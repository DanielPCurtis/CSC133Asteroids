package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class AboutCmd extends Command {
	private GameWorld gw;
	
	public AboutCmd(GameWorld gw) {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("About has been pressed");
		Boolean bOk= Dialog.show("CSC 133 - Asteroids", "Written by Curtis Botonis and Daniel Curtis", "Ok", "Cancel");
	     if (bOk) {
	    	 System.out.println("Thanks for playing...");
	    }
	}

}
