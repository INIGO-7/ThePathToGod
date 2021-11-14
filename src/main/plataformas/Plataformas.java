package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Plataformas {
	
	private PlataformaComponentes p;
	private PlataformaBasica plataformaBasica;
	private PlataformaHielo plataformaHielo;
	private PlataformaTurbo plataformaTurbo;
	private ArrayList<PlataformaComponentes> plataformas;
	private int saltoBasico = plataformaBasica.SALTO;
	private int saltoTurbo = plataformaTurbo.SALTO;
	private Rectangle colisionPlataforma;
	private int anchuraPlat = 20, alturaPlat = 10;
	private Rectangle colisiones;
	private int x, y, tipo;
	private ArrayList<Rectangle> rectangulos = new ArrayList<Rectangle>();

	public Plataformas(PlataformaBasica plataformaBasica, PlataformaHielo plataformaHielo, PlataformaTurbo plataformaTurbo) {
		plataformas = new ArrayList<PlataformaComponentes>();
		
		this.plataformaBasica = new PlataformaBasica(plataformaBasica.spawnX, plataformaBasica.spawnY);
		this.plataformaHielo = new PlataformaHielo(plataformaHielo.spawnX, plataformaHielo.spawnY);
		this.plataformaTurbo = new PlataformaTurbo(plataformaHielo.spawnX, plataformaHielo.spawnY);
		
		plataformas.add(plataformaBasica);
		plataformas.add(plataformaHielo);
		plataformas.add(plataformaTurbo);
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
	
	public void añadirPlataforma(PlataformaComponentes p) {
		plataformas.add(p);
	}
	
	public void createRect(int x, int y, int tipo) {
		colisiones = new Rectangle(x, y, anchuraPlat, 5);
		rectangulos.add(colisiones);
		setType(tipo);
	}
	
	public void setType(int tipo) {
		this.tipo = tipo;
	}
	
	public int getType() {
		return tipo;
	}

	public ArrayList<PlataformaComponentes> getPlataformas(){ return plataformas; }

	public void setPlataformas(ArrayList<PlataformaComponentes> plataformas){ this.plataformas = plataformas; }

	public ArrayList<Rectangle> getRectangulos() {return rectangulos;}

	public void setRectangulos(ArrayList<Rectangle> rectangulos) {this.rectangulos = rectangulos;}

}