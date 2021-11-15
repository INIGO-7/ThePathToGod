package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlataformaTurbo extends PlataformaComponentes{

	private BufferedImage platTurbo;
	private int anchuraPlat = 20, alturaPlat = 10;
	//private ArrayList<Rectangle> colisiones = new ArrayList<Rectangle>();
	private Rectangle platColision;
	
	public PlataformaTurbo(int spawnX, int spawnY) {
		super(spawnX, spawnY);
		salto = -8;
		platColision = new Rectangle(spawnX, spawnY, anchuraPlat, 5);

		try {
			platTurbo = ImageIO.read(new File("res/img/plataformaTurboSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(){

	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(platTurbo, platX, platY, null);
	}
	
	public void tick() {
		
	}
	
//	public ArrayList<Rectangle> getRect() {
//		return colisiones;
//	}

	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
	}

}