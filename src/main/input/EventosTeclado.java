package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.VentanaUsuarios;

public class EventosTeclado implements KeyListener{

	public boolean dcha = false, izq = false, arriba = false;
	public void keyPressed(KeyEvent event) {
		
		if(event.getKeyCode() == KeyEvent.VK_UP) arriba = true;
		if(event.getKeyCode() == KeyEvent.VK_LEFT) izq = true;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) dcha = true;
	}

	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_LEFT) izq = false;
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) dcha = false;		
	}
	
	public void keyTyped(KeyEvent event) {}
		
}