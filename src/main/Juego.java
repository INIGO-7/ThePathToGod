package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLDocument.Iterator;

import main.input.EventosRaton;
import main.input.EventosTeclado;
import main.plataformas.PlataformaBasica;
import main.plataformas.PlataformaComponentes;
import main.plataformas.PlataformaHielo;
import main.plataformas.PlataformaTurbo;
import main.plataformas.Plataformas;

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
	private int mundo = 1;
	private int movPlataformas = 0;

	private Plataformas p;
	private PlataformaBasica plataformaBasica;
	private PlataformaHielo plataformaHielo;
	private PlataformaTurbo plataformaTurbo;
	private Rectangle colisionJugador, colisionPlataformaBasica, colisionPlataformaTurbo, colisionPlataformaHielo;
	
	
	private String titulo;
	private int anchura, altura;
	int jugando = -450;
	private Graphics graphics;
	
	public Juego(String titulo, int anchura, int altura) {
		this.titulo = titulo;
		this.anchura = anchura;
		this.altura = altura;
		
		eventosRaton = new EventosRaton();
		eventosTeclado = new EventosTeclado();
		
		jugador = new Jugador(eventosTeclado, this);
		mundoGenerado = new MundoGenerado(this);
		
		plataformaBasica = new PlataformaBasica(122, 400);
		plataformaTurbo = new PlataformaTurbo(40, 400);
		plataformaHielo = new PlataformaHielo(200, 400);
		p = new Plataformas(plataformaBasica, plataformaHielo, plataformaTurbo);
		p.createRect(122, 400, 1);
		p.createRect(40, 400, 2);
		p.createRect(200, 400, 3);
		
	}

	ArrayList<PlataformaComponentes> plataformas;
	
	private void Comienzo() {
		
		ventana = new Ventana(titulo, anchura, altura);		
		ventana.getCanvas().addMouseListener(eventosRaton);
		ventana.getCanvas().addMouseMotionListener(eventosRaton);
		ventana.addKeyListener(eventosTeclado);
		plataformas = p.getPlataformas();
		
		{
			try {
				pantallaGanador = ImageIO.read(new File("res/img/winScreenPTG.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
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
		
		if(!eventosTeclado.isArriba()) jugando = -2250;
		else if(jugando < -1800) jugando += 3;
		//else jugando += 4;
		
		mundoGenerado.render(graphics);
		
		if(eventosTeclado.isArriba())
			jugador.render(graphics);
				
		if(jugando > -1801) 
			p.render(graphics);
			//movPlataformas += 4;
			
			
			if(mundo == 1) {
			
				p.añadirPlataforma(new PlataformaBasica(200, 320));
				p.createRect(200, 320, 1);
				p.añadirPlataforma(new PlataformaBasica(110, 240));
				p.createRect(110, 240, 1);
				p.añadirPlataforma(new PlataformaBasica(100, 210));
				p.createRect(100, 210, 1);
				p.añadirPlataforma(new PlataformaBasica(150, 120));
				p.createRect(150, 120, 1);
				p.añadirPlataforma(new PlataformaBasica(50, 320));
				p.createRect(50, 320, 1);
				p.añadirPlataforma(new PlataformaBasica(50, 240));
				p.createRect(50, 240, 1);
				p.añadirPlataforma(new PlataformaBasica(50, 210));
				p.createRect(50, 210, 1);
				p.añadirPlataforma(new PlataformaBasica(50, 120));
				p.createRect(50, 120, 1);
			
			
			}else if(mundo == 2) {
			
				p.getPlataformas().clear();
				p.getRectangulos().clear();
				p.añadirPlataforma(new PlataformaBasica(40, 350));
				p.createRect(40, 350, 1);
				p.añadirPlataforma(new PlataformaBasica(120, 260));
				p.createRect(120, 260, 1);
				p.añadirPlataforma(new PlataformaBasica(150, 180));
				p.createRect(150, 180, 1);	
				p.añadirPlataforma(new PlataformaBasica(200, 130));
				p.createRect(200, 130, 1);
			
			}else if(mundo == 3) {
				
				p.getPlataformas().clear();
				p.getRectangulos().clear();
				p.añadirPlataforma(new PlataformaBasica(280, 350));
				p.createRect(280, 350, 1);
				p.añadirPlataforma(new PlataformaBasica(0, 310));
				p.createRect(0, 310, 1);
				p.añadirPlataforma(new PlataformaBasica(260, 220));
				p.createRect(260, 220, 1);
				p.añadirPlataforma(new PlataformaBasica(0, 150));
				p.createRect(0, 150, 1);
				p.añadirPlataforma(new PlataformaBasica(150, 80));
				p.createRect(150, 80, 1);
			}else{
				graphics.drawImage(pantallaGanador, 0, 0, null);
				jugador.setMovimientoY(0);
			}	
			
		//actualizo lo dibujado
		
		{
		graphics.dispose();
		bs.show();
		}
	}
	
	public void colisionesPlataformaJugador() {	
		
		
		colisionJugador = jugador.getAreaSalto();
			
		ArrayList<Rectangle> rectangulos = p.getRectangulos();
		
		for(Rectangle r : rectangulos) {
			if(r.intersects(colisionJugador)) {
				jugador.setMovimientoY(-4);
			}
			/*{
				if()
			}*/
		}
	}

	public void tick()	{
		eventosRaton.tick();
		jugador.tick();
		colisionesPlataformaJugador();
		p.tick();
		
		if(eventosTeclado.isArriba() && c == 1) {
			ventanaUsuarios.añadirPartida();
			c--;
		}	
		
	}

	public synchronized void start() {
		
		thread = new Thread(this);
		thread.run();
		
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

	public int getMundo(){return mundo;}

	public void setMundo(int mundo){this.mundo = mundo;}
	
	public static void main(String args[]) {
		Juego juego = new Juego("Path To God", 300, 450);
		juego.start();
	}
}