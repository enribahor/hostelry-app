package is.gui;

import is.restaurante.Comanda;
import is.restaurante.ComandaInfo;
import is.restaurante.ComandaObserver;
import is.restaurante.Menu;
import is.restaurante.Mesa;
import is.restaurante.Restaurante;
import is.restaurante.TipoPlatos;
import is.restaurante.consumicion.Consumicion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Gestiona el pedido desde una mesa, puediendole añadir bebidas, primeros, segundos o postres y mandando la comanda a cocina.
 * @author Jesús
 */

@SuppressWarnings("serial")
public class RealizarPedido extends JPanel implements ComandaObserver{

	/**
	 * 
	 * @param rest
	 */
	public RealizarPedido(final GUIController rest, final int numeroMesa, int comandaSeleccionada) {	
		this.restaurante = rest;
		this.restaurante.addComandaObserver(this, numeroMesa, comandaSeleccionada);
		this.numeroComanda = comandaSeleccionada;
		this.numeroMesa = numeroMesa;
		inicializaPaneles();
		this.setLayout(new BorderLayout());
		JPanel tipoPlato = new JPanel();
		panelCentral = new JPanel();
		final CardLayout menu = new CardLayout();
		panelCentral.setLayout(menu);	
		for (Integer i=0; i<4; i++)
			panelCentral.add(this.elementosMenu[i], i.toString());
		tipoPlato.setLayout(new GridLayout(4,1));
		tipoPlato.add(new JButton("Bebidas"){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						menu.show(panelCentral, "0");
					}
					
				});
			}
		});
		tipoPlato.add(new JButton("Primeros"){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						menu.show(panelCentral, "1");
						
					}
					
				});
			}
		});
		tipoPlato.add(new JButton("Segundos"){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						menu.show(panelCentral, "2");
						
					}
					
				});
			}
		});
		tipoPlato.add(new JButton("Postres"){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						menu.show(panelCentral, "3");
						
					}
					
				});
			}
		});
		
		this.add(tipoPlato, BorderLayout.WEST);
		frame = new JFrame("Realizar Pedido");
		this.add(panelCentral);
        String[] nombresMesa = { "Mesa 1: 2 personas", "Mesa 2: 4 personas", "Mesa 3: 4 personas", "Mesa 4: 6 personas", "Mesa 5: 8 personas" };
        mesas = new JComboBox(nombresMesa);
		this.add(mesas, BorderLayout.SOUTH);
		
		JButton botonPedido = new JButton("Realizar Pedido"){
			{
				this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
					
				});
			}
		};		
		JPanel panelBotones = new JPanel();	
		panelBotones.setLayout(new GridLayout(1,2));
		panelBotones.add(botonPedido);
		panelBotones.add(new JButton("Cancelar"){
			{
				this.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						restaurante.eliminaUltimaComanda(numeroMesa);
						
						frame.dispose();
					}
					
				});
			}
		});
		this.add(panelBotones, BorderLayout.SOUTH);
		TitledBorder titled = new TitledBorder("Pedido");
		text = new JTextArea("");
		text.setEditable(false);
		scroller = new JScrollPane(text);
        scroller.setPreferredSize(new Dimension(200,200));
        scroller.setBorder(titled);     
        this.add(scroller, BorderLayout.EAST);		
		frame.setVisible(true);	
		frame.add(this);
		frame.pack();

	}
	
	@Override
	public void setComandaNumber(int n) {
		this.numeroComanda = n;
	}
	
	public RealizarPedido(GUIController controller, ComandaInfo comandaEdit){
		
	}

	public void inicializaPaneles(){
		elementosMenu = new JPanel[4];
		ArrayList<Consumicion> beb = this.restaurante.requestBebidas(),
		prim = this.restaurante.requestPrimeros(),
		seg = this.restaurante.requestSegundos(), 
		pos = this.restaurante.requestPostres();
		bebidas = new CeldaPlato[beb.size()];
		primeros = new CeldaPlato[prim.size()];
		segundos = new CeldaPlato[seg.size()];
		postres = new CeldaPlato[pos.size()];	
		elementosMenu[0] = new JPanel();
		int i = 0;
		for (Consumicion c : beb){
			bebidas[i] = new CeldaPlato(restaurante, c, numeroMesa, numeroComanda, TipoPlatos.BEBIDA);
			elementosMenu[0].add(bebidas[i]);
		}
		elementosMenu[1] = new JPanel();
		for (Consumicion c : prim){
			CeldaPlato plato = new CeldaPlato(restaurante, c, numeroMesa, numeroComanda, TipoPlatos.PRIMERO);
			elementosMenu[1].add(plato);
		}
		elementosMenu[2] = new JPanel();
		for (Consumicion c : seg){
			CeldaPlato plato = new CeldaPlato(restaurante, c, numeroMesa, numeroComanda, TipoPlatos.SEGUNDO);
			elementosMenu[2].add(plato);
		}
		elementosMenu[3] = new JPanel();
		for (Consumicion c : pos){
			CeldaPlato plato = new CeldaPlato(restaurante, c, numeroMesa, numeroComanda, TipoPlatos.POSTRE);
			elementosMenu[3].add(plato);
		}
	}
	
	@Override
	public void comandaHaCambiado(String comanda) {
		this.text.setText(comanda);
	}
	
	private CeldaPlato[] primeros;
	private CeldaPlato[] segundos;
	private CeldaPlato[] postres;
	private CeldaPlato[] bebidas;	
	private JFrame frame;
	private JPanel panelCentral;
	private JScrollPane scroller;
	private JTextArea text;
	private GUIController restaurante;
	private JComboBox mesas;
	private JPanel[] elementosMenu;
	private int numeroComanda;
	private int numeroMesa;	
	@Override
	public void comandaError(String error) {
		// TODO Auto-generated method stub
		
	}

	
	
	

	
}
