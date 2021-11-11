package main.input;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.VentanaUsuarios;

public class EventosRaton implements MouseListener, MouseMotionListener{
	
	private boolean clickIzq, encima, usado;
	private Rectangle areaBotonUsuarios = new Rectangle(88, 8, 120, 30);
	private MouseEvent event; 
	private float xRaton, yRaton;
	private EventosTeclado eventosTeclado = new EventosTeclado();
	private VentanaUsuarios ventanaUsuarios;
		
	
	public void mouseClicked(MouseEvent event) {}

	public void mouseEntered(MouseEvent event) {}

	public void mouseExited(MouseEvent event) {}

	public void mousePressed(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1)
			clickIzq = true;
	}
	
	public void mouseReleased(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1)
			clickIzq = false;
	}
	
	public void mouseDragged(MouseEvent event) {
	}

	
	public void mouseMoved(MouseEvent event) {
		xRaton = event.getX();
		yRaton = event.getY();
	}
	
	public void isClicked(MouseEvent event) {
		
		if(areaBotonUsuarios.contains(xRaton, yRaton)) {
			encima = true;
			presionarBotonUsuarios();
		}
	}
	
	public void presionarBotonUsuarios() {
		if(encima && clickIzq) {
			if(!usado) {
				ventanaUsuarios = new VentanaUsuarios();
				usado = true;
			}
		}
	}
	
	public void tick() {
		isClicked(event);
	}
	
}