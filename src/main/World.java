package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import main.input.EventosTeclado;
import main.plataformas.PlataformaBasica;
import main.plataformas.PlataformaComponentes;
import main.plataformas.PlatformManager;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class World {

	private BufferedImage pantallaInicial;
	private Game game;
	private Random randX, randY;
	private int lastjY, newJY, screenY, aleatorio, posicionX, distanceTravelled;
	protected int salto;
	private PlatformManager manager;
	private boolean platGravity, gameCreated;
	private Rectangle playerCollisionsRect;
	private EventosTeclado evt;
	private Player player;

	//TODO: automatic removal of each removed platform's collision rectangle.
	//TODO: make infinite platforms work correctly

	public World(Game game, EventosTeclado evt) {
		this.game = game;
		this.evt = evt;
		player = new Player(evt);
		manager = new PlatformManager();
		pantallaInicial = game.getPantallaInicial();

		randX = new Random();
		randY = new Random();

		screenY = -2250;
		lastjY = 350; // the point at which the first automatically generated platform will be created.
		platGravity = true; //we want everything enabled from the beginning.
		gameCreated = false; //it will be true when the game is created by pressing the up arrow (the game begins)
	}

	public void init(){

		manager.createPlatform(new PlataformaBasica(150, randY.nextInt(30) + 350));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 275));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 200));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 125));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 50));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 30));
	}

	public void tick(){

		player.tick();

		worldManager();
		playerCollisions();

		//we check if the user has pressed the up arrow (i.e wants to start playing)
		if(evt.isArriba() && !gameCreated) {
			game.getVentanaUsuarios().añadirPartida();
			gameCreated = true;
		}

		if(screenY >= -1801){
			manager.tick();
		}
	}

	public void render(Graphics g) {

		if(screenY < -1801){
			g.drawImage(pantallaInicial, 0, screenY+=3, null);
		}else{
			g.drawImage(pantallaInicial, 0, 0, null);
			manager.render(g);	//renders the platforms only when the image stops moving and we start to play.
			altitudeCounter(g);
		}

		player.render(g);
	}

	//el jugador se mueve de lado a lado, y no puede superar una altura máxima en la pantalla, si supera esa altura las plataformas
	//se moverán hacia abajo.

	//hay que hacer que el jugador funcione (tick y render actualizándose) hasta que muera. Así una vez muerto no se gastarán recursos.
	
	public void worldManager() {

		newJY = player.getjY();

		if(manager.getPlataformas().getFirst().getPlatY() > 450){

			lastjY = newJY;
			LinkedList<PlataformaComponentes> allPlatforms = manager.getPlataformas();

			//the first platform in the list (last platform that the player has seen) is removed.
			allPlatforms.removeFirst();
			//remove element from memory manually? or will garbage collector do the job well??

			//now a new higher platform than the last one created will be added.
			manager.createPlatform(new PlataformaBasica(randX.nextInt(260),
					allPlatforms.getLast().getPlatY() - 75 - randX.nextInt(30)));

		}

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		The last lines of code of this function will handle movement. We will have to decide how the player goes up
		depending on the 150 pixel threshold that is set. As long as the player is under that limit, all good. But if
		the player is above that threshold (we dont want to go much	higher because that way we wouldn't see the player),
		the platforms will get the player's momentum and gravity will be applied to them all. When gravity makes
		platforms loose their momentum, the player will have its gravity back.
		*/
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if(!(manager.getPlatformsYspeed() >= 0)) {
			//as we are going to give
			//have gravity again.
			manager.setPlatformsYspeed(0);
			platGravity = false;
		} else if(player.getjY() < 150 && player.getSpeedY() != 0 && platGravity) {
			//if the player just passed the 150 pixels limit, we make the platforms move by setting their speed to the
			//player's, then set the player's speed to 0. We want this to happen once.
			manager.setPlatformsYspeed(Math.abs(player.getSpeedY()));
			player.setSpeedY(0);

		}else if(player.getjY() < 150 && player.getSpeedY() == 0 && platGravity){
			//gravity for the platforms only works above the 150 pixel threshold and when the player is not moving.
			manager.gravity();

		}else{
			if(screenY >= -1801) player.gravedad();
		}
	}

	public void playerCollisions() {

		playerCollisionsRect = player.getAreaSalto();	//gets the "feet" area of the player

		for(Rectangle r : manager.getRectangulos()) {	//checks all the platform collision area
			if(r.intersects(playerCollisionsRect)) {	//if any of the platforms contain the area of the player's "feet"

				//if the player is neither above the 150 pixels threshold or moving in the Y axis, the movement that is
				//generated by touching a platform will make the player go up by pushing the platforms down:
				if(player.getjY() < 150 && player.getSpeedY() == 0){
					manager.setPlatformsYspeed(4);

				//else, the player will go up by "jumping" on the platform
				}else{
					player.setSpeedY(-4);
					platGravity = true;
				}
			}
		}
	}

	public void altitudeCounter(Graphics g){

		int numberWidth = 1;
		//int fontSize = g.getFont().getSize();
		//System.out.println(fontSize * 1.6 * 2);
		g.setColor(Color.BLACK);  // Here
		//g.drawString("40", (int) (game.getWindowWidth() - fontSize * 1.6), 10);
		distanceTravelled = manager.getDistanceTravelled();
		g.drawString(String.valueOf(distanceTravelled), getXcoordinate(distanceTravelled), 10);

	}

	public int getXcoordinate(int number){
		if(number < 10){
			return 280;
		}else if(number < 100){
			return 272;
		}else if(number < 1000){
			return 264;
		}else{
			return 256;
		}
	}

	public Player getPlayer(){
		return player;
	}

	public PlatformManager getManager(){
		return manager;
	}
	
}
