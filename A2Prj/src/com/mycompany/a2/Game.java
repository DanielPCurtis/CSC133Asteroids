package com.mycompany.a2;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.commands.*;

public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private Toolbar sideMenu;
	private Container cont;
	private int h;
	private int w;
	
	//Button declarations
	private GameButton addAsteroidButton;
	private GameButton addNPSButton;
	private GameButton addStationButton;
	private GameButton addPSButton;
	private GameButton psShootButton;
	private GameButton jumpButton;
	
	
	//Command Declarations
	private AddAsteroidCmd addAsteroidCmd;
	private AddNPSCmd addNPSCmd;
	private AddPSCmd addPSCmd;
	private AddStationCmd addStationCmd;
	private PSFireCmd psFireCmd;
	private JumpCmd jumpCmd;
	private AsteroidHitsNPSCmd asteroidHitsNPSCmd;
	private DecreaseSpeedCmd  decreaseSpeedCmd;
	private IncreaseSpeedCmd increaseSpeedCmd;
	private NPSFireCmd npsFireCmd;
	private NPSMissileHitPSCmd npsMissileHitPSCmd;
	private PSHitsAsteroidCmd psHitsAsteroidCmd;
	private PSHitsNPSCmd psHitsNPSCmd;
	private PSMissileHitAsteroidCmd psMissileHitAsteroidCmd;
	private PSMissileHitNPSCmd psMissileHitNPSCmd;
	private ReloadPSCmd reloadPSCmd;
	private TickCmd tickCmd;
	private TurnMLLeftCmd turnMLLeftCmd;
	private TurnMLRightCmd turnMLRightCmd;
	private TurnPSLeftCmd turnPSLeftCmd;
	private TurnPSRightCmd turnPSRightCmd;
	private TwoAsteroidsCollideCmd twoAsteroidsCollideCmd;
	
	
	
	
	
	
