package is.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import is.restaurante.Menu;
import is.restaurante.MenuObserver;
import is.restaurante.TipoPlatos;
import is.restaurante.consumicion.Consumicion;

/**
 * Muestra el menú, pudiendo crear platos y añadirlos al propio menú.
 * 
 * @author Kike
 * @author JaimeDan
 */
@SuppressWarnings("serial")
// MenuObserver
public class VerMenu extends JFrame implements MenuObserver {
	public VerMenu(GUIController controller) {
		inicializarVerMenu();
		this.controlador = controller;

		this.controlador.registerMenuObserver(this);

		menu = controlador.getMenu();
		inicializarVentana();

		TipoPlatos[] tipoPlatos = TipoPlatos.values();
		for (TipoPlatos tPlato : tipoPlatos) {
			nuevoTipoPlato(tPlato);
		}
		this.setVisible(true);
	}

	// Crea la pestaña para un tipo de plato
	private void nuevoTipoPlato(TipoPlatos tPlato) {
		JPanel panel = new JPanel(new BorderLayout());
		pestanyas.add(tPlato.toString(), panel);
		cadaPestanya.add(panel);

		JPanel opciones = new JPanel(new GridLayout());
		panel.add(opciones, BorderLayout.SOUTH);

		JButton anyadir = new JButton("add");
		anyadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anyadirNuevoPlato();
			}
		});
		JButton editar = new JButton("edit");
		editar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarPlato();
			}
		});
		JButton borrar = new JButton("remove");
		borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarPlato();
			}
		});
		opciones.add(anyadir);
		opciones.add(editar);
		opciones.add(borrar);

		/**** Añade los platos correspondientes a un tipo de Plato ****/

		JPanel platos = new JPanel(new FlowLayout());
		platos.setName(tPlato.toString());
		panel.add(platos);
		ventanas.add(platos);
		ArrayList<Consumicion> consum = menu.getPlatos(tPlato);
		for (Consumicion cons : consum) {
			/* Habría que guardar las descripciones tb para mostrarlas */
			JToggleButton boton = new JToggleButton(cons.getNombre());
			botones.add(boton);
			platos.add(boton);
			boton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					consumicionPulsada();
				}

			});
		}
		// añades los JtextFiel para las descripciones
		JTextField descrip = new JTextField();
		descrip.setMaximumSize(new Dimension(1, 1));
		JScrollPane js = new JScrollPane(descrip);
		descrip.setEditable(false);
		panel.add(js, BorderLayout.NORTH);
		descriptions.add(descrip);
	}

	private void inicializarVentana() {

		panelGeneral = new JPanel();// Creamos el panel principal
		cardLayout = new CardLayout();
		panelGeneral.setLayout(cardLayout);

		pestanyas = new JTabbedPane();

		panelGeneral.add(pestanyas, "pestanyas");
		this.add(panelGeneral);
	}

	// Inicializa la ventana
	private void inicializarVerMenu() {
		this.setSize(600, 400);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		this.botones = new ArrayList<JToggleButton>();
		ventanas = new ArrayList<JPanel>();
		cadaPestanya = new ArrayList<JPanel>();
		this.descriptions = new ArrayList<JTextField>();
	}

	/* Elimina el plato seleccionado */
	private void borrarPlato() {
		if (this.consumicionSeleccionada != null)
			this.controlador.deleteConsumicion(menu
					.getConsumicion(consumicionSeleccionada.getText()));
		else
			notificar("Se debe seleccionar el plato a borrar");
	}

	/* Edita el Plato que esté seleccionado */
	private void editarPlato() {
		if (this.consumicionSeleccionada != null) {
			ConsumicionSwing nuevaCons = new ConsumicionSwing(this,
					controlador, menu.getConsumicion(consumicionSeleccionada
							.getText()), true);
			panelGeneral.add(nuevaCons, "editConsumicion");
			cardLayout.show(panelGeneral, "editConsumicion");
		} else
			notificar("Se debe seleccionar el plato a editar");
	}

	/* Añade un nuevo plato al menu */
	private void anyadirNuevoPlato() {
		ConsumicionSwing nuevaCons = new ConsumicionSwing(this, controlador,
				null, false);
		panelGeneral.add(nuevaCons, "createConsumicion");
		cardLayout.show(panelGeneral, "createConsumicion");
	}

	/*
	 * Libera todos los botones excepto el último pulsado, y guarda el plato
	 * pulsado
	 */

	private void consumicionPulsada() {
		for (JToggleButton boton : botones)
			if (!boton.isFocusOwner()) //
				boton.setSelected(false);
			else {
				consumicionSeleccionada = boton;
				mostrarDescripcion(menu.getConsumicion(boton.getText())
						.getDescripcion());
			}
	}

	/* Muestra la descripción de la consumición dada */
	private void mostrarDescripcion(String description) {
		for (JTextField jText : descriptions)
			jText.setText(description);
	}

	public void devolverControlPrincipal() {
		cardLayout.show(panelGeneral, "pestanyas");
		for (JPanel pan : ventanas)
			pan.repaint();
	}

	private void notificar(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/* Metodos del observer */
	private JToggleButton buscarBoton(String name) {
		for (JToggleButton bot : botones) {
			if (bot.getText().equals(name))
				return bot;
		}
		return null;
	}

	private void borrarBoton(String nameBoton, String nameTipo) {
		JToggleButton botonParaBorrar = buscarBoton(nameBoton);
		if (botonParaBorrar != null) {
			JPanel pan = buscarPanel(nameTipo);
			pan.remove(botonParaBorrar);
			pan.repaint();
		}
	}

	private JPanel buscarPanel(String namePanel) {
		for (JPanel panel : ventanas) {
			if (panel.getName().equals(namePanel))
				return panel;
		}
		return null;
	}

	private void anyadirBoton(String consId, String tipo) {
		JToggleButton boton = new JToggleButton(consId);
		botones.add(boton);
		JPanel platos = buscarPanel(tipo);
		platos.add(boton);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consumicionPulsada();
			}

		});
		platos.repaint();
	}

	@Override
	public void addConsumption(String consId, String tipo) {
		anyadirBoton(consId, tipo);
	}

	@Override
	public void deleteConsumption(String consId, String tipo) {
		borrarBoton(consId, tipo);
	}

	@Override
	public void editConsumption(String nameAnt, String nameNuevo,
			String tipoAnt, String tipoNuevo) {
		deleteConsumption(nameAnt, tipoAnt);
		anyadirBoton(nameNuevo, tipoNuevo);
	}

	private ArrayList<JTextField> descriptions;
	private ArrayList<JPanel> cadaPestanya;
	private JTabbedPane pestanyas;
	private CardLayout cardLayout;
	private JPanel panelGeneral;
	private ArrayList<JPanel> ventanas;
	private JToggleButton consumicionSeleccionada;
	private ArrayList<JToggleButton> botones;
	private GUIController controlador;
	private Menu menu;

}
