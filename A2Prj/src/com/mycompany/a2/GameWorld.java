package com.mycompany.a2;

import java.util.Collection;
import java.util.Observable;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

//BackgroundMusic:
//Pay The Reaper by Darren-Curtis | https://soundcloud.com/desperate-measurez
//Music promoted by https://www.free-stock-music.com
//Creative Commons Attribution 3.0 Unported License
//https://creativecommons.org/licenses/by/3.0/deed.en_US

/**
 * @author Daniel Curtis and Curtis Botonis Generates and controls game world
 */
public class GameWorld extends Observable implements IGameWorld {
	// Game data //
	/**
	 * Vector to store GameObjects
	 */
	private GameObjectCollection gameObj;
	private GameObjectCollection deletes = new GameObjectCollection();

	/**
	 * int value of player lives
	 */
	private int playerLives;
	/**
	 * int value of players score
	 */
	private int playerScore;
	/**
	 * int value of clock time - elapsedGameTime
	 */
	private int clock;
	/**
	 * Tracks if a PlayerShip is in the world. - hasPlayerShip
	 */
	private boolean pship = false;
	/**
	 * Tracks amount of NonPlayerShips in the world.
	 */
	private int npship = 0;
	/**
	 * Tracks amount of Asteroids in the world.
	 */
	private int astObj = 0;
	/**
	 * Tracks amount of Missiles in the world.
	 */
	private int misObj = 0;
	/**
	 * Tracks number of NonPlayerShip's Missile objects in the world;
	 */
	private int npsMis = 0;
	
	/**
	 * Tracks if a spaceStation is in the world
	 */
	private boolean station = false;
	/**
	 * Tracks width
	 */
	private int width = 0;
	/**
	 * Tracks height
	 */
	private int height = 0;
	/**
	 * Tracks if sound is On
	 */
	private boolean soundOn = true;
	private static double gameWidth = getGameWidth();
	private static double gameHeight = getGameHeight();
	BgSound bg = new BgSound("darren-curtis-pay-the-reaper.mp3");
	Sound shipFireMissile = new Sound("asteroids-ship-shoot.wav"); 
	Sound quitSound = new Sound("priceiswrong.mp3");
	Sound turnLLeft = new Sound("chngnd1a.wav");
	Sound turnLRight = new Sound("chngnu1a.wav");

	// METHODS
	/**
	 * Constructor instantiates a Vector to store Game Objects
	 */
	public GameWorld() {
		gameObj = new GameObjectCollection();
		this.init();
	}

	/**
	 * initializes game variables to new game state
	 */
	public void init() {
		playerLives = 3;
		playerScore = 0;
		clock = 0;
		bg.play();
		pship = false;
		astObj = 0;
		misObj = 0;
		npsMis = 0;
		station = false;
	}

