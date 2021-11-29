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

	public PlataformaBasica(int spawnX, int spawnY) {
		super(spawnX, spawnY);
		salto = -4;
		ANCHURA_PLAT = 20;
		ALTURA_PLAT = 5;
		platColision = new Rectangle(spawnX, spawnY, ANCHURA_PLAT, ALTURA_PLAT);

		try {
			platBasic = ImageIO.read(new File("res/img/plataformaBasicaSmallPTG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics graphics) {

		graphics.drawImage(platBasic, platX, platY, null);

	}
	
	public void tick() {
		platformMovementX();
	}

	public Rectangle getPlatColision() {
		return platColision;
	}

	public void setPlatColision(Rectangle platColision) {
		this.platColision = platColision;
		
	}

}
