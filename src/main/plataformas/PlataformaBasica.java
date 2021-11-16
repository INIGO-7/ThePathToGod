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
	private final int ANCHURA_PLAT = 20, ALTURA_PLAT = 5;

	public PlataformaBasica(int spawnX, int spawnY) {
		super(spawnX, spawnY);
		salto = -4;
		platColision = new Rectangle(spawnX, spawnY, ANCHURA_PLAT, ALTURA_PLAT);

		try {
			platBasic = ImageIO.read(new File("res/img/plataformaBasicaSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(){

		if(platX > 299 - ANCHURA_PLAT || platX < 1) movementX = -movementX;
		platX += movementX;
		platColision.setLocation((int) platColision.getX() + movementX, (int) platColision.getY());

	}
	
	public void render(Graphics graphics) {

		graphics.drawImage(platBasic, platX, platY, null);

	}
	
	public void tick() {

		move();

	}

	public Rectangle getPlatColision() {
		return platColision;
	}

	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
		
	}

}
