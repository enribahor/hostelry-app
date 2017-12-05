package is.gui;

import is.Usuario;
import is.restaurante.Restaurante;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Presenta la ventana de Login, donde el empleado introduce su nombre y contraseña.
 * Si es correcta le permite acceder a la instancia RestauranteSwing que desde aquí se crea.
 * @author Jesús
 */


@SuppressWarnings("serial")
public class UserWindow extends JFrame  {
	
	public void initializeGUI(){
		this.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem quit = new JMenuItem("Quit");
		menu.add(quit);
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CloseApp.quitApp();		
			}
			
		});
		this.setJMenuBar(menuBar);	
		
		ImageIcon kike = createImageIcon("images/kikeHost2.png", "KIKE");
		setIconImage(new ImageIcon(getClass().getResource("images/icono.png")).getImage());
		logo = new JLabel(kike);
		logo.setSize(20, 20);
		this.add(logo, BorderLayout.NORTH);
		front = new JPanel();
		front.setLayout(new GridLayout(3,1, 3, 3));
		JPanel usuario = new JPanel();
		usuario.setLayout(new GridLayout(2,1));
		JPanel contrasena = new JPanel();
		contrasena.setLayout(new GridLayout(2,1));
		JLabel textoUsuario = new JLabel("USUARIO");
		textoUsuario.setOpaque(true);
		usuario.add(textoUsuario, BorderLayout.NORTH);
		user = new JTextField(10);
		usuario.add(user);
		JLabel textoContrasena = new JLabel("CONTRASEÑA");
		textoUsuario.setOpaque(true);
		contrasena.add(textoContrasena, BorderLayout.NORTH);
		password = new JPasswordField(10);
		password.getPassword();
		contrasena.add(password);
		front.add(usuario);
		front.add(contrasena);
		front.add(new JButton("Entrar") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (!user.getText().isEmpty()){
							if(registroCorrecto()) 
								mostrarRestaurante();
							else notificar("Usuario o contraseña incorrectos");
						}					
					}

					private void showMessage(String string) {
						// TODO Auto-generated method stub
						
					}

				
				});
				
			}

		});
		this.add(front);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//RestauranteSwing rest = new RestauranteSwing(new Restaurante());
		//RestauranteSwing rest = new RestauranteSwing();
		
	}
	
	public UserWindow(GUIController controller) {	
		super("KIKE HOSTELERIA S.A.");
		this.controlador = controller;
		this.setSize(800, 600);
		initializeGUI();
		usuarios = new Usuario[1];
		usuarios[0] = new Usuario("Usuario", "Contraseña");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	private void notificar(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void mostrarRestaurante(){

		this.remove(logo);

		this.remove(front);
		this.setSize(650,400);
		RestauranteSwing rest = new RestauranteSwing(controlador);
		this.add(rest);
		
	}
	private boolean registroCorrecto(){
		boolean b = false;
			for (int i = 0; i< usuarios.length && !b; i++){
				if (user.getText().equals(usuarios[i].getId()) && passwordCorrecto(password.getPassword(), usuarios[i].getPassword()))  b = true;
			}
		return b;
	}
	private boolean passwordCorrecto(char[] p1, char[] p2){
		boolean b = true;
		if (p1.length != p2.length) return false;
		else for (int i = 0; i < p1.length && b; i++){
			if (p1[i] != p2[i]) b = false;
	
		}
		return b;
		
		
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	private JMenuBar menuBar;
	private GUIController controlador;
	private JTextField user;
	private JPasswordField password;
	private Usuario[] usuarios;
	private JLabel logo;
	private JPanel front;
	private RestauranteSwing rest;
		
}