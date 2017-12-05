package is.gui;

import is.restaurante.ComandaInfo;
import is.restaurante.Mesa;
import is.restaurante.MesaInfo;
import is.restaurante.MesaObserver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Genera la factura de una mesa, sumando el coste de todas las comandas
 * asociadas a esa mesa, y la muestra por pantalla.
 * 
 * @author Villarín
 */
public class GenerarFactura implements MesaObserver {

	@SuppressWarnings({ "serial", "unchecked" })
	public GenerarFactura(GUIController rest) {
		this.controlador = rest;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		panel.add(new JLabel("Introduzca el numero de mesa"));

		MesaInfo[] mesasRestaurante = controlador.requestMesas();

		String[] stringMesas = new String[mesasRestaurante.length];
		for (int i = 0; i < stringMesas.length; i++)
			stringMesas[i] = "Mesa " + (i);

		mesas = new JComboBox(stringMesas);
		panel.add(mesas);
		panel.add(new JButton("Generar Factura") {
			{
				this.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int j = mesas.getSelectedIndex();
						addMesaObserver(j);
						controlador.getMesa(j).generarFactura();
						removeMesaObserver(j);

					}

				});
			}
		});
		JFrame marco = new JFrame();
		marco.add(panel);
		marco.setSize(new Dimension(400, 150));
		marco.setVisible(true);

	}

	private void addMesaObserver(int j) {
		controlador.addMesaObserver(this, j);
	}

	private void removeMesaObserver(int j) {
		controlador.removeMesaObserver(this, j);
	}

	private JComboBox mesas;
	private JFrame marco;
	private JFrame marco2;
	private Mesa mesa;
	private GUIController controlador;
	private JTextField numMesa;

	@SuppressWarnings("serial")
	@Override
	public void facturaGenerada(String factura) {
		marco2 = new JFrame();
		marco2.setLayout(new GridLayout(2, 1));
		marco2.add(new JLabel(factura));
		marco2.add(new JButton("Aceptar") {
			{
				this.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						marco2.setVisible(false);
					}

				});
			}
		});
		marco2.pack();
		marco2.setVisible(true);
	}

	@Override
	public void cambioOcurrido(ComandaInfo[] comandas, int numeroMesa) {
		// TODO Auto-generated method stub

	}

}
