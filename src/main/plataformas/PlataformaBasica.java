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
	public int spawnX, spawnY;
	private Rectangle platColision = new Rectangle(spawnX, spawnY, anchuraPlat, alturaPlat);
	protected static int SALTO = -4;
	private ArrayList<Rectangle> colisiones = new ArrayList<Rectangle>();
	
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
	};
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

}
