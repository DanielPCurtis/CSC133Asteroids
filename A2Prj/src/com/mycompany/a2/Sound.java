package com.mycompany.a2;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media med;
	
	public Sound(String fileName) {
		
		try
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			med = MediaManager.createMedia(is, "audio/wav");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void play() 
	{
		//start playing the sound from time zero (beginning of the sound file)
		med.setTime(0);
		med.play();
	}
}


