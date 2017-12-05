package is.restaurante.consumicion;

public class Bebida implements Consumicion {

	/**
	 * Gestiona una bebida, un tipo de consumición
	 * 
	 * @author JaimeDan
	 */

	public Bebida(String name, float price, boolean disp, String desc) {
		this.descripcion = desc;
		this.precio = price;
		this.nombre = name;
		this.disponible = disp;
	}

	public float getPrecio() {
		return this.precio;
	}

	public String getNombre() {

		return this.nombre;
	}

	public String getDescripcion() {

		return this.descripcion;
	}

	public boolean isDisponible() {

		return this.disponible;
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
