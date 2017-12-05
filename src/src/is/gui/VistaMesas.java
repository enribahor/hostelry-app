package is.gui;

import is.restaurante.ComandaInfo;
import is.restaurante.Mesa;
import is.restaurante.MesaInfo;
import is.restaurante.MesaObserver;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
/**
 * Muestra todas las mesas
 * Al elegir una mesa permite añadir o eliminar una comanda para esa mesa.
 * TODO lo está arreglando villarín, con los cambios que hizo el y los que hizo Jaime
 * @author JaimeDan
 * @author Villarin
 */
public class VistaMesas extends JPanel implements MesaObserver {

	public VistaMesas(final GUIController controller) {
		this.restController = controller;
		this.mesas = this.restController.requestMesas();
		this.setLayout(new BorderLayout());
		comandasPanel = new JPanel[mesas.length];
		comandasContainer = new JPanel();
		comandasLayout = new CardLayout();
		comandasContainer.setLayout(comandasLayout);
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(comandasLayout);
		final TitledBorder title = new TitledBorder("Mesa");
		cardPanel.setBorder(title);
		for (int j = 0; j < mesas.length; j++) {
			comandasPanel[j] = new JPanel();
			TitledBorder titulo = BorderFactory.createTitledBorder("Mesa "
					+ (j));
			comandasPanel[j].setBorder(titulo);
			// boolean true, para ver si es la primera vez que escogemos la mesa
			setComandasPanel(j, true);
		}

		/* Creamos un panel con las mesas, usando un flowlayout */
		JPanel panelMesas = new JPanel();
		panelMesas.setLayout(new FlowLayout());
	
		/*
		 * Para cada mesa añadimos un listener que hara que el cardlayout
		 * muestre el panel de comandas correspondiente a la mesa j
		 */
		for (int i = 0; i < mesas.length; i++) {
			// el numero de mesa siempre es el mismo que el indice
			final Integer j = mesas[i].getNumeroMesa();// para que pueda ser
														// utilizado por el
														// listener

			// se pone con getnumeromesa, da igual que empiece por 0, porque en
			// otros sitios esta asi
			JButton mesa = new JButton("Mesa " + (mesas[i].getNumeroMesa()  ));
			mesa.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					numMesa = j;
					title.setTitle("Mesa " + (j));
					addMesaObserver(j);
					comandasLayout.show(
					comandasContainer, j.toString());

				}

			});
			panelMesas.add(mesa);
		}
		/* Añadimos el panel con las mesas al norte */
		this.add(panelMesas, BorderLayout.NORTH);
		/*
		 * Añadimos el contenedor padre con las comandas por cada mesa en el
		 * centro
		 */
		this.add(comandasContainer, BorderLayout.CENTER);

		JFrame frame = new JFrame("Comandas por mesa");
		frame.setPreferredSize(new Dimension(500, 300));
		frame.setVisible(true);
		frame.add(this);
		frame.pack();

	}

	protected void addMesaObserver(int j) {
		restController.addMesaObserver(this, j);
		
	}

	public void setComandasPanel(Integer numeroMesa, boolean desdeElPrincipio) {
		// se deja con numeroMesa
		TitledBorder title = new TitledBorder("Mesa " + (numeroMesa ));
		if (desdeElPrincipio) {
			comandas = restController.getMesa(numeroMesa).getListaComandas();
		}
		comandasPanel[numeroMesa].setBorder(title);
		comandasPanel[numeroMesa].setLayout(new BorderLayout());
		JPanel comanditasPanel = new JPanel();
		/*
		 * Se crea un JPanel con Flow layout para añadir como botones las
		 * comandas de las mesas igual en vez de botones se podrian usar
		 * JRadioButtons
		 */
		comanditasPanel.setLayout(new FlowLayout());
		/* Si la mesa tiene comandas, es decir, comandas!= null */
		if (comandas != null) {
			/* Creamos los botones con las comandas */
			for (int i = 0; i < comandas.length; i++) {
				final int j = i;
				JButton comandaMesa = new JButton("Comanda " + (i ));
				comandaMesa.addActionListener(new ActionListener() {
					/*
					 * Al seleccionar una comanda guardaremos en un atributo qué
					 * comanda hemos elegido
					 */
					@Override
					public void actionPerformed(ActionEvent e) {
						comandaSelected = j;
					}

				});
				/* Añadimos el boton al panel con las comandas */
				comanditasPanel.add(comandaMesa);
			}
			/*
			 * Despues de haber creado el panel con las comandas lo añadimos al
			 * centro de comandasPanel[numPanel]
			 */
			comandasPanel[numeroMesa].add(comanditasPanel, BorderLayout.CENTER);
		}

		/* Creamos un JPanel que tendrá los botones de Editar, añadir y eliminar */
		JPanel botoncitosPanel = new JPanel();
		botoncitosPanel.setLayout(new FlowLayout());

		/*JButton editarComanda = new JButton("Editar");
		editarComanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		botoncitosPanel.add(editarComanda);*/

		JButton añadirComanda = new JButton("Añadir");
		añadirComanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (numMesa != -1) {
					restController.requestNewCommand(numMesa);
					new RealizarPedido(restController, numMesa, comandas.length - 1);
				}
				else notificar("Debe seleccionar una mesa");
				
				cardSet(numMesa);
			}

			

		});
		botoncitosPanel.add(añadirComanda);

		JButton eliminarComanda = new JButton("Eliminar");
		eliminarComanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comandaSelected != -1 && numMesa != -1) {
					restController.eliminarComanda(numMesa, comandaSelected);
				}
				else notificar("Debe seleccionar una comanda");
				cardSet(numMesa);
			}

		});
		botoncitosPanel.add(eliminarComanda);
		/*
		 * Se añade el Panel con los botones de control al
		 * comandasPanel[numeroMesa]
		 */
		comandasPanel[numeroMesa].add(botoncitosPanel, BorderLayout.SOUTH);

		/*
		 * Se añade al JPanel padre el comandasPanel[numeroMesa], pasandole como
		 * clave el numero de mesa en string
		 */
		comandasContainer.add(comandasPanel[numeroMesa], numeroMesa.toString());
	}
	
	private void cardSet(Integer numMesa) {
		comandasLayout.show(
				comandasContainer, numMesa.toString());
	}

	@Override
	public void cambioOcurrido(final ComandaInfo[] comandas, int numeroMesas) {
		this.comandas = comandas;
		comandasPanel[numeroMesas].removeAll();
		setComandasPanel(numeroMesas, false);
		comandasPanel[numeroMesas].requestFocusInWindow();
	}
	private void notificar(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	private JPanel comandasContainer;
	private GUIController restController;
	private MesaInfo[] mesas;
	private ComandaInfo[] comandas;
	private JPanel[] comandasPanel;
	int comandaSelected = -1;
	private int numMesa = -1;
	
	private final CardLayout comandasLayout;

	@Override
	public void facturaGenerada(String factura) {
		// TODO Auto-generated method stub

	}

}
