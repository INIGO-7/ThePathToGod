package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Ventana extends JFrame{

	private String titulo;
	private int anchura, altura;
	private JFrame frame;
	private Canvas canvas;
	
	
	public Ventana(String titulo,int anchura,int altura) {
	
		setSize(anchura, altura);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(titulo);
		setLocationRelativeTo(null);
		setVisible(true);
		
		canvas = new Canvas();
		canvas.setSize(anchura, altura);
		canvas.setFocusable(false);
		
		add(canvas);
	
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}