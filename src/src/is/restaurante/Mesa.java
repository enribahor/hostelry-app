package is.restaurante;

import is.restaurante.consumicion.Consumicion;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Contiene la información relativa a la mesa, con la lista de comandas y el
 * número de identificación de la mesa. Permite añadir comandas y generar la
 * factura correspondiente a la mesa.
 * 
 * @author Villarin
 */
public class Mesa implements MesaInfo {

	 public Mesa(int num){
		 this.obs = new ArrayList<MesaObserver>();
         this.comandas = new Vector<Comanda>();
         this.setNumeroMesa(num);
 }
	public Mesa(int num, int cap) {
		numeroMesa = num;
		this.obs = new ArrayList<MesaObserver>();
         this.comandas = new Vector<Comanda>();
	}

	public void addPedido(Comanda comanda) {
		comandas.add(comanda);
	}

	@Override
	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public void setCapacidad(int c) {
	}

	public void generarFactura() {

		String factura = Factura.generarFactura(comandas);
		for (MesaObserver m :obs) 
			m.facturaGenerada(factura);
		comandas.removeAllElements();
	}

	@Override
	public ComandaInfo[] getListaComandas() {
		if (this.comandas != null)
			return this.listaComandasToArray();
		else
			return null;
	}


    private ComandaInfo[] listaComandasToArray(){
            ComandaInfo[] comandaArray = new ComandaInfo[comandas.size()];
            for (int i=0; i<comandas.size(); i++){
                    comandaArray[i] = new Comanda();
                    comandaArray[i] = comandas.get(i);
            }
            return comandaArray;
    }
	/**
	 * Escoge una comanda como actual, para realizar acciones sobre ella
	 * 
	 * @param comandaSelected
	 */
	public void avisaComanda(int comandaSelected) {
		if (comandaActual != -1)
			comandaActual = comandaSelected;
	}

	public void eliminaComanda(int comandaSelected) {
		if (!comandas.isEmpty()) comandas.remove(comandaSelected);
		for (int i=0; i<comandas.size(); i++){
			comandas.get(i).setNumComanda(i);
		}
		emitirCambios();
	}

	public void addConsumption(int numComanda, Consumicion plato,
			TipoPlatos tPlato) {
		switch (tPlato) {
		case PRIMERO:
			comandas.get(numComanda).addPrimero(plato);
		case SEGUNDO:
			comandas.get(numComanda).addSegundo(plato);
		case POSTRE:
			comandas.get(numComanda).addPostre(plato);
		case BEBIDA:
			comandas.get(numComanda).addBebida(plato);
		}
	}
	
	public void addConsumption(int numComanda, Consumicion plato,
			TipoPlatos tPlato, int veces) {

		comandas.get(numComanda).setConsumicionTimes(plato, veces, tPlato);
	}


	
	private void emitirCambios() {
		for (MesaObserver o : obs)
			o.cambioOcurrido(this.listaComandasToArray(), this.numeroMesa);
	}

	public void addNewComanda() {
		comandas.add(new Comanda());
		emitirCambios();
	}

	public void addComandaObserver(ComandaObserver observer, int comandaSeleccionada) {
		comandas.get(comandaSeleccionada);
		comandas.get(comandaSeleccionada).addObserver(observer);
	}

	public void addMesaObserver(MesaObserver obs2) {
		obs.add(obs2);
	}

	public void removeMesaObserver(MesaObserver generarFactura) {
		obs.remove(generarFactura);
	}
	public void eliminaUltimaComanda() {
		comandas.remove(comandas.lastElement());	
	}
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

	
	private ArrayList<MesaObserver> obs;
	private Vector<Comanda> comandas;
	private int numeroMesa;
	private int comandaActual = -1;
	



}
