package main.plataformas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlataformaHielo extends PlataformaComponentes{
	
	protected static int DESLIZAMIENTO = 2;
	private BufferedImage platHielo;
	private int anchuraPlat = 20, alturaPlat = 10;
	private Rectangle platColision;
	
	public PlataformaHielo(int spawnX, int spawnY) {
		super(spawnX, spawnY);
		salto = -4;
		platColision = new Rectangle(spawnX, spawnY, anchuraPlat, 5);

		try {
			platHielo = ImageIO.read(new File("res/img/plataformaHieloSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(){

	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(platHielo, platX, platY, null);
	}
	
	public void tick() {
		
	}

	public static int getDESLIZAMIENTO() {
		return DESLIZAMIENTO;
	}

	public Rectangle getPlatColision() {
		return platColision;
	}

	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
	}

}
