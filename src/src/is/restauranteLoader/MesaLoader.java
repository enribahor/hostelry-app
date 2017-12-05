package is.restauranteLoader;

import is.restaurante.Mesa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class MesaLoader extends Loader {
	public ArrayList<Mesa> loadMesas(String fileName) throws IOException {
		ArrayList<Mesa> mesas = new ArrayList<Mesa>();
		FileInputStream file;
		try {
			file = new FileInputStream(fileName);
			tokenizer = new StreamTokenizer(new InputStreamReader(file));
			tokenizer.wordChars('\u0021', '\u007E');
			tokenizer.quoteChar('"');
			forceString("BeginMesas");
			int numMesa = 0;
			while (!peek().equals("EndMesas")) {
				forceInt(numMesa);
				int capacidad = forceInt();
				Mesa mesa = new Mesa(numMesa, capacidad);
				mesas.add(mesa);
				numMesa++;
			}
			return mesas;
		} catch (FileNotFoundException e) {
			System.err.println("El fichero no existe");
			return null;
		}
	}
}