public Game() {
	
	gw = new GameWorld();
	mv = new MapView(gw);
	pv = new PointsView(gw);
	//gw.setGameWidth(mv.getWidth());
	//gw.setGameHeight(mv.getHeight());
	
	gw.init();
	setTitle("This is the Asteroids knock off");
	
	setLayout(new BorderLayout());
	//this.getStyle().setBgColor(ColorUtil.YELLOW);
	add(BorderLayout.NORTH, pv);
	gw.addObserver(pv);
	add(BorderLayout.CENTER, mv);
	gw.addObserver(mv);
	
	
	cont = new Container();
	cont.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
	cont.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
	
	//Add Asteroid
	addAsteroidButton = new GameButton("+ Asteroid");
	addAsteroidCmd = new AddAsteroidCmd(gw);
	addAsteroidButton.setCommand(addAsteroidCmd);
	cont.add(addAsteroidButton);
	//Add NonPlayerShip
	addNPSButton = new GameButton("+ NPS");
	addNPSCmd = new AddNPSCmd(gw);
	addNPSButton.setCommand(addNPSCmd);
	cont.add(addNPSButton);
	//Add PLayerShip
	addPSButton = new GameButton("+ PS");
	addPSCmd = new AddPSCmd(gw);
	addPSButton.setCommand(addPSCmd);
	cont.add(addPSButton);
	//Add SpaceStation
	addStationButton = new GameButton("+ Space Station");
	addStationCmd = new AddStationCmd(gw);
	addStationButton.setCommand(addStationCmd);
	cont.add(addStationButton);
	//Fire PlayerShipMissile
	psShootButton = new GameButton("PS Fire");
	psFireCmd = new PSFireCmd(gw);
	psShootButton.setCommand(psFireCmd);
	addKeyListener(-90, psFireCmd);
	addKeyListener('f', psFireCmd);
	cont.add(psShootButton);
	// HyperSpace Jump
	jumpButton = new GameButton("Jump");
	jumpCmd = new JumpCmd(gw);
	jumpButton.setCommand(jumpCmd);
	addKeyListener('j', jumpCmd);
	cont.add(jumpButton);
	//Asteroid hits NPS
	asteroidHitsNPSCmd  = new AsteroidHitsNPSCmd(gw);
	addKeyListener('I', asteroidHitsNPSCmd);
	//Decrease PlayerShip speed
	decreaseSpeedCmd = new DecreaseSpeedCmd(gw);
	addKeyListener(-92, decreaseSpeedCmd);
	addKeyListener('d', decreaseSpeedCmd);
	//Increase PlayerShip speed
	increaseSpeedCmd = new IncreaseSpeedCmd(gw);
	addKeyListener(-91, increaseSpeedCmd);
	addKeyListener('i', increaseSpeedCmd);
	//NPS shoot missile
	npsFireCmd = new NPSFireCmd(gw);
	addKeyListener('L', npsFireCmd);
	//NPS missile hits PS
	npsMissileHitPSCmd = new NPSMissileHitPSCmd(gw);
	addKeyListener('E', npsMissileHitPSCmd);
	//PS crashes into asteroid
	psHitsAsteroidCmd = new PSHitsAsteroidCmd(gw);
	addKeyListener('c',  psHitsAsteroidCmd);
	//PS crashes into NPS
	psHitsNPSCmd = new PSHitsNPSCmd(gw);
	addKeyListener('h', psHitsNPSCmd);
	//PS missile hits asteroid
	psMissileHitAsteroidCmd = new PSMissileHitAsteroidCmd(gw);
	addKeyListener('k', psMissileHitAsteroidCmd);
	//PS Missile hits NPS
	psMissileHitNPSCmd = new PSMissileHitNPSCmd(gw);
	addKeyListener('e', psMissileHitNPSCmd);
	//Reload PS missiles
	reloadPSCmd = new ReloadPSCmd(gw);
	addKeyListener('n', reloadPSCmd);
	//Tick
	tickCmd = new TickCmd(gw);
	addKeyListener('t', tickCmd);
	//Turn missile launcher left
	turnMLLeftCmd = new TurnMLLeftCmd(gw);
	addKeyListener(',', turnMLLeftCmd);
	addKeyListener('<', turnMLLeftCmd);
	//turn missile launcher right
	turnMLRightCmd = new TurnMLRightCmd(gw);
	addKeyListener('.', turnMLRightCmd);
	addKeyListener('>', turnMLRightCmd);
	//turn player ship left
	turnPSLeftCmd = new TurnPSLeftCmd(gw);
	addKeyListener('l', turnPSLeftCmd);
	addKeyListener(-93, turnPSLeftCmd);
	// turn player ship right
	turnPSRightCmd = new TurnPSRightCmd(gw);
	addKeyListener('r', turnPSRightCmd);
	addKeyListener(-94, turnPSRightCmd);
	//asteroids collide
	twoAsteroidsCollideCmd = new TwoAsteroidsCollideCmd(gw);
	addKeyListener('x', twoAsteroidsCollideCmd);
	
	
	
	
	add(BorderLayout.WEST, cont);
	gw.notifyObservers();
	//SIDE MENU//
	sideMenu = new Toolbar();
	setToolbar(sideMenu);
	
	//Quit the Game
	QuitCmd quitCmd = new QuitCmd(gw);
	//About
	AboutCmd aboutCmd = new AboutCmd(gw);
	//Sound Check Box
	SoundCheckCmd soundCheckCmd = new SoundCheckCmd(gw);
	CheckBox cBox = new CheckBox("Sound On/Off");
	cBox.addActionListener(soundCheckCmd);

	//New
	NewCmd newCmd = new NewCmd(gw);
	//Save
	SaveCmd saveCmd = new SaveCmd(gw);
	//Undo
	UndoCmd undoCmd = new UndoCmd(gw);
	
	sideMenu.addCommandToSideMenu(quitCmd);
	sideMenu.addCommandToSideMenu(aboutCmd);
	sideMenu.addComponentToSideMenu(cBox);
	sideMenu.addCommandToSideMenu(newCmd);
	sideMenu.addCommandToSideMenu(saveCmd);
	sideMenu.addCommandToSideMenu(undoCmd);
	
	///Game Timer
	UITimer myTimer = new UITimer (this);
	myTimer.schedule(200, true, this);
	
	show();
	h = mv.getHeight();
	w = mv.getWidth();
	gw.setGameHeight(h);
	gw.setGameWidth(w);
	
}//end constructor
@Override
public void run() {
		gw.clockTicked();
		mv.repaint();
}
}//end class
