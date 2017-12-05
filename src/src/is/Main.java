package is;

/**
 * Lanza la aplicación, mediante el Método Modelo-Vista-Controlador
 * @author Jesús
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import is.gui.GUIController;
import is.gui.UserWindow;
import is.restaurante.Menu;
import is.restaurante.Mesa;
import is.restaurante.Reserva;
import is.restaurante.Restaurante;
import is.restauranteLoader.MenuLoader;
import is.restauranteLoader.MesaLoader;
import is.restauranteLoader.ReservaLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		MesaLoader loadMesas = new MesaLoader();
		ArrayList<Mesa> mesas = loadMesas.loadMesas("Mesas.txt");
		ReservaLoader loadReserva = new ReservaLoader();
		List<Reserva> reservas = loadReserva.loadReservas("Reservas.txt");
		MenuLoader loadMenu = new MenuLoader();
		Menu menu = loadMenu.loadMenu("Menu.txt");
		Restaurante rest = new Restaurante(reservas, menu, mesas);
		//Restaurante rest = new Restaurante();
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// .setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		GUIController controlador = new GUIController(rest);
		UserWindow window = new UserWindow(controlador);
		window.setVisible(true);
	}
}
