package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class SaveCmd extends Command{
	private GameWorld gw;
	
	public SaveCmd(GameWorld gw) {
		super("Save");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Save game has been pressed");
	}

}
