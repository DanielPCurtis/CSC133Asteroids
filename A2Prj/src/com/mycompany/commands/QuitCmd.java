package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * 
 * @author Daniel Curtis and Curtis Botonis
 *
 */
public class QuitCmd extends Command{
	private GameWorld gw;
	public QuitCmd(GameWorld gw) {
		super("Quit");
		this.gw =gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt)	{
		System.out.println("Quit has been called from the Side Menu");
		gw.quit();
	}

}
