package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.plataformas.PlataformaBasica;
import main.plataformas.PlatformManager;

public class MundoGenerado {

	private BufferedImage pantallaInicial;
	private Juego juego;
	//private Random random = new Random();
	private int aleatorio, posicionX;
	protected int salto;
	private PlatformManager plats;
	
	public MundoGenerado(Juego juego) {
		this.juego = juego;
		plats = new PlatformManager();
	}

	public void init(){
		plats.createPlatform(new PlataformaBasica(140, 380));
	}

	public void tick(){

		plats.tick();

	}

	public void render(Graphics graphics) {

		plats.render(juego.getGraphics());
		
	}

	//el jugador se mueve de lado a lado, y no puede superar una altura máxima en la pantalla, si supera esa altura las plataformas
	//se moverán hacia abajo.

	//hay que hacer que el jugador funcione (tick y render actualizándose) hasta que muera. Así una vez muerto no se gastarán recursos.
	
	public void generarMundo() {
		
		//a = basica, b = hielo y c = turbo => si valor entre 0 y 6 = a ; si valor entre 6 y 9 = b ; si = 10 = c.
		
		//aleatorio = random.nextInt(10);
		//posicionX = random.nextInt(300);	
		
	}

	public PlatformManager getPlats(){
		return plats;
	}
	
}
