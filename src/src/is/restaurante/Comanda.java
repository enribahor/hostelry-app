package is.restaurante;

import is.restaurante.consumicion.Bebida;
import is.restaurante.consumicion.Consumicion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Clase que guarda una comanda, guardando todas las bebidas, primeros, segundos
 * y postres que se han elegido y la cantidad de cada uno de ellos
 * 
 * @author JaimeDan
 * 
 */
public class Comanda implements ComandaInfo {

	@SuppressWarnings("unchecked")
	public Comanda() {
		observers = new ArrayList<ComandaObserver>();
		comanda = new HashMap[4];
		for (int i = 0; i < 4; i++)
			comanda[i] = new HashMap<Consumicion, Integer>();
	}

	public void addObserver(ComandaObserver obs) {
		this.observers.add(obs);
	}

	private void addConsumicion(Consumicion consumicion, int index) {
		if (comanda[index].containsKey(consumicion)) {
			comanda[index]
					.put(consumicion, comanda[index].get(consumicion) + 1);
		} else
			comanda[index].put(consumicion, 1);
		consumicion.getPrecio();
	}

	private void deleteConsumicion(Consumicion consumicion, int index) {
		if (comanda[index].containsKey(consumicion)) {
			comanda[index]
					.put(consumicion, comanda[index].get(consumicion) - 1);
			if (comanda[index].get(consumicion) == 0)
				comanda[index].remove(consumicion);
			consumicion.getPrecio();
			emitirCambio();
		} else
			emitirError("No existe la consumicion de nombre " + consumicion.getNombre());
	}
	
	public void setConsumicionTimes(Consumicion consumicion, int veces, TipoPlatos tPlato){
		boolean hasChanged = false;
		if (veces <0){ return;}
		for (int i=0; i<4; i++){
			if (comanda[i].containsKey(consumicion)){
				//si las veces son 0, entonces quitamos la consumicion del pedido
				if (veces == 0)
					comanda[i].remove(consumicion);
				//si no, ponemos el numero correspondiente de veces
				else
					comanda[i].put(consumicion, veces);
				hasChanged = true;
				emitirCambio();
				break;
			}
		}
		//si no se ha encontrado es que no estaba, asi que lo añadimos con las veces veces
		if (!hasChanged){
			comanda[tPlato.toInt()].put(consumicion, veces);// emitError
			emitirCambio();
		}
	}

	public void addPrimero(Consumicion primero) {
		addConsumicion(primero, 0);
		emitirCambio();
	}

	public void deletePrimero(Consumicion primero) {
		deleteConsumicion(primero, 0);
		emitirCambio();
	}

	public void setVecesPrimero(Consumicion primero) {

	}

	public void addSegundo(Consumicion segundo) {
		addConsumicion(segundo, 1);
		emitirCambio();
	}

	public void deleteSegundo(Consumicion segundo) {
		deleteConsumicion(segundo, 1);
		emitirCambio();
	}

	public void setVecesSegundo(Consumicion segundo) {

	}

	public void addPostre(Consumicion postre) {
		addConsumicion(postre, 2);
		emitirCambio();
	}

	public void deletePostre(Consumicion postre) {
		deleteConsumicion(postre, 2);
		emitirCambio();
	}

	public void setVecesPostre(Consumicion postre) {

	}

	public void addBebida(Consumicion bebida) {
		addConsumicion(bebida, 3);
	}

	public void deleteBebida(Consumicion bebida) {
		deleteConsumicion(bebida, 3);
		emitirCambio();
	}

	public void setVecesBebidas(Bebida drinking) {

	}

	public void append(Comanda other) {
		for (int tipoPlato = 0; tipoPlato < 4; tipoPlato++) {
			Set<Consumicion> claveOther = other.comanda[tipoPlato].keySet();
			// Obtenemos un vector con los elementos de la otra Comanda del tipo
			// de plato i
			Consumicion[] consumicionesOther = claveOther
					.toArray(new Consumicion[claveOther.size()]);
			// Para cada elemento del vector, los añadimos tanta veces como
			// existira en la otra comanda
			for (int pos = 0; pos < consumicionesOther.length; pos++) {
				int veces = other.comanda[tipoPlato]
						.get(consumicionesOther[pos]);
				for (int num = 0; num < veces; num++)
					addConsumicion(consumicionesOther[pos], tipoPlato);
			}
		}
	}

	public String toString() {
		double price = 0;
		String pedido = "";
		for (int i = 0; i < 4; i++)
			for (Entry<Consumicion, Integer> entry : comanda[i].entrySet()) {
				pedido = pedido + entry.getKey().getNombre() + " "
						+ entry.getKey().getPrecio() + " Cantidad: "
						+ comanda[i].get(entry.getKey()) + LINE_SEPARATOR;
				price += entry.getKey().getPrecio()*comanda[i].get(entry.getKey());
			}
		pedido += "TOTAL: " + price;
		
		return pedido;
	}

	public List<Consumicion> lista() {
		return null;

	}
	
	public void setNumComanda(int n){
		for (ComandaObserver obs: observers)
			obs.setComandaNumber(n);
	}

	public HashMap<Consumicion, Integer> getPrimeros() {
		return comanda[0];
	}

	public HashMap<Consumicion, Integer> getSegundos() {
		return comanda[1];
	}

	public HashMap<Consumicion, Integer> getPostres() {
		return comanda[2];
	}

	public HashMap<Consumicion, Integer> getBebidas() {
		return comanda[3];
	}

	private void emitirCambio() {
		for (ComandaObserver obs : observers)
			obs.comandaHaCambiado(this.toString());
	}

	private void emitirError(String error) {
		for (ComandaObserver obs : observers)
			obs.comandaError(error);
	}
	
	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private HashMap<Consumicion, Integer> comanda[];
	private ArrayList<ComandaObserver> observers;

}
