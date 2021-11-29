package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.input.EventosRaton;
import main.input.EventosTeclado;
import main.plataformas.PlataformaComponentes;

public class Juego implements Runnable{
	
	private Thread thread;
	private Boolean running = false;
	private Ventana ventana;
	private BufferStrategy bs;
	private BufferedImage pantallaGanador;
	
	private MundoGenerado mundoGenerado;
	private EventosRaton eventosRaton;
	private EventosTeclado eventosTeclado;
	
	private VentanaUsuarios ventanaUsuarios = new VentanaUsuarios();
	
	private Jugador jugador;
	private int abajoDcha, abajoIzq, arribaIzq, arribaDcha, i, c = 1;
	private int movPlataformas = 0;

	private Rectangle colisionJugador;
	private BufferedImage pantallaInicial;
	private String titulo;
	private int anchura, altura;
	private int jugando = -450;
	private Graphics graphics;

	public Juego(String titulo, int anchura, int altura) {
		this.titulo = titulo;
		this.anchura = anchura;
		this.altura = altura;
		
		eventosRaton = new EventosRaton();
		eventosTeclado = new EventosTeclado();
		
		jugador = new Jugador(eventosTeclado, this);
		mundoGenerado = new MundoGenerado(this);
		
	}
	
	private void Comienzo() {
		
		ventana = new Ventana(titulo, anchura, altura);		
		ventana.getCanvas().addMouseListener(eventosRaton);
		ventana.getCanvas().addMouseMotionListener(eventosRaton);
		ventana.addKeyListener(eventosTeclado);
		mundoGenerado.init();
		
		{
			try {
				pantallaGanador = ImageIO.read(new File("res/img/winScreenPTG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void render() {

		bs = ventana.getCanvas().getBufferStrategy();
		if(bs == null) {
			//System.out.println("null");
			ventana.getCanvas().createBufferStrategy(3);
			return;
		}

		graphics = bs.getDrawGraphics();
		
		//preparo la pantalla para diobujar la nueva imagen

		graphics.clearRect(0, 0, anchura, altura); //si no hicieramos esto la pantalla parpadea sin parar.

		//dibujo en pantalla

		if(pantallaInicial == null){
			try {
				pantallaInicial = ImageIO.read(new File("res/img/pantallaFullPTG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		graphics.drawImage(pantallaInicial, 0, jugando, null);

		if(!eventosTeclado.isArriba()) jugando = -2250;
		else if(jugando < -1800) jugando += 3;
		//else jugando += 4;
		
		if(eventosTeclado.isArriba())
			jugador.render(graphics);
				
		if(jugando > -1801) {
			mundoGenerado.tick();
			mundoGenerado.render(graphics);
		}

		//actualizo lo dibujado
		
		{
		graphics.dispose();
		bs.show();
		}
	}
	
	public void colisionesPlataformaJugador() {

		colisionJugador = jugador.getAreaSalto();
		
		for(Rectangle r : mundoGenerado.getManager().getRectangulos()) {
			if(r.intersects(colisionJugador)) {
				if(jugador.getjY() < 150 && jugador.getSpeedY() == 0){
					mundoGenerado.getManager().setPlatformsYspeed(4);
				}else{
					jugador.setSpeedY(-4);
				}
			}
		}
	}

	public void tick()	{
		eventosRaton.tick();
		jugador.tick();
		colisionesPlataformaJugador();
		
		if(eventosTeclado.isArriba() && c == 1) {
			ventanaUsuarios.añadirPartida();
			c--;
		}	
		
	}

	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		//gameLoop: ponemos cuantas veces se han de usar render y tick. Lo haremos 60 veces por segundo.
		running = true;
		float tiempoAntes = System.nanoTime();
		Comienzo();
		int frames = 0;
		
		while(running) { 								//funciona todo el rato
			
			float tiempo = 1000000000/60;				//1 segundo = 1000000000 nanosegs, queremos que llame a render y tick cada 1/60 de segundo. 			
			float tiempoAhora = System.nanoTime();
			float tiempoTranscurrido = (tiempoAhora - tiempoAntes);
			//System.out.println(tiempoTranscurrido);	
			//System.out.println(tiempoAhora);
			//System.out.println(tiempoAntes);		
			
			if(tiempoTranscurrido >= tiempo) {
				render();
				tick();
				tiempoTranscurrido = 0;
				tiempoAntes = tiempoAhora;
				frames++;
				
				if(frames == 60) {
					System.out.println("fotogramas por segundo: " + frames);
					frames = 0;
				}
			}
			
		}stop();
	}

	public int getJugando() {return jugando;}

	public void setJugando(int jugando) {this.jugando = jugando;}

	public Graphics getGraphics() { return graphics; }

	public Jugador getJugador(){ return jugador; }
	
	public static void main(String[] args) {
		Juego juego = new Juego("Path To God", 300, 450);
		juego.start();
	}
}