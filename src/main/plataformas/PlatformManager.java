package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PlatformManager {
	
	private PlataformaComponentes p;
	private PlataformaBasica plataformaBasica;
	private PlataformaHielo plataformaHielo;
	private PlataformaTurbo plataformaTurbo;
	private ArrayList<PlataformaComponentes> plataformas;	//we create an array of PlataformaComponentes (this is the parent abstract class for ice, turbo and basic platforms)
	private ArrayList<Rectangle> rectangulos;

	private Rectangle colisionPlataforma;
	private int anchuraPlat = 20, alturaPlat = 10;
	private int x, y;

	public PlatformManager() {
		plataformas = new ArrayList<PlataformaComponentes>();
		rectangulos = new ArrayList<Rectangle>();
	}
	
	public void render(Graphics graphics) {
		for(PlataformaComponentes p : plataformas) {
			p.render(graphics);
		}
	}
	
	public void tick() {
		for(PlataformaComponentes p : plataformas) {
			p.tick();
		}
	}

	public void createPlatform(PlataformaComponentes p){
		plataformas.add(p);
		rectangulos.add(p.getRect());	//we add the original collisions rectangle to an arraylist containing every platform,
										//to check afterwards whether the player intersects with it or not.
	}

	public ArrayList<PlataformaComponentes> getPlataformas(){ return plataformas; }

	public void setPlataformas(ArrayList<PlataformaComponentes> plataformas){ this.plataformas = plataformas; }

	public ArrayList<Rectangle> getRectangulos() {return rectangulos;}

	public void setRectangulos(ArrayList<Rectangle> rectangulos) {this.rectangulos = rectangulos;}

}