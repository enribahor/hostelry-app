package is.gui;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import is.restaurante.consumicion.Consumicion;
import is.restaurante.ComandaObserver;
import is.restaurante.TipoPlatos;
/**
 * Celda que tiene un plato, y un JSpinner con el número de veces que ha sido elegido.
 * Posteriormente se mostrará por pantalla al elegir el menú
 * @author JaimeDan
 */
@SuppressWarnings("serial")
public class CeldaPlato extends JPanel implements ComandaObserver{

	public CeldaPlato(GUIController controller, final Consumicion plato, final int numMesa, final int numComanda, final TipoPlatos tPlato){
		this.restControl = controller;
		this.veces = 0;
		this.botonPlato = new JButton(plato.getNombre()){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						incrementaVeces();
					}
					
				});
			}
		};
		this.flechitas = new JSpinner();
		JFormattedTextField ftf = getTextField(flechitas);
	        if (ftf != null ) 
	            ftf.setColumns(2); //cambia el tamaño del spinner
	        
		this.flechitas.setValue(0);
		this.flechitas.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				veces = (int) flechitas.getValue();
				if (veces < 0){
					flechitas.setValue(0);
					veces = 0;
				}
				restControl.requestNewConsumption(numComanda, numMesa, plato, tPlato, veces);
				
			}
			
		});
		this.setLayout(new FlowLayout());
		this.add(botonPlato);
		this.add(flechitas);
		
	}
	
	@Override
	public void comandaHaCambiado(String comanda) {
		
	}

	@Override
	public void setComandaNumber(int n) {
//		this.numComanda = n;
	}
	
	public void incrementaVeces(){
		this.veces++;
		this.flechitas.setValue(this.veces);
	}
	
	public int getVeces(){
		return this.veces;
	}
	
	public JFormattedTextField getTextField(JSpinner spinner) {
	        JComponent editor = spinner.getEditor();
	        if (editor instanceof JSpinner.DefaultEditor) {
	            return ((JSpinner.DefaultEditor)editor).getTextField();
	        } else {
	            System.err.println("Unexpected editor type: "
	                               + spinner.getEditor().getClass()
	                               + " isn't a descendant of DefaultEditor");
	            return null;
	        }
	    }
	 
	public void setValuesAtZero(){
		this.veces = 0;
	}
	
	private GUIController restControl;
	private int veces;
	private JButton botonPlato;
	private JSpinner flechitas;
	
	@Override
	public void comandaError(String error) {
		// TODO Auto-generated method stub
		
	}
	
}
