package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.input.EventosRaton;
import main.input.EventosTeclado;

public class Game implements Runnable{
	
	private Thread thread;
	private Boolean running = false;
	private Ventana ventana;
	private BufferStrategy bs;
	private BufferedImage pantallaInicial, pantallaGanador, gameOver;
	
	private World world;
	private EventosRaton eventosRaton;
	private EventosTeclado eventosTeclado;
	
	private VentanaUsuarios ventanaUsuarios;
	
	private Player player;
	private int abajoDcha, abajoIzq, arribaIzq, arribaDcha, i, c = 1;
	private int movPlataformas = 0;

	private Rectangle colisionJugador;
	private String titulo;
	private int anchura, altura;
	private Graphics graphics;

	public Game(String titulo, int anchura, int altura) {
		this.titulo = titulo;
		this.anchura = anchura;
		this.altura = altura;

		try {
			pantallaInicial = ImageIO.read(new File("res/img/pantallaFullPTG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			pantallaGanador = ImageIO.read(new File("res/img/winScreenPTG.png"));
			gameOver = ImageIO.read(new File("res/img/gameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		eventosRaton = new EventosRaton();
		eventosTeclado = new EventosTeclado();

		ventanaUsuarios = new VentanaUsuarios();
		player = new Player(eventosTeclado);
		world = new World(this, eventosTeclado);
		world.init();

		ventana = new Ventana(titulo, anchura, altura);
		ventana.getCanvas().addMouseListener(eventosRaton);
		ventana.getCanvas().addMouseMotionListener(eventosRaton);
		ventana.addKeyListener(eventosTeclado);
		
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

		if(!eventosTeclado.isArriba()){
			//If the up arrow is not pressed, just render the initial image.
			graphics.drawImage(pantallaInicial, 0, -2250, null);
		}else if(world.getPlayer().getjY() < 450){
			//If the up arrow is pressed and the player is on the screen (has not fallen), render the world.
			world.render(graphics);
		}else{
			//If the player has fallen, render the gameover image.
			graphics.drawImage(gameOver, 0, 0, null);
		}

		/*
		graphics.drawImage(pantallaInicial, 0, screenY, null);

		if(!eventosTeclado.isArriba()) screenY = -2250;
		else if(screenY < -1800) screenY += 3;
		//else jugando += 4;
		
		if(eventosTeclado.isArriba())
			jugador.render(graphics);
				
		if(screenY > -1801) {
			world.tick();
			world.render(graphics);
		}
		*/


		//actualizo lo dibujado
		
		{
		graphics.dispose();
		bs.show();
		}
	}

	public void tick(){
		eventosRaton.tick();
		player.tick();

		if(world.getPlayer().getjY() < 450 && eventosTeclado.isArriba()){
			world.tick();
		}

		/*
		if(eventosTeclado.isArriba() && c == 1) {
			ventanaUsuarios.añadirPartida();
			c--;
		}	
		*/
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
		int frames = 0;
		
		while(running) { 								//always working
			
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

	public Graphics getGraphics() { return graphics; }

	public Player getPlayer(){ return player; }

	public VentanaUsuarios getVentanaUsuarios(){ return ventanaUsuarios; }

	public BufferedImage getPantallaInicial(){
		return pantallaInicial;
	}
	
	public static void main(String[] args) {
		Game game = new Game("Path To God", 300, 450);
		game.start();
	}

}