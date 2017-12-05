package is.restaurante;

import java.util.ArrayList;

/**
 * Permite crear notas y mostrarlas en el tablón. En esta versión del prototipo no será implementado.
 * @author Jesús
 */

public class Tablon {

	public Tablon(){
		notas = new ArrayList<String>();
	}
	public void crearNota(String nota){
		notas.add(nota);
	}
	
	private ArrayList<String> notas;
}
