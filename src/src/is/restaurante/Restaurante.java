package is.restaurante;

import is.restaurante.consumicion.Consumicion;

import java.util.ArrayList;

/**
 * Clase principal del proyecto, contiene la lista de mesas, el menú y el libro
 * de reservas. Permite añadir y buscar reservas en el libro de reservas, así
 * como gestionar consumiciones.
 * 
 * @author Jesús
 * @author JaimeDan
 * @author Kike
 */
public class Restaurante {

	public Restaurante() {
		this.mesas = new ArrayList<Mesa>(5);
		for (int i = 0; i < 5; i++)
			mesas.add(new Mesa(i));
		this.reservas = new LibroReservas();
		this.menu = new Menu();
	}

	public Restaurante(is.List<Reserva> list, Menu m, ArrayList<Mesa> mesasRes) {
		mesas = mesasRes;
		reservas = new LibroReservas(list);
		menu = m;
	}

	public void añadirReserva(Reserva reserva) {
		reservas.addReserva(reserva);
	}

	public ArrayList<ReservaInfo> buscarReservas(
			String nombre) {
		return this.reservas.buscarReserva(nombre);
	}

	public ArrayList<ReservaInfo> buscarReservas(int i) {
		return this.reservas.buscarReserva(i);
	}

	public Menu getMenu() {
		return menu;
	}

	public ArrayList<Consumicion> getMenu(int n) {
		if (n == 0)
			return menu.getBebidas();
		else if (n == 1)
			return menu.getPrimeros();
		else if (n == 2)
			return menu.getSegundos();
		else if (n == 3)
			return menu.getPostres();
		else
			return null;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Mesa buscarMesa(int i) {
		for (Mesa m : mesas)
			if (m.getNumeroMesa() == i)
				return m;
		return null;
	}

	public void avisaComanda(int numMesa, int comandaSelected) {
		mesas.get(numMesa).avisaComanda(comandaSelected);
	}

	public void eliminaComanda(int numMesa, int comandaSelected) {
		mesas.get(numMesa).eliminaComanda(comandaSelected);
	}

	public void addNewConsumption(int numComanda, int numMesa,
			Consumicion plato, TipoPlatos tPlato) {
		mesas.get(numMesa).addConsumption(numComanda, plato, tPlato);
	}

	public void addNewConsumption(int numComanda, int numMesa,
			Consumicion plato, TipoPlatos tPlato, int veces) {
		mesas.get(numMesa).addConsumption(numComanda, plato, tPlato, veces);
	}

	public MesaInfo[] getMesas() {
		MesaInfo[] infoMesas = new MesaInfo[this.mesas.size()];
		for (int i = 0; i < this.mesas.size(); i++)
			infoMesas[i] = this.mesas.get(i);
		return infoMesas;
	}

	public void actualizaConsumicion(Consumicion consAntigua,
			Consumicion consNueva, TipoPlatos tPlato) {
		menu.actualizaConsumicion(consAntigua, consNueva, tPlato);
	}

	public void deleteConsumicion(Consumicion consumicion) {
		menu.deleteConsumicion(consumicion);
	}

	public void addConsumicion(Consumicion consumicion, TipoPlatos tPlato) {
		menu.addConsumicion(consumicion, tPlato);
	}

	public void addNewCommand(int numMesa) {
		this.mesas.get(numMesa).addNewComanda();
	}

	public void registerMenuObserver(MenuObserver verMenu) {
		menu.addObserver(verMenu);
	}

	public void addComandaObserver(ComandaObserver observer, int numeroMesa,
			int comandaSeleccionada) {
		mesas.get(numeroMesa).addComandaObserver(observer, comandaSeleccionada);
	}

	public void registerLibroReservaObserver(
			LibroReservaObserver libroReservaObserver) {
		reservas.addObserver(libroReservaObserver);
	}

	public void registerMesaObserver(MesaObserver obs, int j) {
		mesas.get(j).addMesaObserver(obs);
	}

	public void deleteMesaObserver(MesaObserver generarFactura, int numMesa) {
		mesas.get(numMesa).removeMesaObserver(generarFactura);
	}
	public void deleteComanda(int numeroMesa) {
		mesas.get(numeroMesa).eliminaUltimaComanda();
		
	}
	public void deleteReserva(String nombre, String fecha, String comensales,
			String mesa) {
		reservas.deleteReserva( nombre,  fecha,  comensales, mesa);
	}

	private ArrayList<Mesa> mesas;
	private LibroReservas reservas;
	private Menu menu;


}