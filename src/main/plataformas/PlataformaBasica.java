package main.plataformas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlataformaBasica extends PlataformaComponentes{

	private BufferedImage platBasic;
	private int anchuraPlat = 20, alturaPlat = 10;
	private int spawnX, spawnY;
	private Rectangle platColision;
	protected static int SALTO = -4;
	private ArrayList<Rectangle> colisiones = new ArrayList<>();
	
	public PlataformaBasica(int spawnX, int spawnY) {
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		platColision = new Rectangle(spawnX, spawnY, anchuraPlat, 5);
		colisiones.add(platColision);
	}

	{
		try {
			platBasic = ImageIO.read(new File("res/img/plataformaBasicaSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graphics) {
		
	
		graphics.drawImage(platBasic, spawnX, spawnY, null);
		graphics.setColor(Color.red);
		//graphics.fillRect(spawnX, spawnY, platColision.width, 5);
	}
	
	public void tick() {
		
	}

	public Rectangle getPlatColision() {
		return platColision;
	}


	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
		
	}
	
	public ArrayList<Rectangle> getRect() {
		return colisiones;
	}

	public static int getSALTO() {
		return SALTO;
	}

	public int getSpawnX(){ return this.spawnX; }
	public void setSpawnX(int spawnX){ this.spawnX = spawnX; }

	public int getSpawnY(){ return this.spawnY; }
	public void setSpawnY(int spawnY){ this.spawnY = spawnY; }

}
