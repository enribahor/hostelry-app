package is.restauranteLoader;

import is.restaurante.Menu;
import is.restaurante.consumicion.Consumicion;
import is.restaurante.consumicion.Plato;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class MenuLoader extends Loader {

	private ArrayList<Consumicion> forceConsumicion(String tipo)
			throws IOException {
		ArrayList<Consumicion> platos = new ArrayList<Consumicion>();
		while (!peek().equals(tipo)) {
			tokenizer.nextToken();
			String name = tokenizer.sval;
			tokenizer.nextToken();
			String descripcion = tokenizer.sval;
			float precio = forceFloat();
			boolean disponible = forceString("Disponible", "NoDisponible");
			Plato plato = new Plato(name, precio, disponible, descripcion);
			platos.add(plato);
		}
		tokenizer.nextToken();
		return platos;
	}

	public Menu loadMenu(String fileName) throws IOException {
		FileInputStream file;
		try {
			file = new FileInputStream(fileName);
			tokenizer = new StreamTokenizer(new InputStreamReader(file));
			tokenizer.wordChars('\u0021', '\u007E');
			tokenizer.quoteChar('"');
			forceString("BeginMenu");
			forceString("BeginPrimeros");
			ArrayList<Consumicion> listaPrimeros = forceConsumicion("EndPrimeros");
			forceString("BeginSegundos");
			ArrayList<Consumicion> listaSegundos = forceConsumicion("EndSegundos");
			forceString("BeginBebidas");
			ArrayList<Consumicion> listaBebidas = forceConsumicion("EndBebidas");
			forceString("BeginPostres");
			ArrayList<Consumicion> listaPostres = forceConsumicion("EndPostres");
			forceString("EndMenu");
			return new Menu(listaPrimeros, listaSegundos, listaBebidas,
					listaPostres);
		} catch (FileNotFoundException e) {
			System.err.println("El fichero no existe");
			return null;
		}
	}
}
