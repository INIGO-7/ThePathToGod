package main;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.input.EventosRaton;
import main.input.EventosTeclado;

public class VentanaUsuarios extends JFrame{

	protected Button enseñarUsuarios, inicioSesion;
	protected Scanner sc;	
	protected Label addMe;
	protected TextField addUser;
	
	protected static boolean sesionIniciada = false;
	protected static String nombre;
	protected static int llaveUsuario; 
	protected static int valorInt;
	
	public boolean usado = false;
	protected String valor;
	
	HashMap<String, Integer> usuarios = new HashMap<>();
	
	public VentanaUsuarios(){
		setTitle("Usuarios");
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);	
		
		this.setLayout(new FlowLayout());
	
	try {
		
		sc = new Scanner(new File("fileHola.txt"));
		while (sc.hasNextLine()) {
			String linea = sc.nextLine();
			
			String[] campos = linea.split(" ");
			
			for (int k = 0; k < campos.length; k++) {
				if(k == 0) {
					nombre = campos[k];
				}
				if(k == 1) {
					valor = campos[k];
				}
			}

			int valorInt = Integer.parseInt(valor);
			usuarios.put(nombre, valorInt);
			
			}
			} catch (Exception e) {e.printStackTrace();}
	
	
	enseñarUsuarios = new Button("Informaci�n de usuarios");
	enseñarUsuarios.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent a) {
					
				System.out.println(usuarios);
			}
							
	});
			
		add(enseñarUsuarios);
			
		addMe = new Label("Iniciar sesion/crear usuario: ");
		add(addMe);
	
		addUser = new TextField(15);
		add(addUser);
	
		inicioSesion = new Button("Iniciar Sesion");
		inicioSesion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					
				sesionIniciada = true;
				nombre = addUser.getText();
					
				if(usuarios.containsKey(nombre)) {
					
					llaveUsuario = usuarios.get(nombre);
					valorInt = llaveUsuario;
					usuarios.replace(nombre, valorInt);
				}else {		
					valorInt = 0;
					usuarios.put(nombre, valorInt);
				}
				escribeFichero();
			}	
					
		});
			
		add(inicioSesion);
	
	}	
		
	public void añadirPartida() {
					
				if(sesionIniciada) {
					
					valorInt = llaveUsuario + 1;
					usuarios.replace(nombre, valorInt);
					
				}else {
					nombre = "defaultUser";
					if(usuarios.containsKey("defaultUser")) {
						
						llaveUsuario = usuarios.get(nombre);
						valorInt = llaveUsuario + 1;
						usuarios.replace(nombre, valorInt);
					}else {
						usuarios.put(nombre, 1);
						valorInt = 1;
					}
				}escribeFichero();
	}	
	
	
	public void escribeFichero() {
		
		try {
			FileWriter fw = new FileWriter("fileHola.txt", true);
			fw.append("\n" + nombre + " " + valorInt);
			System.out.println("escrito");
			fw.flush();
			fw.close();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	
}