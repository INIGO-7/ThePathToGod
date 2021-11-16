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
	
	public MundoGenerado(Juego juego) {
		this.juego = juego;
		manager = new PlatformManager();
		randX = new Random();
		randY = new Random();
	}

	public void init(){

		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), 30));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 50));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 125));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 200));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 275));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 350));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 475));
		manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + 550));
	}

	public void tick(){

		manager.tick();

	}

	public void render(Graphics graphics) {

		manager.render(juego.getGraphics());
		
	}

	//el jugador se mueve de lado a lado, y no puede superar una altura máxima en la pantalla, si supera esa altura las plataformas
	//se moverán hacia abajo.

	//hay que hacer que el jugador funcione (tick y render actualizándose) hasta que muera. Así una vez muerto no se gastarán recursos.
	
	public void generarMundo() {

		newJY = juego.getJugador().getjY();

		if(lastjY > newJY - 200){

			LinkedList<PlataformaComponentes> allPlatforms = manager.getPlataformas();

			//the first platform in the list (last platform that the player has seen) is removed.
			allPlatforms.removeFirst();
			//remove element from memory manually? or will garbage collector do the job well??

			//now a new higher platform than the last one created will be added.
			manager.createPlatform(new PlataformaBasica(randX.nextInt(260), randY.nextInt(30) + allPlatforms.getLast().getPlatY()));

		}

		
	}

	public PlatformManager getPlats(){
		return manager;
	}
	
}
