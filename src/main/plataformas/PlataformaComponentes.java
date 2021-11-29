package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PlataformaComponentes {

	protected final int movementSpeed = 1;
	protected int platX, platY, speedX = movementSpeed, speedY,
			salto, ANCHURA_PLAT, ALTURA_PLAT;
	private long tiempo, tiempoPasado, tiempoAnterior;
	protected Rectangle platColision;

	public PlataformaComponentes(int spawnX, int spawnY){
		this.platX = spawnX;
		this.platY = spawnY;
		speedY = 0;
	}

	public abstract void tick();
	public abstract void render(Graphics graphics);

	public void platformMovementX(){
		if(platX > 299 - ANCHURA_PLAT || platX < 1) speedX = -speedX;
		platX += speedX;
		platColision.setLocation((int) platColision.getX() + speedX, (int) platColision.getY());
	}

	public void gravity() {

		tiempo = System.nanoTime();
		tiempoPasado = tiempo - tiempoAnterior;

		if (tiempoPasado > 200000000) {
			tiempoAnterior = tiempo;
			tiempoPasado = 0;
			speedY -= 1;
		}
		platY += speedY;
		platColision.setLocation((int) platColision.getX(), (int) platColision.getY() + speedY);
	}

	public int getSalto() {
		return salto;
	}
	public Rectangle getRect(){ return platColision; }

	public int getPlatX(){ return this.platX; }
	public void setPlatX( int spawnX ){ this.platX = spawnX; }

	public int getPlatY(){ return this.platY; }
	public void setPlatY( int spawnY ){ this.platY = spawnY; }

	public int getSpeedX(){ return this.speedX; }
	public void setSpeedX( int speedX ){ this.speedX = speedX; }

	public int getSpeedY(){ return this.speedY; }
	public void setSpeedY( int speedY ){ this.speedY = speedY; }

}