package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.input.EventosTeclado;

public class Player {

	private int jX = 132, jY = 250, speedX, speedY;
	long tiempoPasado, tiempo, tiempoAnterior = System.nanoTime();
	public int playerHeight = 50, playerWidth = 30;
	private BufferedImage character;
	private EventosTeclado eventosTeclado;
	private static final int X_MOVEMENT_SPEED = 4;

	private Rectangle jugadorArea;
	private Rectangle areaSalto;
	
	public Player(EventosTeclado eventosTeclado) {
		this.eventosTeclado = eventosTeclado;
	}

	{
		try {
			character = ImageIO.read(new File("res/img/playerFigurePTG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {

		g.drawImage(character, jX, jY, null);

		//graphics.setColor(Color.red);
		//graphics.fillRect(jX, jY + 40, jugadorAnchura, 10);
	}
	
	public void jugadorMovimiento() {

		//check is the right arrow is pressed (movement requested) and the player has not surpassed the screen limit on the right.
		if(eventosTeclado.isDcha() && jX >= 350 - playerWidth) jX = 350;
		else if(eventosTeclado.isDcha() && jX < 350 - playerWidth) speedX =  X_MOVEMENT_SPEED;
		//check is the left arrow is pressed (movement requested) and the player has not surpassed the screen limit on the left.
		if(eventosTeclado.isIzq() && jX <= 0) jX = 0;
		else if(eventosTeclado.isIzq() && jX > 0) speedX = -X_MOVEMENT_SPEED;
		//when neither arrows are pressed, the player won't carry any momentum. We set its speed to 0.
		if(!eventosTeclado.isIzq() && !eventosTeclado.isDcha()) speedX = 0;

		//updates the coordinates in both x and y axis depending on the speed of the player.
		jX += speedX;
		jY += speedY;

	}
	
	public void tick() {
		jugadorMovimiento();
		areaSalto = new Rectangle(jX, jY + 49, playerWidth, 1);
		jugadorArea = new Rectangle(jX, jY, playerWidth, playerHeight);

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

		if(speedY < 5 && tiempoPasado > 200000000) {
			tiempoAnterior = tiempo;
			tiempoPasado = 0;
			speedY += 1;
		}
	}
	
}