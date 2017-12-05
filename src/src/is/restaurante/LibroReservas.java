package is.restaurante;

import java.util.ArrayList;
import java.util.Date;
import is.Fecha;
import is.List;

/**
 * Clase que guarda todas las reservas
 * 
 * @author JaimeDan
 * 
 */
public class LibroReservas{
	/**
	 * Constructor
	 */
	public LibroReservas() {
		observers = new ArrayList<LibroReservaObserver>();
		listaReservas = new List<Reserva>();
	}

	public LibroReservas(List<Reserva> list) {
		listaReservas = list;
		observers = new ArrayList<LibroReservaObserver>();
	}

	public boolean addReserva(Reserva res) {
		boolean exito = listaReservas.add(res); 
		if (exito) emitirCambios();
		return exito;

	}

	public ArrayList<ReservaInfo> reservasHoy() {
		Date date = new Date();
		Fecha fecha = new Fecha(0, 0, 0, 0, 0);
		fecha.parse(date.toString(), 0, 0);
		ArrayList<ReservaInfo> reservasDeHoy = new ArrayList<ReservaInfo>();
		reservasDeHoy = buscarReservaHoy(fecha);
		return reservasDeHoy;

	}

	public ArrayList<ReservaInfo> reservasSemana() {
		Date date = new Date();

		Fecha fecha = new Fecha(0, 0, 0, 0, 0); // new Date (hoy) o algo asi
		fecha.parse(date.toString(), 0, 0);
		ArrayList<ReservaInfo> reservasSemana = new ArrayList<ReservaInfo>();
		reservasSemana = buscarReservaSemana(fecha);
		return reservasSemana;

	}

	public ArrayList<ReservaInfo> reservasMes() {
		Date date = new Date();
		Fecha fecha = new Fecha(0, 0, 0, 0, 0); // new Date (hoy) o algo asi
		fecha.parse(date.toString(), 0, 0);
		ArrayList<ReservaInfo> reservasMes = new ArrayList<ReservaInfo>();
		reservasMes = buscarReservaMes(fecha);
		return reservasMes;

	}
	
	public ArrayList <ReservaInfo> reservasDesdeHoy(){
		Date date = new Date();

		Fecha fecha = new Fecha(0, 0, 0, 0, 0); // new Date (hoy) o algo asi
		fecha.parse(date.toString(), 0, 0);
		ArrayList<ReservaInfo> reservasSiempre = new ArrayList<ReservaInfo>();
		for (int i=0; i<listaReservas.size(); i++){
			if (listaReservas.get(i).getFecha().esMayorQue(fecha) || listaReservas.get(i).getFecha().mismoDia(fecha)){
				reservasSiempre.add(listaReservas.get(i));
			}
		}
		return reservasSiempre;
	}

	public boolean eliminarReserva(Reserva res) {
		boolean exito = listaReservas.erase(res);
		emitirCambios();
		return exito;
	}
	
	private ReservaInfo[] listaReservasToArray() {
		ReservaInfo[] reservaArray = new ReservaInfo[listaReservas.size()];
		for (int i = 0; i < listaReservas.size(); i++) {
			reservaArray[i] = new Reserva();
			reservaArray[i] = listaReservas.get(i);
		}
		return reservaArray;
	}

	/**
	 * Por que coño ahora esto es con integers? De donde sacas entonces la
	 * reserva para poder mostrarla en verReserva??
	 */
	public ArrayList<ReservaInfo> buscarReserva(String nombre) {
		ArrayList<ReservaInfo> listaRes = new ArrayList<ReservaInfo>();
		int i = 0;
		while (i < listaReservas.size()) {
			if (listaReservas.get(i).getNombre().equals(nombre))
				;
			listaRes.add(listaReservas.get(i));
		}
		return listaRes;
	}

	public ArrayList<ReservaInfo> todasReservas() {
		ArrayList<ReservaInfo> listaRes = new ArrayList<ReservaInfo>();
		for (int i = 0; i < this.listaReservas.size(); i++)
			listaRes.add(this.listaReservas.get(i));
		return listaRes;
	}

	public ArrayList<ReservaInfo> buscarReserva(int j) {
		if (j == -1)
			return this.reservasDesdeHoy();//todasReservas();
		if (j == 0)
			return reservasHoy();
		else if (j == 1)
			return reservasSemana();
		else if (j == 2)
			return reservasMes();
		return null;
	}
	


	public ArrayList<ReservaInfo> buscarReservaHoy(Fecha date) {
		ArrayList<ReservaInfo> reservasHoy = new ArrayList<ReservaInfo>();
		for (int i = 0; i < listaReservas.size(); i++) {
			if (listaReservas.get(i).getFecha().mismoDia(date))
				reservasHoy.add(listaReservas.get(i));
		}
		return reservasHoy;
	}

	public ArrayList<ReservaInfo> buscarReservaSemana(Fecha dateInicial) {
		ArrayList<ReservaInfo> reservasSemana = new ArrayList<ReservaInfo>();
		Fecha dateTope = dateInicial.siguienteSemana();
		for (int i = 0; i < listaReservas.size(); i++) {
			if (!listaReservas.get(i).getFecha().esMayorQue(dateTope)
					&& (listaReservas.get(i).getFecha().esMayorQue(dateInicial) || listaReservas
							.get(i).getFecha().mismoDia(dateInicial)))
				reservasSemana.add(listaReservas.get(i));
		}
		return reservasSemana;
	}

	public ArrayList<ReservaInfo> buscarReservaMes(Fecha dateInicial) {
		ArrayList<ReservaInfo> reservasSemana = new ArrayList<ReservaInfo>();
		Fecha dateTope = dateInicial.siguienteMes();
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < listaReservas.size(); i++) {
				if (!listaReservas.get(i).getFecha().esMayorQue(dateTope)
						&& (listaReservas.get(i).getFecha()
								.esMayorQue(dateInicial) || listaReservas
								.get(i).getFecha().equals(dateInicial)))
					reservasSemana.add(listaReservas.get(i));
				dateInicial = dateInicial.siguienteSemana();
				dateTope = dateTope.siguienteSemana();

			}
		}
		return reservasSemana;
	}

	public void addObserver(LibroReservaObserver obs) {
		this.observers.add(obs);
	}

	public void deleteReserva(String nombre, String fecha, String comensales,
			String mesa) {
		for (int i=0; i<listaReservas.size(); i++){
			if (listaReservas.get(i).esMismaReserva(nombre, fecha, comensales, mesa)){
				listaReservas.erase(i);
				emitirCambios();
				break;
			}
				
		}

	}
	
	private void emitirCambios(){
		for (LibroReservaObserver obs: observers)
			obs.cambioOcurrido(this.reservasHoy(), this.reservasSemana(), this.reservasDesdeHoy());
	}

	private List<Reserva> listaReservas;
	private ArrayList<LibroReservaObserver> observers;
	public ArrayList<ReservaInfo> buscarReserva(Fecha fecha) {
		// TODO Auto-generated method stub
		return null;
	}


}