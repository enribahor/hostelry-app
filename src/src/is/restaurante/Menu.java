package is.restaurante;

import is.restaurante.consumicion.Bebida;
import is.restaurante.consumicion.Consumicion;
import is.restaurante.consumicion.Plato;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase que guarda el menu, clasificando las consumiciones segun sean bebidas,
 * primeros, segundos
 * 
 * @author JaimeDan
 * 
 */
public class Menu implements MenuInfo {

	public Menu() {
		observers = new ArrayList<MenuObserver>();
		bebidas = new ArrayList<Consumicion>();
		primeros = new ArrayList<Consumicion>();
		segundos = new ArrayList<Consumicion>();
		postres = new ArrayList<Consumicion>();
		bebidas = new ArrayList<Consumicion>(2);
		bebidas.add(new Bebida("Vodka-Limón", 1, true, null));
		bebidas.add(new Bebida("Anticongelante", 5, true, null));
		primeros = new ArrayList<Consumicion>(2);
		primeros.add(new Plato("Lentejas", 5, true, null));
		primeros.add(new Plato("Canelones", 5, true, null));		
		segundos = new ArrayList<Consumicion>(2);
		segundos.add(new Plato("Filete de caballo", 5, true, null));		
		segundos.add(new Plato("Almondigas", 5, true, null));		
		
		postres = new ArrayList<Consumicion>(2);
		postres.add(new Plato("Helado de gordaco", 5, true, null));		
		postres.add(new Plato("Manzana de Newton", 5, true, null));		
		
		
	}

	public Menu(ArrayList<Consumicion> listaPrimeros,
			ArrayList<Consumicion> listaSegundos,
			ArrayList<Consumicion> listaBebidas,
			ArrayList<Consumicion> listaPostres) {
		observers = new ArrayList<MenuObserver>();
		primeros = listaPrimeros;
		segundos = listaSegundos;
		bebidas = listaBebidas;
		postres = listaPostres;
	}

	/** Devuelve un string con los platos correspondientes **/
	public ArrayList<Consumicion> getPlatos(TipoPlatos tipoPlato) {
		switch (tipoPlato) {
		case BEBIDA:
			return bebidas;
		case PRIMERO:
			return primeros;
		case SEGUNDO:
			return segundos;
		case POSTRE:
			return postres;
		default:
			return null;
		}
	}

	// Añade un plato al array del tipo

	public void deleteConsumicion(Consumicion consumicion) {
		for (MenuObserver obs : observers)
			obs.deleteConsumption(consumicion.getNombre(),
					tipoPlato(consumicion.getNombre()).toString());
		dondeEsta(consumicion).remove(consumicion);

	}

	/* Devuelve el array list donde está el elemento buscado */
	private ArrayList<Consumicion> dondeEsta(Consumicion consumicion) {
		for (Consumicion c : bebidas)
			if (c.getNombre().equals(consumicion.getNombre()))
				return bebidas;
		for (Consumicion c : primeros)
			if (c.getNombre().equals(consumicion.getNombre()))
				return primeros;
		for (Consumicion c : segundos)
			if (c.getNombre().equals(consumicion.getNombre()))
				return segundos;
		for (Consumicion c : postres)
			if (c.getNombre().equals(consumicion.getNombre()))
				return postres;
		return null;
	}

	/** Devuelve el enumerado al que pertenece el elemento buscado */
	public TipoPlatos tipoPlato(String name) {
		for (Consumicion c : bebidas)
			if (c.getNombre().equals(name))
				return TipoPlatos.BEBIDA;
		for (Consumicion c : primeros)
			if (c.getNombre().equals(name))
				return TipoPlatos.PRIMERO;
		for (Consumicion c : segundos)
			if (c.getNombre().equals(name))
				return TipoPlatos.SEGUNDO;
		for (Consumicion c : postres)
			if (c.getNombre().equals(name))
				return TipoPlatos.POSTRE;
		return null;
	}

