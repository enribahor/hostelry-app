package is.restaurante;

import is.restaurante.consumicion.Consumicion;
import java.util.HashMap;

public interface ComandaInfo {
	public HashMap<Consumicion, Integer> getPrimeros();

	public HashMap<Consumicion, Integer> getSegundos();

	public HashMap<Consumicion, Integer> getPostres();

	public HashMap<Consumicion, Integer> getBebidas();
}
