package is.gui;

import is.restaurante.Menu;
import is.restaurante.Restaurante;
import is.restaurante.consumicion.Consumicion;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Panel con todos los platos disponibles al realizar un pedido, son platos de un determinado tipo
 * esta clase puede que se tenga que quitar
 * 
 * @author JaimeDan
 * @author Jesús
 */
@SuppressWarnings("serial")
public class PlatosPanel extends JPanel{

	public PlatosPanel(Restaurante rest){
		this.menu = rest.getMenu();

		//El grid layout tiene que tener el tamaño del menu que corresponda, pero ponemos un ancho fijo
		this.setLayout(new GridLayout(4, 11));

		
	}
	
	public PlatosPanel(GUIController rest){
		
	}
	
	public PlatosPanel(int i, GUIController rest) {
		this.menu = rest.getMenu();
		// Denotamos 0 bebidas 1 primero 2 segundo 3 postre
		if (i == 0) platos = menu.getBebidas();
		else if(i == 1) platos = menu.getPrimeros();
		else if (i == 2)platos = menu.getSegundos();
		else if (i == 3) platos = menu.getPostres();
		generarPanel();
	}

	private void generarPanel() {
		celdaPlatos = new ArrayList<CeldaPlato>();
				for (Consumicion c: platos){
					CeldaPlato cp = new CeldaPlato(null, c, 0, 0, null);
					celdaPlatos.add(cp);
					this.add(cp);
				}				
	}

	private ArrayList<Consumicion> platos;
	private Menu menu;
	private ArrayList<CeldaPlato> celdaPlatos;
	CeldaPlato[] celdas;
}