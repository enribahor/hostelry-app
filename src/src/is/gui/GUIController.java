package is.gui;

import java.util.ArrayList;
import is.Fecha;
import is.restaurante.ComandaObserver;
import is.restaurante.LibroReservaObserver;
import is.restaurante.Menu;
import is.restaurante.MenuObserver;
import is.restaurante.Mesa;
import is.restaurante.MesaInfo;
import is.restaurante.MesaObserver;
import is.restaurante.Reserva;
import is.restaurante.ReservaInfo;
import is.restaurante.Restaurante;
import is.restaurante.TipoPlatos;
import is.restaurante.consumicion.Consumicion;



/**
 * Clase que se encarga de llevar los avisos recibidos en la vista al modelo
 * @author JaimeDan
 */
public class GUIController {

	
	public void crearComanda(){
	} 
	
	public GUIController(Restaurante restaurante){
		this.restaurante = restaurante;
	}
	public  Menu getMenu(){
		return restaurante.getMenu();
	}
	public  ArrayList<Consumicion> requestPrimeros(){
		return restaurante.getMenu(1);
		
	}
	public  ArrayList<Consumicion> requestSegundos(){
		return restaurante.getMenu(2);
		
	}
	public  ArrayList<Consumicion> requestPostres(){
		return restaurante.getMenu(3);
		
	}
	public  ArrayList<Consumicion> requestBebidas(){
		return restaurante.getMenu(0);
		
	}
	public ArrayList<ReservaInfo> getReservas(int i){
		return restaurante.buscarReservas(i);
		
	}
	public void communicateReserva(String cad, int hora, int minutos, String cliente, int numeroComensales, boolean[] mesasSel) {
		Fecha fRes = new Fecha(0,0,0,0,0);
		fRes.parse(cad, hora, minutos);
		ArrayList<Mesa> mesas = new ArrayList<Mesa>();
		for (int i = 0; i < mesasSel.length; i++)
			if (mesasSel[i]) mesas.add(this.getMesa(i));			
		Reserva reserva = new Reserva(fRes, cliente, numeroComensales, mesas);
		restaurante.añadirReserva(reserva);
	}
	
	public void communicateReserva(String uneditedDate, String cliente, int numeroComensales,
			boolean[] mesasSel) {
		Fecha fRes = new Fecha(0,0,0,0,0);
		fRes.parseDayFirst(uneditedDate);
		ArrayList<Mesa> mesas = new ArrayList<Mesa>();
		for (int i=0; i<mesasSel.length; i++)
			if (mesasSel[i]) mesas.add(this.getMesa(i));
		Reserva reserva = new Reserva(fRes, cliente, numeroComensales, mesas);
		restaurante.añadirReserva(reserva);
	}

	public void comunicarComanda(int año, int mes, int dia, int hora, 

			int minutos, String cliente, int numeroComensales) {
		Fecha fechaRes = new Fecha(año, mes, dia, hora, minutos);
		Reserva reserva = new Reserva(fechaRes, cliente, numeroComensales, null);
		restaurante.añadirReserva(reserva);
	}
	
	public MesaInfo[] requestMesas() {
		return this.restaurante.getMesas();
	}
	
	public ArrayList<ReservaInfo> reservasDeUnCliente(String nombre){
		return this.restaurante.buscarReservas(nombre);
	}
	
	public Mesa getMesa(int j) {
		return restaurante.buscarMesa(j);
		
	}
	public Mesa getMesa(String j) {
		int i = Integer.parseInt(j);
		return restaurante.buscarMesa(i);
		
	}
	

	public void avisaComanda(int numMesa, int comandaSelected) {
		this.restaurante.avisaComanda(numMesa, comandaSelected);
	}
	
	public void eliminarComanda(int numMesa, int comandaSelected) {
		this.restaurante.eliminaComanda(numMesa, comandaSelected);
	}

	public void requestNewConsumption(int numComanda, int numMesa, Consumicion plato, TipoPlatos tPlato) {
		this.restaurante.addNewConsumption(numComanda, numMesa, plato, tPlato);
	}
	
	public void requestNewConsumption(int numComanda, int numMesa,
			Consumicion plato, TipoPlatos tPlato, int veces) {
		this.restaurante.addNewConsumption(numComanda, numMesa, plato, tPlato, veces);
	}

	
	public void actualizaConsumicion(Consumicion consAntigua, Consumicion consNueva,TipoPlatos tPlato){
		restaurante.actualizaConsumicion(consAntigua, consNueva, tPlato);
	}
	public void deleteConsumicion(Consumicion consumicion){
		restaurante.deleteConsumicion(consumicion);
	}
	public void addConsumicion(Consumicion consumicion, TipoPlatos tPlato){
		restaurante.addConsumicion( consumicion, tPlato);
	}

	public void requestNewCommand(int numMesa) {
		this.restaurante.addNewCommand(numMesa);
	}

	public void addComandaObserver(ComandaObserver observer, int numeroMesa, int comandaSeleccionada) {
		restaurante.addComandaObserver(observer, numeroMesa, comandaSeleccionada);
	}

	public void registerMenuObserver(MenuObserver verMenu) {
		restaurante.registerMenuObserver(verMenu);
	}

	public void addLibroReservaObserver(LibroReservaObserver verReserva) {
		restaurante.registerLibroReservaObserver(verReserva);
	}

	public void addMesaObserver(MesaObserver generarFactura, int j) {
		this.restaurante.registerMesaObserver(generarFactura, j);
	}

	public void removeMesaObserver(MesaObserver generarFactura, int numMesa) {
		this.restaurante.deleteMesaObserver(generarFactura, numMesa);
	}

	public void requestRemoveReserva(String nombre, String fecha,
			String comensales, String mesa) {
		this.restaurante.deleteReserva(nombre, fecha, comensales, mesa);
	}

	private Restaurante restaurante;

	public void addMesaObserver(MesaObserver mesa) {

	}

	public void eliminaUltimaComanda(int numeroMesa) {
		restaurante.deleteComanda(numeroMesa);
		
	}











}
