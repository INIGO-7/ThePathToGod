package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.input.EventosTeclado;

public class Jugador {

	private int jX = 132, jY = 250, speedX, speedY;
	long tiempoPasado, tiempo, tiempoAnterior = System.nanoTime();
	public int jugadorAltura = 50, jugadorAnchura = 30;
	private BufferedImage personaje, gameOver;
	private EventosTeclado eventosTeclado;
	private Juego juego;
	private static int VELOCIDAD_LATERAL_JUGADOR = 4;
	private Graphics graphics;

	private Rectangle jugadorArea;
	private Rectangle areaSalto;
	
	public Jugador(EventosTeclado eventosTeclado, Juego juego) {
		this.eventosTeclado = eventosTeclado;
		this.juego = juego;
	}

	{
		try {
			personaje = ImageIO.read(new File("res/img/playerFigurePTG.png"));
			gameOver = ImageIO.read(new File("res/img/gameOver.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graphics) {
		
		if(!(jY > 450)) {
		graphics.drawImage(personaje, jX, jY, null);
		}else{
			speedY = 0;
			graphics.drawImage(gameOver, 0, 0, null);
		}	
		//graphics.setColor(Color.red);
		//graphics.fillRect(jX, jY + 40, jugadorAnchura, 10);
	}
	
	public void jugadorMovimiento() {
		
		if(eventosTeclado.isDcha()) speedX =  VELOCIDAD_LATERAL_JUGADOR;
		
		if(eventosTeclado.isIzq()) speedX = -VELOCIDAD_LATERAL_JUGADOR;
		
		if(!eventosTeclado.isIzq() && !eventosTeclado.isDcha()) speedX = 0;
		
		colisionesLimitesPantalla();
	}
	
	public void colisionesLimitesPantalla() {
			
		jX += speedX;
		jY += speedY;
		
		if(jX > 300 - jugadorAnchura - 8) jX = 1; //si se mueve hacia la dcha y se pasa de largo, le pongo en el l�mite de la pantalla
		if(jX < 0) jX = 299 - jugadorAnchura - 8;												//lo mismo a la izq

	}
	
	public void tick() {
		jugadorMovimiento();
		areaSalto = new Rectangle(jX, jY + 49, jugadorAnchura, 1);
		jugadorArea = new Rectangle(jX, jY, jugadorAnchura, jugadorAltura);

	}

	public int getjY() {
		return jY;
	}

	public void setjY(int jY) {
		this.jY = jY;
	}

	public int getjX() { return jX; }

	public void setjX(int jX) {	this.jX = jX; }

	public Rectangle getJugadorArea() {
		return jugadorArea;
	}

	public void setJugadorArea(Rectangle jugadorArea) {
		this.jugadorArea = jugadorArea;
	}

	public Rectangle getAreaSalto() {
		return areaSalto;
	}

	public void setAreaSalto(Rectangle areaSalto) {
		this.areaSalto = areaSalto;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public void gravedad(){
		
		tiempo = System.nanoTime();
		tiempoPasado = tiempo - tiempoAnterior;
		
		if(juego.getJugando() == -1800 && speedY < 5) {
			if(tiempoPasado > 200000000) {
				tiempoAnterior = tiempo;
				tiempoPasado = 0;
				speedY += 1;
			}
		}	
	}
	
}