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
	protected static int SALTO = -8;
	public int spawnX = 122, spawnY = 350;
	private int anchuraPlat = 20, alturaPlat = 10;
	//private ArrayList<Rectangle> colisiones = new ArrayList<Rectangle>();
	private Rectangle platColision = new Rectangle(spawnX, spawnY, anchuraPlat, alturaPlat);
	
	public PlataformaTurbo(int spawnX, int spawnY) {
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		platColision = new Rectangle(spawnX, spawnY, anchuraPlat, 5);
		//this.colisiones.add(platColision);
	}
	
	{
		try {
			platTurbo = ImageIO.read(new File("res/img/plataformaTurboSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		}
	
	public void render(Graphics graphics) {
		graphics.drawImage(platTurbo, spawnX, spawnY, null);
	}
	
	public void tick() {
		platColision = new Rectangle(spawnX, spawnY, anchuraPlat, alturaPlat);
		
	}
	
	public static int getSALTO() {
		return SALTO;
	}
	
	public Rectangle getPlatColision() {
		return platColision;
	}
	
//	public ArrayList<Rectangle> getRect() {
//		return colisiones;
//	}

	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
	}

}