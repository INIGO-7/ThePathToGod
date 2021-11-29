package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import main.plataformas.PlataformaBasica;
import main.plataformas.PlataformaComponentes;
import main.plataformas.PlatformManager;

public class MundoGenerado {

	private BufferedImage pantallaInicial;
	private Juego juego;
	private Random randX, randY;
	private int lastjY,  newJY;
	private int aleatorio, posicionX;
	protected int salto;
	private PlatformManager manager;

	//TODO: automatic removal of each removed platform's collision rectangle.
	//TODO: make platforms go down as the player goes higher so it seems as if the player is going up.
	//TODO: altitude threshold for the player which mustn't surpass. The platforms going down will do the job.
	//TODO: a way to count the altitude that the player reaches (for scorecounting and knowing when and how the platforms should move)

	public MundoGenerado(Juego juego) {
		this.juego = juego;
		manager = new PlatformManager();
		randX = new Random();
		randY = new Random();
		lastjY = 350; // the point at which the first automatically generated platform will be created.
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

		manager.tick();
		worldManager();

	}

	public void render(Graphics graphics) {

		manager.render(graphics);
		
	}

	//el jugador se mueve de lado a lado, y no puede superar una altura máxima en la pantalla, si supera esa altura las plataformas
	//se moverán hacia abajo.

	//hay que hacer que el jugador funcione (tick y render actualizándose) hasta que muera. Así una vez muerto no se gastarán recursos.
	
	public void worldManager() {

		newJY = juego.getJugador().getjY();

		System.out.println(manager.getPlataformas().size());

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


		if(juego.getJugador().getjY() < 150 && juego.getJugador().getSpeedY() != 0) {
			//if the player just passed the 150 pixels limit, we make the platforms move by setting their speed to the
			//player's, then set the player's speed to 0
			manager.setPlatformsYspeed(Math.abs(juego.getJugador().getSpeedY()));
			juego.getJugador().setSpeedY(0);

		}else if(juego.getJugador().getjY() < 150 && juego.getJugador().getSpeedY() == 0){
			//gravity for the platforms only works above the 150 pixel threshold and when the player is not moving.
			manager.gravity();

		}else if(manager.getPlatformsYspeed() < 0) {
			//when the platforms lose their speed, we no longer want to apply gravity to them, instead the player will
			//have gravity again.
			manager.setPlatformsYspeed(0);
		}else{
			juego.getJugador().gravedad();
		}

	}

	public PlatformManager getManager(){
		return manager;
	}
	
}
