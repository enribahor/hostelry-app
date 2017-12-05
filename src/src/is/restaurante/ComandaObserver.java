package is.restaurante;

public interface ComandaObserver {

	public void comandaHaCambiado(String comanda);

	public void setComandaNumber(int n);
		
	public void comandaError(String error);

}
