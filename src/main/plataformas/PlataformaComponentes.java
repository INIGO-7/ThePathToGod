package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PlataformaComponentes {
	
	private int spawnX, spawnY;
	public abstract void tick();
	public abstract void render(Graphics graphics);

	public abstract int getSpawnX();
	public abstract void setSpawnX( int spawnX );

	public abstract int getSpawnY();
	public abstract void setSpawnY( int spawnY );

}
