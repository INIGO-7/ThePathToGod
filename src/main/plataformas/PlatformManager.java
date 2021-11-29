package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public class PlatformManager {
	
	private PlataformaComponentes p;
	private PlataformaBasica plataformaBasica;
	private PlataformaHielo plataformaHielo;
	private PlataformaTurbo plataformaTurbo;
	private LinkedList<PlataformaComponentes> plataformas;	//we create an array of PlataformaComponentes (this is the parent abstract class for ice, turbo and basic platforms)
	private LinkedList<Rectangle> rectangulos;

	private Rectangle colisionPlataforma;
	private int anchuraPlat = 20, alturaPlat = 10;
	private int x, y;

	public PlatformManager() {
		plataformas = new LinkedList<PlataformaComponentes>();
		rectangulos = new LinkedList<Rectangle>();
	}
	
	public void render(Graphics graphics) {
		for(PlataformaComponentes p : plataformas) p.render(graphics);
	}
	
	public void tick() {
		for(PlataformaComponentes p : plataformas) p.tick();
	}

	public void gravity(){
		for(PlataformaComponentes p : plataformas) p.gravity();
	}

	public void setPlatformsYspeed(int spy){
		for(PlataformaComponentes p: plataformas) p.setSpeedY(spy);
	}

	public int getPlatformsYspeed(){
		return plataformas.getFirst().getSpeedY();
	}

	public void createPlatform(PlataformaComponentes p){
		plataformas.add(p);
		rectangulos.add(p.getRect());	//we add the original collisions rectangle to an arraylist containing every platform,
										//to check afterwards whether the player intersects with it or not.
	}

	public LinkedList<PlataformaComponentes> getPlataformas(){ return plataformas; }

	public void setPlataformas(LinkedList<PlataformaComponentes> plataformas){ this.plataformas = plataformas; }

	public LinkedList<Rectangle> getRectangulos() {return rectangulos;}

	public void setRectangulos(LinkedList<Rectangle> rectangulos) {this.rectangulos = rectangulos;}

}