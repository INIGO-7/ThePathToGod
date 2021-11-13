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
import java.util.regex.Pattern;

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
		sc = new Scanner(new File("res/txt/usersLog.txt"));

		while (sc.hasNextLine()) {

			String linea = sc.nextLine();
			String[] lineaSplit = linea.split(" ");	//this will hold two values: the username (index 0)
															//and the associated number of sessions played (index 1)
			usuarios.put(lineaSplit[0], Integer.parseInt(lineaSplit[1]));
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
				}
				escribeFichero();
	}	
	
	
	public void escribeFichero() {
		
		try {
			String existingName = null;
			Scanner sc1 = new Scanner(new File("res/txt/usersLog.txt"));

			while (sc1.hasNextLine()) {
				String linea = sc1.nextLine();

				if(Pattern.compile(nombre).matcher(linea).find()) {
					existingName = linea;
					break;
				}
			}

			Scanner sc2 = new Scanner(new File("res/txt/usersLog.txt"));

			//in filewriter, the second argument indicates whether or not the previous contents of the file will be overwritten.
			//false means that the contents will be overwritten, while true means the text to write will be appended.

			if(existingName == null) {
				FileWriter fw = new FileWriter("res/txt/usersLog.txt", true);
				fw.write(nombre + " " + valorInt + "\n");
				fw.flush(); fw.close();
			}else {
				StringBuffer buffer = new StringBuffer();
				while (sc2.hasNextLine()) buffer.append(sc2.nextLine() + System.lineSeparator());    //I put the whole file into a stringbuffer.

				FileWriter fw = new FileWriter("res/txt/usersLog.txt", false);
				fw.write(buffer.toString().replaceAll(existingName, nombre + " " + valorInt));
				//I change the number of sessions played by the user and afterwards append the whole file contents updated.
				fw.flush(); fw.close();
			}

		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	
}