	/**
	 * @return Int playerScore
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * Add Points to playerScore
	 */
	public void setPlayerScore(int playerScore) {
		this.playerScore += playerScore;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * @return Int total playerShip missiles on board
	 */
	public int getMissileCount() {
		int count = 0;
		IIterator iter = getIterator();
		GameObject x = null;
		while (iter.hasNext()) {
			x = iter.getNext();
			if (x instanceof PlayerShip) {
				PlayerShip ps = (PlayerShip) x;
				count = ps.getMissileCount();
			}
		}
		return count;
	}

	/**
	 * get the Iterator
	 */
	public IIterator getIterator() {
		return gameObj.getIterator();
	}

	/**
	 * @return totalElapsedTime
	 */
	public int getElapsedTime() {
		return clock;
	}

	public void incrementGameclock() {
		this.clock += 1;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * @return if Sound has been Enabled
	 */
	public String getSound() {
		if (soundOn)
			return "ON";
		else
			return "OFF";
		}

	/**
	 * @return total playerLives remaining in game
	 */
	public int getLivesRemaining() {
		return playerLives;

	}

	/**
	 * decrement player lives
	 */
	public void decrementPlayerLives(int playerLives) {
		this.playerLives -= 1;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Change each time called
	 */
	public void setSound() {
		if(soundOn) {
			soundOn = false;
			bg.pause();
		}
		else if (!soundOn){
			soundOn = true;
			bg.play();
		}
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	/**
	 * returns bool soundOn
	 */
	public boolean getSoundToggle() {
		return soundOn;
	}
	/**
	 * adds an Asteroid object to the game world
	 */
	public void addAsteroid() {
		Asteroid a = new Asteroid();
		gameObj.add(a);
		astObj++;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * adds a NonPlayerShip object to the world
	 */
	public void addNPS() {
		NonPlayerShip nps = new NonPlayerShip();
		gameObj.add(nps);
		npship++;
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * adds a SpaceStation object to the world
	 */
	public void addSpaceStation() {
		if (!station) {
			SpaceStation ss = new SpaceStation();
			gameObj.add(ss);
			station = true;
		} else {
			System.out.println("A SpaceStation has already been created");
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * adds a PlayerShip object to the world
	 */
	public void addPS() {
		if (!pship) {
			PlayerShip ps = new PlayerShip();
			gameObj.add(ps);
			pship = true;
		} else {
			System.out.println("You can only have one playerShip at a time");
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * adds a Missile object fired from the PlayerShip object into the world
	 */
	public void psFireMissile() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("You can't shoot missiles without a ship");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					if (ps.getMissileCount() > 0) {
						Missile msl = new Missile(ps.getLocation(), ps.getSpeed(), ps.getLauncherDirection(), pship, 6);
						ps.decrementMissileCount();
						gameObj.add(msl);
						shipFireMissile.play();
						misObj++;
					}

					else {
						System.out.println("Error: No missiles remaining!");
					}
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * adds a Missile object fired from the NonPlayerShip object into the world
	 */
	public void npsFireMissile() {
		IIterator theColl = getIterator();
		if (npship == 0) {
			System.out.println("You can't shoot missiles without a ship");
		} else {
			while (theColl.hasNext()) {
				NonPlayerShip nps = null;
				GameObject x = theColl.getNext();
				if (x instanceof NonPlayerShip) {
					nps = (NonPlayerShip) x;
					if (nps.getMissileCount() > 0) {
						Missile msl = new Missile(nps.getLocation(), nps.getSpeed(), nps.getDirection(), false, 6);
						nps.setMissileCount(nps.getMissileCount() - 1);
						gameObj.add(msl);
						shipFireMissile.play();
						npsMis++;
					} else {
						System.out.println("Error: No missiles remaining!");
					}
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * prints all of the objects that are currently in the GameWorld
	 */
	public void printMap() {
		IIterator theColl = getIterator();
		System.out.println("World Map: ");
		while (theColl.hasNext()) {
			GameObject x = theColl.getNext();
			System.out.println(x.toString());
		}
	}

	/**
	 * prints the amount of players lives, their score, and clock time
	 */
	public void printDisplay() {
		System.out.println("Playerlives: " + playerLives + " PlayerScore: " + playerScore + " Clock: " + clock);
	}

	/**
	 * reloads PlayerSHips missile supply to 10
	 */
	public void resupplyMissiles() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to reload");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					if (ps.getMissileCount() < 10) {
						ps.reloadMissiles();
					} else {
						System.out.println("Error: Missiles already full");
					}
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * turns the PLayerShipMissileLauncher clockwise
	 */
	public void turnLauncherRight() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip means no missile launcher to turn");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
			        turnLRight.play();
					ps.revolveLauncher();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * turns the PLayerShipMissileLauncher counterclockwise
	 */
	public void turnLauncherLeft() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip means no missile launcher to turn");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
			        turnLLeft.play();
					ps.revolveLauncherLeft();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * A PLayerShip's Missile hits and destroys an Asteroid, removing one of each
	 * from the game world
	 */
	public void psMissileHitAsteroid() {
		if (misObj == 0) {
			System.out.println("Error: No missiles to explose Asteroids");
		} else if (astObj == 0) {
			System.out.println("Error: There are no Asteroids to explode");
		} else {
			psMissileHit();
			misObj--;
			asteroidHit();
			astObj--;
			System.out.println("PlayerShip missile hit an Asteroid");
			setPlayerScore(10);
		}
	}

	/**
	 * A PlayerShip's Missile has destroyed an NonPlayerShip, Missile and
	 * NonPlayerShip removed from game, player score + 10.
	 */
	public void psMissileHitNPS() {
		if (misObj == 0) {
			System.out.println("Error: No missiles to explose NPS");
		} else if (npship == 0) {
			System.out.println("Error: There are no NPS to explode");
		} else {
			psMissileHit();
			misObj--;
			npsHit();
			npship--;
			System.out.println("PlayerShip missile hit NonPlayerShip");
			setPlayerScore(5);
		}
	}

	/**
	 * A NonPlayerShip's Missile has hit the PlayerShip, removing the PlayerShip
	 * from the world and removing one of the players lives.
	 */
	public void npsMissileHitPS() {
		if (!pship) {
			System.out.println("Error: No PlayerShip to explode");
		} else if (npship == 0) {
			System.out.println("Error: No NPS on the map");
		} else if (npsMis == 0) {
			System.out.println("Error: The NPS hasn't shot any Missiles");
		} else {
			npsMissileHit();
			npsMis--;
			psHit();
			pship = false;
			System.out.println("NonPlayerShip missile hit PlayerShip");
			if (playerLives == 1) {
				System.out.println("GameOver");
				printDisplay();
				gameOver();
			} else {
				decrementPlayerLives(1);
			}
		}
	}

	/**
	 * The PlayerShip has collided with an Asteroid, removing both from the game
	 * world and removing a player life
	 */
	public void psHitAsteroid() {
		if (!pship) {
			System.out.println("Error: There is no ship on the map");
		} else if (astObj == 0) {
			System.out.println("Error: There is no Asteroid on the map");
		} 
		else {
			psHit();
			pship = false;
			asteroidHit();
			astObj--;
			System.out.println("PlayerShip collided with an Asteroid");
			if (playerLives == 1) {
				System.out.println("GameOver");
				printDisplay();
				gameOver();
			} else {
				decrementPlayerLives(1);
			}
		}
	}

	/**
	 * A PlayerShip collided with a NonPLayerShip, removing the PlayerShip and
	 * NonPlayerShip from the world and removing a player life
	 */
	public void psHitNPS() {

		if (!pship) {
			System.out.println("Error: There is no ship on the map");
		} else if (npship == 0) {
			System.out.println("Error: There is no NPS on the map");
		} else {
			psHit();
			pship = false;
			npsHit();
			npship--;
			System.out.println("PlayerShip collided with a NonPlayerShip");
			if (playerLives == 1) {
				System.out.println("GameOver");
				printDisplay();
				quit();
			} else {
				decrementPlayerLives(1);
			}
		}
	}

	/**
	 * Two Asteroids collide removing both from the world
	 */
	public void twoAsteroidsCollide() {
		if (astObj < 2) {
			System.out.println("Error: Less than two asteroids created");
		} else {
			asteroidHit();
			astObj--;
			asteroidHit();
			astObj--;
			System.out.println("An Asteroid collided with another Asteroid");
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * An Asteroid collides with a NonPlayerShip removing both from the world
	 */
	public void asteroidHitNPS() {

		if (astObj == 0) {
			System.out.println("Error: There are no Asteroids in the world");
		} else if (npship == 0) {
			System.out.println("Error: There are no Asteroids in the world");
		}

		else {
			asteroidHit();
			astObj--;
			npsHit();
			npship--;
			System.out.println("An asteroid has hit an NonPlayerShip");
		}
	}

	/**
	 * Returns the PlayerShip to the center of the map
	 */
	public void hyperspaceJump() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to speed up");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					ps.returnToCenter();
					System.out.println("Hyperspace...HOLD ON!");
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));

	}

	/**
	 * Increases the PlayerShips speed
	 */
	public void increaseSpeed() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to speed up");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					ps.increaseSpeed();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Decreases the PlayerShips speed
	 */
	public void decreaseSpeed() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to slow down");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					ps.decreaseSpeed();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Turns the PlayerShip left
	 */
	public void turnShipLeft() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to turn left");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					ps.steerLeft();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * Turns the PlayerShip right
	 */
	public void turnShipRight() {
		IIterator theColl = getIterator();
		if (!pship) {
			System.out.println("Error: No PlayerShip to turn right");
		} else {
			while (theColl.hasNext()) {
				PlayerShip ps = null;
				GameObject x = theColl.getNext();
				if (x instanceof PlayerShip) {
					ps = (PlayerShip) x;
					ps.steerRight();
				}
			}
		}
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}

	/**
	 * MoveableObjects move(), missile fuel decreases or burns out Space Station
	 * light blinks, gameClock increases
	 */
	public void clockTicked() {
		IIterator theColl = getIterator();
		while (theColl.hasNext()) {
			GameObject x = theColl.getNext();
			if (x instanceof IMove) {
				IMove mObj = (IMove) x;
				mObj.move();
			}
			if (x instanceof Missile && ((Missile) x).getFuelLevel() == 0) {
				deletes.add(x);
				if (x instanceof Missile && ((Missile) x).getMissileType() == true) {misObj--; }
				if (x instanceof Missile && ((Missile) x).getMissileType() == false) {npsMis--;}

			}
			if (x instanceof Missile && ((Missile) x).getFuelLevel() > 0) {
				((Missile) x).decrementFuelLevel();
			}
			

			if (x instanceof SpaceStation) {
				SpaceStation ss = (SpaceStation) x;
				if (clock % ss.getBlinkRate() == 0 && clock != 0) {
					ss.switchLightOn();
					//System.out.println("The light hast turned on");
				}
				else {ss.switchLightOff();  }
			}
			if (x instanceof NonPlayerShip && getElapsedTime() % 50 == 0) {npsFireMissile();}
		}
		IIterator it = deletes.getIterator();
		while (it.hasNext()) {
			GameObject o = it.getNext();
			gameObj.remove(o);
		}
		checkCollisions();
		incrementGameclock();
		//System.out.println("Clock has ticked");
		//System.out.println("MoveableObjects has moved");
	}

	/**
	 * Cycle through collection to find instance of PlayerShip and remove
	 */
	public void psHit() {
		IIterator iterator = getIterator();
		while (iterator.hasNext()) {
			GameObject x = iterator.getNext();
			if (x instanceof PlayerShip) {
				gameObj.remove(x);
				break;
			}
		}
	}

	/**
	 * Cycle through collection to find first instance of NonPlayerShip and remove
	 */
	public void npsHit() {
		IIterator iterator = getIterator();
		while (iterator.hasNext()) {
			GameObject x = iterator.getNext();
			if (x instanceof NonPlayerShip) {
				gameObj.remove(x);
				break;
			}
		}
	}

	/**
	 * Cycle through collection to find first instance of Asteroid and remove
	 */
	public void asteroidHit() {
		IIterator iterator = getIterator();
		while (iterator.hasNext()) {
			GameObject x = iterator.getNext();
			if (x instanceof Asteroid) {
				gameObj.remove(x);
				break;
			}
		}
	}


	/**
	 * Cycle through collection to find first instance of PlayerShip missile and
	 * remove
	 */
	public void psMissileHit() {
		IIterator iterator = getIterator();
		while (iterator.hasNext()) {
			GameObject x = iterator.getNext();
			if (x instanceof Missile && ((Missile) x).getMissileType()) {
				gameObj.remove(x);
				break;
			}
		}
	}

	/**
	 * Cycle through collection to find first instance of NonPlayerShip missile and
	 * remove
	 */
	public void npsMissileHit() {
		IIterator iterator = getIterator();
		while (iterator.hasNext()) {
			GameObject x = iterator.getNext();
			if (x instanceof Missile && !((Missile) x).getMissileType()) {
				gameObj.remove(x);
				break;

			}
		}
	}

	/**
	 * Method to confirm before user quits.
	 */
	public void quit() {
		quitSound.play();
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		if (bOk) {
			Display.getInstance().exitApplication();
		}
	}
	
	/**
	 * Ask player if they want to start over after running out of lives
	 */
	public void gameOver() {
		quitSound.play();
		Boolean bOk = Dialog.show("Game Over", "Do you want to play again?", "No", "Yes");
		if (bOk) {
			Display.getInstance().exitApplication();
		}
		else {startOver();}
	}
/**
 * Resets game world after game over
 */
public void startOver() {
	
		gameObj = new GameObjectCollection();
			
		
	init();
		
	}
	/**
	 * 
	 * @param width to set GameWorld Width
	 */
	
	  public void setGameWidth(int width) { gameWidth = width; }
	 /**
		 * 
		 * @param height to set GameWorld Height
		 */
	public void setGameHeight(int height) { gameHeight = height; }
	public static double getGameHeight() {return gameHeight;}
	public static double getGameWidth() {return gameWidth;}
	
	private void checkCollisions() {
		IIterator it = gameObj.getIterator();
		
		while (it.hasNext()) {
			GameObject x = it.getNext();
			if (x instanceof ICollider) {
				ICollider collObj = (ICollider) x;
				
				IIterator other = gameObj.getIterator();
				while (other.hasNext()) {
					GameObject otherObj = other.getNext();
					if(otherObj instanceof ICollider && (x != (otherObj))) {
						ICollider otherCollObj = (ICollider) otherObj;
						if(collObj.collidesWith (otherCollObj)) {
							collObj.handleCollision(otherCollObj);
						}
					}
				}
			}
		}
		//deleteCollisions();
	}
	/*
	 * public void deleteCollisions() { IIterator removeStuff =
	 * gameObj.getIterator();
	 * 
	 * while(removeStuff.hasNext()) { GameObject obj = removeStuff.getNext(); if
	 * (obj instanceof ICollider && ((ICollider)obj).getRemove()) {
	 * deletes.add(obj); } if (obj instanceof Missile && ((Missile)
	 * obj).getMissileType()==true) { setPlayerScore(((Missile) obj).getPoints());
	 * misObj --; } if (obj instanceof PlayerShip) { pship = ((PlayerShip)
	 * obj).getDead(); }
	 * 
	 * } IIterator it = deletes.getIterator(); while (it.hasNext()) { GameObject o =
	 * it.getNext(); gameObj.remove(o); } }
	 */
	  
  }
			 

