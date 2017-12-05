package is.restauranteLoader;

import is.Fecha;
import is.List;
import is.restaurante.Mesa;
import is.restaurante.Reserva;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class ReservaLoader extends Loader {
	public List<Reserva> loadReservas(String fileName) throws IOException {
		List<Reserva> reservas = new List<Reserva>();
		FileInputStream file;
		try {
			file = new FileInputStream(fileName);
			tokenizer = new StreamTokenizer(new InputStreamReader(file));
			tokenizer.wordChars('\u0021', '\u007E');
			tokenizer.quoteChar('"');
			forceString("BeginReservas");
			while (!peek().equals("EndReservas")) {
				tokenizer.nextToken();
				String name = tokenizer.sval;
				int comensales = forceInt();
				tokenizer.nextToken();
				Fecha fecha = new Fecha();
				fecha = fecha.parse(tokenizer.sval);
				ArrayList<Mesa> mesas = new ArrayList<Mesa>();
				forceString("MesasReserva");
				while (!peek().equals("EndMesasReserva"))
					mesas.add(new Mesa(forceInt(), forceInt()));
				tokenizer.nextToken();
				reservas.add(new Reserva(fecha, name, comensales, mesas));
			}
			return reservas;
		} catch (FileNotFoundException e) {
			System.err.println("El fichero no existe");
			return null;
		}
	}
}
