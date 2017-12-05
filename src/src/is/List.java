package is;

/**
 * @author Villarín
 * Clase que contiene un vector de objectos de la misma clase. Contiene los
 * metodos para añadir, obtener y eliminar objetos del vector. Esta implementado
 * con busqueda binaria. Tambien permite ampliarse cuando el tamaño es
 * insufuciente para contener mas objetos.
 * 
 * @param <Tipo>
 *            - indica a que clase pertenece el objecto que recibe
 */
@SuppressWarnings("unchecked")
public class List<Tipo extends Comparable<Tipo>> {
	/**
	 * Constructor de la clase List. Crea un array vacio de objetos e inicializa
	 * su contador a cero.
	 */
	public List() {
		list = new Object[MIN_VALUE];
		size = 0;
	}
	
	/**
	 * 
	 * @return tamaño del vector
	 */
	public int size(){
		return this.size;
	}

	/**
	 * Obtiene un objeto de la lista que se busca a partir de otro objeto. Se
	 * supone que el objeto que nos proporcionan no tiene todos los datos que el
	 * objeto que esta en el array.
	 * 
	 * @param object
	 *            - Objeto que se busca
	 * @return el objeto que se buscaba si se encuentra en la lista o false en
	 *         caso contrario.
	 */
	public Tipo get(Tipo object) {
		if (object != null) {
			int pos = binary(object, 0, size - 1);
			if (object.compareTo((Tipo) list[pos]) == 0)
				return (Tipo) list[pos];
		}
		return null;
	}
	
	public Tipo get(int i){
		if (i >= this.size())
			return null;
		else return (Tipo) list[i];
	}

	/**
	 * Agrega ordenadamente un objeto a la lista.
	 * 
	 * @param object
	 *            - Objeto que se desea añadir al array
	 * @return true si se ha completado correctamente, false en caso contrario.
	 */
	public boolean add(Tipo object) {
		if (object == null)
			return false;
		int pos = binary(object, 0, size - 1);
		//if (object.compareTo((Tipo) list[pos]) == 0)
		//	return false;
		//else {
			if (size == list.length)
				biggerList();
			for (int i = size; i > pos; i--)
				list[i] = list[i - 1];
			list[pos] = object;
			size++;
			return true;
		//}
	}

	/**
	 * Borra un objeto de la lista y reordena la lista de modo que no quede
	 * ningun hueco intermedio.
	 * 
	 * @param object
	 *            - Objeto que se desea borrar
	 * @return true si se ha borrado correctamente, false en caso contrario.
	 */
	public boolean erase(Tipo object) {
		if (object != null) {
			int pos = binary(object, 0, size - 1);
			if (object.compareTo((Tipo) list[pos]) == 0) {
				list[pos] = null;
				size--;
				for (int i = pos; i < size; i++)
					list[i] = list[i + 1];
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Borra un objeto de la lista de indice n
	 */
	public boolean erase (int n){
		if (n >= 0 && n< size){
			list[n] = null;
			for (int i=n; i< size-1; i++)
				list[i] = list[i+1];
			size--;
			return true;
		}else return false;
	}

	/**
	 * Dobla el tamaño de la lista para que quepan mas objetos.
	 */
	public void biggerList() {
		Object[] object = new Object[2 * size];
		for (int i = 0; i < size; i++)
			object[i] = list[i];
		list = object;
	}

	/**
	 * Busqueda binaria. Busca de forma binaria la posicion de un objeto en la
	 * lista usando el metodo compareTo. Es necesario implementar compareTo para
	 * cada clase sobre la que se desee usar esta busqueda.
	 * 
	 * @param object
	 *            - Objeto que se busca
	 * @param first
	 *            - Primera posicion del vector en el que se busca
	 * @param last
	 *            - Ultima posicion del vector en el que se busca
	 * @return La posicion que ocupa el objeto, o la que deberia ocupar en caso
	 *         de no encontrarse.
	 */
	public int binary(Tipo object, int first, int last) {
		if (first > last)
			return first;
		int pos = (first + last) / 2;
		if (object.compareTo((Tipo) list[pos]) < 0)
			return binary(object, first, pos - 1);
		if (object.compareTo((Tipo) list[pos]) > 0)
			return binary(object, pos + 1, last);
		return pos;
	}

	protected Object[] list;
	protected int size;
	protected static final int MIN_VALUE = 4;
}
