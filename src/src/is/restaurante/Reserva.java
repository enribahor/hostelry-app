package is.restaurante;

import is.Fecha;
import java.util.ArrayList;

/**
 * Contiene la información relativa a la reserva, que se rellena en la clase
 * RealizarReserva, esto es: Fecha, nombre, número de comensales y la lista de
 * las mesas reservadas. Permite devolver un string con toda esta información
 * mediante el método toString().
 * 
 * @author Jesús
 */

public class Reserva implements Comparable<Reserva>, ReservaInfo {
	public Reserva() {
		nombre = "";
		numeroDeComensales = -1;
		fecha = new Fecha();
		mesas = new ArrayList<Mesa>();
	}

	/**
	 * 
	 * @param date
	 * @param hour
	 * @param min
	 * @param name
	 * @param number
	 * @param listaMesas
	 */
	public Reserva(Fecha date, String name, int numComensales,
			ArrayList<Mesa> listaMesas) {
		this.fecha = date;
		this.nombre = name;
		this.numeroDeComensales = numComensales;
		this.mesas = listaMesas;

	}

	/**
	 * 
	 * @return nombre del cliente que reserva
	 */
	public String getNombre() {
		return this.nombre;
	}

	public Fecha getFecha() {
		return this.fecha;
	}

	@Override
	public int compareTo(Reserva o) {
		if (o != null) {
			if (this.fecha.esMayorQue(o.fecha))
				return 1;
			else if (this.fecha.equals(o.fecha))
				return 0;
			else
				return -1;
		}
		return -1;
	}

	public String toString() {
		String name = "";
		name += "Nombre: " + this.nombre + LINE_SEPARATOR + "Fecha: "
				+ this.fecha.toString() + LINE_SEPARATOR;
		if (mesas != null) {
			name += "Mesas: ";
			for (Mesa m : this.mesas)
				name += m.getNumeroMesa() + " ";
			name += LINE_SEPARATOR;
		}
		name += "Numero de comensales: " + this.numeroDeComensales
				+ LINE_SEPARATOR;
		return name;
	}
	
	@Override
	public String fechaToString() {
		return this.fecha.toString();
	}

	@Override
	public String nombreToString() {
		return this.nombre;
	}

	@Override
	public String mesasToString() {
		String mesas = "";
		for (Mesa m : this.mesas)
			mesas += m.getNumeroMesa() + " ";
		return mesas;
	}

	@Override
	public String comensalesToString() {
		return this.numeroDeComensales.toString();
	}

	public boolean esMismaReserva(String nombre2, String fecha2,
			String comensales, String mesa) {
		return (this.nombreToString().equals(nombre2) && 
				this.fechaToString().equals(fecha2.toString()) && 
				this.comensalesToString().equals(comensales) && 
				this.mesasToString().equals(mesa));
				
	}

	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private ArrayList<Mesa> mesas;
	private Fecha fecha;
	private String nombre;
	private Integer numeroDeComensales;
	

}
