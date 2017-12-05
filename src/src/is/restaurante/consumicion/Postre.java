package is.restaurante.consumicion;

/**
 * Gestiona una postre, un tipo de consumición
 * 
 * @author Jesús
 */
public class Postre implements Consumicion {

	public Postre(String name, float price, boolean disp, String desc) {
		this.descripción = desc;
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
		return this.descripción;
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

	private String descripción;
	private String nombre;
	private float precio;
	private boolean disponible;

}
