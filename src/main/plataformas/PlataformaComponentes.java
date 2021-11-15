package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PlataformaComponentes {

	public PlataformaComponentes(int spawnX, int spawnY){
		this.platX = spawnX;
		this.platY = spawnY;
	}

	protected final int movementSpeed = 1;
	protected int platX, platY, movementX = movementSpeed, salto;
	protected Rectangle platColision;
	public abstract void tick();
	public abstract void render(Graphics graphics);
	public abstract void move();

	public int getSalto() {
		return salto;
	}
	public Rectangle getRect(){ return platColision; }

	public int getPlatX(){ return this.platX; }
	public void setPlatX( int spawnX ){ this.platX = spawnX; }

	public int getPlatY(){ return this.platY; }
	public void setPlatY( int spawnY ){ this.platY = spawnY; }

}
