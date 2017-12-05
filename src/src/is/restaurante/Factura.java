package is.restaurante;

import java.util.Vector;

/**
 * Clase que genera una factura a partir de una lista de comandas
 * @author JaimeDan
 * @author Alejandro
 *
 */
public class Factura {
	
	public static String generarFactura(Vector<Comanda> comandas) {
		Comanda comanda = new Comanda();
		for (Comanda c : comandas)
			comanda.append(c);
		return comanda.toString();
	}
	
}
