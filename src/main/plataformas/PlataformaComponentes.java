package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class PlataformaComponentes {
	
	public int spawnX, spawnY;
	public abstract void tick();
	public abstract void render(Graphics graphics);
	
}
