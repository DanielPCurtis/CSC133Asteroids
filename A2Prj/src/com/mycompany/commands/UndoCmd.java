package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class UndoCmd extends Command{
	GameWorld gw;
	
	public UndoCmd(GameWorld gw) {
		super("Undo");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Undo has been pressed");
	}

}
