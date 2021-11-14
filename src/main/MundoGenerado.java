package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.net.ssl.KeyManager;

import main.input.EventosTeclado;
import main.plataformas.PlataformaBasica;
import main.plataformas.PlataformaHielo;
import main.plataformas.PlataformaTurbo;

public class MundoGenerado {

	private BufferedImage pantallaInicial;
	private Juego juego;
	//private Random random = new Random();
	private int aleatorio, posicionX;
	protected int salto;
	private PlataformaBasica a;
	private PlataformaHielo b;
	private PlataformaTurbo c;
	
	public MundoGenerado(Juego juego) {
		
		this.juego = juego;
	
	}
	
	public void render(Graphics graphics) {
	
		try {
			pantallaInicial = ImageIO.read(new File("res/img/pantallaFullPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		graphics.drawImage(pantallaInicial, 0, juego.jugando, null);
		
	}
	
	public void generarMundo() {
		
		//a = basica, b = hielo y c = turbo => si valor entre 0 y 6 = a ; si valor entre 6 y 9 = b ; si = 10 = c.
		
		//aleatorio = random.nextInt(10);
		//posicionX = random.nextInt(300);	
		
	}
	
}
