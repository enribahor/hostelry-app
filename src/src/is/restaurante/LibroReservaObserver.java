package is.restaurante;

import java.util.ArrayList;

public interface LibroReservaObserver {
	
	void cambioOcurrido(ArrayList<ReservaInfo> reservaHoy, ArrayList<ReservaInfo> reservaSemana, ArrayList<ReservaInfo> reservasSiempre);
	
}
