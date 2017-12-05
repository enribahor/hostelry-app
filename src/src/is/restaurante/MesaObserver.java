package is.restaurante;

public interface MesaObserver {

	public void facturaGenerada(String factura);
	
	public void cambioOcurrido(ComandaInfo[] comandas, int numeroMesa);
}
