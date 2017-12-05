package is.restaurante.consumicion;

/**
 * Gestiona un plato, un tipo de consumición
 * 
 * @author JaimeDan
 * @author Kike
 */

public class Plato implements Consumicion {

	public Plato() {
		nombre = "";
		precio = -1;
		disponible = false;
		descripcion = "";
	}

	/**
	 * Constructor de un plato
	 * 
	 * @param descripcion
	 *            : descripcion del plato
	 * @param nombre
	 *            : nombre del plato
	 * @param precio
	 *            : precio del plato
	 */
	public Plato(String nombre, float precio, boolean disp, String descripcion) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.precio = precio;
		this.disponible = disp;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @return precio del plato
	 */

	public float getPrecio() {
		return precio;
	}

	/**
	 * comprueba si el plato está disponible
	 * 
	 * @return disponible si el plato está disponible
	 */

	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * marca la disponibilidad de un plato
	 * 
	 * @param
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * Precio que queremos que tenga el plato
	 * 
	 * @param precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String toString() {
		String str = "";
		str += "\"" + nombre + "\" \"" + descripcion + "\" " + precio + " ";
		if (disponible)
			str += "Disponible";
		else
			str += "NoDisponible";
		str += LINE_SEPARATOR;
		return str;
	}

	static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private String descripcion;
	private String nombre;
	private float precio;
	private boolean disponible;

}
