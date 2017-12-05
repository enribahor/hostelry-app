package is.gui;

import is.gui.spring.SpringUtilities;
import is.restaurante.TipoPlatos;
import is.restaurante.consumicion.Bebida;
import is.restaurante.consumicion.Consumicion;
import is.restaurante.consumicion.Plato;
import is.restaurante.consumicion.Postre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
/**
 * Muestra en swing una consumición
 * Aparecen por tanto el precion, nombre, descripción y si está disponible o no.
 * Asimismo, permite editar estos campos al correr la aplicación, y crear nuevos platos añadiéndolos al menú.
 * @author Kike
 */
@SuppressWarnings("serial")
public class ConsumicionSwing extends JPanel{

	public ConsumicionSwing(VerMenu ventana, GUIController controller, Consumicion consumicion, boolean edit){
		
		this.edit = edit;
		this.campos = new JPanel();
		this.setLayout(new BorderLayout());
		
		anyadirControles();
		this.campos.setLayout(new SpringLayout());
		consumicionInicial = consumicion;
		this.ventanaGeneral = ventana;
		this.restControl = controller;
		
		/*********Campo nombre***********************/
		JLabel nombreL = new JLabel(" Nombre ");
		nombreL.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.campos.add(nombreL);
		if(edit)
			nombrePlato = new JTextField(consumicion.getNombre());
		else
			nombrePlato = new JTextField();

		nombrePlato.setMaximumSize(new Dimension(1, 1));
		this.campos.add(nombrePlato);
		
		/**********Campo descripcion******************/
		JLabel descripcionL = new JLabel(" Descripción ");
		descripcionL.setBorder(BorderFactory.createLineBorder(Color.black));
		this.campos.add(descripcionL);
		if(edit)
			this.descripcion = new JTextArea(consumicion.getDescripcion());
		else
			this.descripcion = new JTextArea();
				
		this.campos.add(this.descripcion);
			
		/**********Campo precio***********************/
		JLabel precioL = new JLabel(" Precio ");
		precioL.setBorder(BorderFactory.createLineBorder(Color.black));
	
		this.campos.add(precioL);
		if(edit)
			this.precioField = new JTextField(Float.toString(consumicion.getPrecio()));
		else
			this.precioField = new JTextField();
		
		precioField.setMaximumSize(new Dimension(1, 1));	
		this.campos.add(precioField);
		
		/*************Campo tipo**********************/
		JLabel tipoL = new JLabel(" Tipo ");
		tipoL.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.campos.add(tipoL);
		TipoPlatos[] arrayTipos = TipoPlatos.values();
		Vector<TipoPlatos> vectorTipos = new Vector<TipoPlatos>();
		for(TipoPlatos t: arrayTipos){
			vectorTipos.add(t);
		}
		tipoPlato = new JComboBox<TipoPlatos>(vectorTipos);
		
		tipoPlato.setMaximumSize(new Dimension(1, 1));	
		this.campos.add(tipoPlato);
		
		/**************Campo disponible*****************/
		
		JLabel disponibleL = new JLabel(" Disponible ");
		disponibleL.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.campos.add(disponibleL);
		disponible = new JRadioButton();
		disponible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(disponible.isSelected())
					textoDisponibilidad.setText("Plato disponible");
				else
					textoDisponibilidad.setText("Plato no disponible");		
			}
		});
		

		contenerDisponibilidad = new JPanel();
		contenerDisponibilidad.setMaximumSize(new Dimension(1, 1));	
		contenerDisponibilidad.add(disponible);
		textoDisponibilidad = new JLabel("Disponibilidad del plato");
		contenerDisponibilidad.add(textoDisponibilidad);
		this.campos.add(contenerDisponibilidad);
		/*************Configuracion final***************/
		SpringUtilities.makeCompactGrid(campos, 5, 2, 6, 6, 10, 10);
		this.add(campos, BorderLayout.CENTER);
	}
	
	private void anyadirControles(){
		JButton done = new JButton("done");
		done.addActionListener(new ActionListener(){				
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nombrePlato.getText().isEmpty())
					notificar("Debe indicar un nombre de plato");
				else if(descripcion.getText().isEmpty())
					notificar("Debe decribir el plato");
				else if(precioField.getText().isEmpty())
					notificar("Debe indicar un precio");
				else if(!isNumber(precioField.getText()))
					notificar("El precio debe ser un numero");
				else
					guardarPlato();					
			}
		});
		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener(){				
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaGeneral.devolverControlPrincipal();					
			}
		});
		JPanel controles = new JPanel();
		controles.setLayout(new GridLayout());
		controles.add(done);
		controles.add(cancel);
		this.add(controles, BorderLayout.SOUTH);
		
	}
	private Consumicion nuevaConsumicion(){
		TipoPlatos tipo = (TipoPlatos) tipoPlato.getSelectedItem();
		switch (tipo) {
		case BEBIDA:
			return new Bebida(nombrePlato.getText(), Float.parseFloat(precioField.getText()),disponible.isSelected(),  descripcion.getText());
		case PRIMERO:
			return new Plato(nombrePlato.getText(), Float.parseFloat(precioField.getText()),disponible.isSelected(),  descripcion.getText());
		case SEGUNDO:
			return new Plato(nombrePlato.getText(), Float.parseFloat(precioField.getText()),disponible.isSelected(),  descripcion.getText());
		case POSTRE:
			return new Postre(nombrePlato.getText(), Float.parseFloat(precioField.getText()),disponible.isSelected(),  descripcion.getText());
		default:
			return consumicionInicial;
		}
	}
	private void guardarPlato(){
		/*Esta funcion actualiza en el menu el plato cambiado
		 * le pasa el antiguo y el nuevo
		 */
		if(edit)
			restControl.actualizaConsumicion(consumicionInicial, nuevaConsumicion(), (TipoPlatos) tipoPlato.getSelectedItem());
		else
			restControl.addConsumicion(nuevaConsumicion(), (TipoPlatos) tipoPlato.getSelectedItem());
		ventanaGeneral.devolverControlPrincipal();
	}
	private void notificar(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	private boolean isNumber(String palabra){
		try{
			Float.parseFloat(palabra);
		}catch( NumberFormatException n){
			return false;
		}
		return true;
	}
	
	//TODO Elegir disponibilidad
	private JPanel campos;
	private JRadioButton disponible;
	private JTextField precioField;
	private GUIController restControl;
	private JTextField nombrePlato;
	private JTextArea descripcion;
	private JComboBox<TipoPlatos> tipoPlato;
	private VerMenu ventanaGeneral;
	private boolean edit;
	private Consumicion consumicionInicial;
	JPanel contenerDisponibilidad;
	JLabel textoDisponibilidad;
	
}