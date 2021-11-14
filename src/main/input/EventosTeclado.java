package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.VentanaUsuarios;

public class EventosTeclado implements KeyListener{

	private boolean dcha = false;
	private boolean izq = false;
	private boolean arriba = false;

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

	public boolean isDcha() {return dcha;}

	public void setDcha(boolean dcha) {this.dcha = dcha;}

	public boolean isIzq() {return izq;}

	public void setIzq(boolean izq) {this.izq = izq;}

	public boolean isArriba() {return arriba;}

	public void setArriba(boolean arriba) {this.arriba = arriba;}
}