	// Actualiza un plato al array del tipo

	public void actualizaConsumicion(Consumicion consAntigua,
		Consumicion consNueva, TipoPlatos tPlato) {
		deleteConsumicion(consAntigua);
		addConsumicion(consNueva, tPlato);
	}

	// Añade un plato al array del tipo

	public void addConsumicion(Consumicion consumicion, TipoPlatos tPlato) {
		if (dondeEsta(consumicion) == null) {
			switch (tPlato) {
			case BEBIDA:
				bebidas.add(consumicion);
				break;
			case PRIMERO:
				primeros.add(consumicion);
				break;
			case SEGUNDO:
				segundos.add(consumicion);
				break;
			case POSTRE:
				postres.add(consumicion);
				break;
			default:
				;
			}
			for (MenuObserver obs : observers)
				obs.addConsumption(consumicion.getNombre(), tPlato.toString());
		}
	}

	public ArrayList<Consumicion> getBebidas() {
		return bebidas;
	}

	public ArrayList<Consumicion> getPrimeros() {
		return primeros;
	}

	public ArrayList<Consumicion> getSegundos() {
		return segundos;
	}

	public ArrayList<Consumicion> getPostres() {
		return postres;
	}

	private String stringConsumiciones(ArrayList<Consumicion> listaConsumiciones) {
		String str = "";
		for (Consumicion consumicion : listaConsumiciones)
			str += consumicion.toString();
		return str;
	}

	public String toString() {
		String menu = "";
		menu += "Menu:" + LINE_SEPARATOR + "Primeros:" + LINE_SEPARATOR;
		menu += stringConsumiciones(primeros);
		menu += LINE_SEPARATOR + "Segundos:" + LINE_SEPARATOR;
		menu += stringConsumiciones(segundos);
		menu += LINE_SEPARATOR + "Bebidas:" + LINE_SEPARATOR;
		menu += stringConsumiciones(bebidas);
		menu += LINE_SEPARATOR + "Postres" + LINE_SEPARATOR;
		menu += stringConsumiciones(postres);
		return menu;
	}

	public boolean saveMenu(String fileName) {
		try {
			FileWriter fichero = new FileWriter(fileName + ".txt");
			PrintWriter printer = new PrintWriter(fichero);
			String menu = "";
			menu += "BeginMenu" + LINE_SEPARATOR + "BeginPrimeros"
					+ LINE_SEPARATOR;
			menu += stringConsumiciones(primeros);
			menu += "EndPrimeros" + LINE_SEPARATOR + "BeginSegundos"
					+ LINE_SEPARATOR;
			menu += stringConsumiciones(segundos);
			menu += "EndSegundos" + LINE_SEPARATOR + "BeginBebidas"
					+ LINE_SEPARATOR;
			menu += stringConsumiciones(bebidas);
			menu += "EndBebidas" + LINE_SEPARATOR + "BeginPostres"
					+ LINE_SEPARATOR;
			menu += stringConsumiciones(postres);
			menu += "EndPostres" + LINE_SEPARATOR + "EndMenu" + LINE_SEPARATOR;
			printer.println(menu);
			fichero.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Consumicion getConsumicion(String name) {
		for (Consumicion c : bebidas)
			if (c.getNombre().equals(name))
				return c;
		for (Consumicion c : primeros)
			if (c.getNombre().equals(name))
				return c;
		for (Consumicion c : segundos)
			if (c.getNombre().equals(name))
				return c;
		for (Consumicion c : postres)
			if (c.getNombre().equals(name))
				return c;
		return null;
	}

	public void addObserver(MenuObserver verMenu) {
		observers.add(verMenu);
	}

	static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private ArrayList<MenuObserver> observers;
	private ArrayList<Consumicion> bebidas;
	private ArrayList<Consumicion> primeros;
	private ArrayList<Consumicion> segundos;
	private ArrayList<Consumicion> postres;

}
