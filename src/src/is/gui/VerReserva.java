package is.gui;

import is.Fecha;
import is.restaurante.LibroReservaObserver;
import is.restaurante.Reserva;
import is.restaurante.ReservaInfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *Muestra cuatro pestañas con las reservas de hoy, dentro de una semana, dentro de un mes y todas las reservas.
 * @author JaimeDan
 */

public class VerReserva implements LibroReservaObserver{

	@SuppressWarnings("serial")
	public VerReserva(GUIController rest) {
		this.controlador = rest;
		this.controlador.addLibroReservaObserver(this);
		inicializaTabla();
		
		tTodas = new JTable(tablaTodas);
		JScrollPane scrollTodas = new JScrollPane(tTodas);
		tHoy = new JTable(tablaHoy);
		JScrollPane scrollHoy = new JScrollPane(tHoy);
		tSemana = new JTable(tablaSemana);
		JScrollPane scrollSemana = new JScrollPane(tSemana);	
		JTabbedPane tabbedPane = new JTabbedPane();		
		this.actualizaVerReservas();
		
//		Añade los botones para todas
		JPanel panelTodas = new JPanel(new BorderLayout());
		JPanel opcionesTodas = new JPanel(new GridLayout());
		anyadirBotones(opcionesTodas, "todas");
		panelTodas.add(scrollTodas, BorderLayout.CENTER);
		panelTodas.add(opcionesTodas, BorderLayout.SOUTH);
		
//		Añade los botones para hoy
		JPanel panelHoy = new JPanel(new BorderLayout());
		JPanel opcionesHoy = new JPanel(new GridLayout());
		anyadirBotones(opcionesHoy, "hoy");
		panelHoy.add(scrollHoy, BorderLayout.CENTER);
		panelHoy.add(opcionesHoy, BorderLayout.SOUTH);
		
//		Añade los botones para semana
		JPanel panelSemana = new JPanel(new BorderLayout());
		JPanel opcionesSemana = new JPanel(new GridLayout());
		anyadirBotones(opcionesSemana, "semana");
		panelSemana.add(scrollSemana, BorderLayout.CENTER);
		panelSemana.add(opcionesSemana, BorderLayout.SOUTH);
		
//		Añade los botones para mes
				
//		Añade las tablas a las pestañas
		tabbedPane.addTab("Todas", null, panelTodas, "Muestra todas las reservas");
		tabbedPane.addTab("Hoy", null, panelHoy, "Muestra las reservas de hoy");
		tabbedPane.addTab("Esta semana",null, panelSemana,
		                  "Muestra las reservas de esta semana");
		
		ventanaVerReservas = new JFrame();
		ventanaVerReservas.add(tabbedPane);
		ventanaVerReservas.setSize(500, 300);
		ventanaVerReservas.setVisible(true);
	}
	
//	borra la reserva seleccionada
	private void borrarReserva(){
		
	}
	
	private void inicializaTabla(){
		tablaTodas = new TableReservas();
		tablaHoy = new TableReservas();
		tablaSemana = new TableReservas();
		//tablaMes = new TableReservas();
	}
	
	private void anyadirBotones(JPanel opciones, final String cuando){	
		JButton anyadir = new JButton("add");
		anyadir.addActionListener(new ActionListener(){				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new RealizarReserva(controlador, null, null, null, null, false);
				}
		});
		JButton editar = new JButton("edit");
		editar.addActionListener(new ActionListener(){				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cuando.equalsIgnoreCase("todas")){
					int i= tTodas.getSelectedRow();
					if (i >= 0){
						new RealizarReserva(controlador, tablaTodas.getValueAt(i, 0), 
								tablaTodas.getValueAt(i, 1),
								tablaTodas.getValueAt(i, 2),
								tablaTodas.getValueAt(i, 3),
								true);
						controlador.requestRemoveReserva(tablaTodas.getValueAt(i, 0), 
								tablaTodas.getValueAt(i, 1),
								tablaTodas.getValueAt(i, 2),
								tablaTodas.getValueAt(i, 3));
						
					}
						
				}
				else if (cuando.equalsIgnoreCase("hoy")){
					int i= tTodas.getSelectedRow();
					
					if (i >= 0){
						new RealizarReserva(controlador, tablaHoy.getValueAt(i, 0),  
								tablaHoy.getValueAt(i, 1),
								tablaHoy.getValueAt(i, 2),
								tablaHoy.getValueAt(i, 3), true
								);
						controlador.requestRemoveReserva(tablaHoy.getValueAt(i, 0),  
								tablaHoy.getValueAt(i, 1),
								tablaHoy.getValueAt(i, 2),
								tablaHoy.getValueAt(i, 3));
						
					}
						
				}
				else if (cuando.equalsIgnoreCase("semana")){
					if(tSemana.getSelectedRow() >= 0){
						int i= tTodas.getSelectedRow();
						new RealizarReserva(controlador,
							tablaSemana.getValueAt(i, 0), 
							tablaSemana.getValueAt(i, 1),
							tablaSemana.getValueAt(i, 2),
							tablaSemana.getValueAt(i, 3),
							true);
						controlador.requestRemoveReserva(tablaSemana.getValueAt(i, 0), 
								tablaSemana.getValueAt(i, 1),
								tablaSemana.getValueAt(i, 2),
								tablaSemana.getValueAt(i, 3));
						
					}
						
				}
			}
		});
		JButton borrar = new JButton("remove");
		borrar.addActionListener(new ActionListener(){				
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cuando.equalsIgnoreCase("todas")){
					if (tTodas.getSelectedRow() >= 0)
						controlador.requestRemoveReserva(tablaTodas.getValueAt(tTodas.getSelectedRow(), 0), 
								tablaTodas.getValueAt(tTodas.getSelectedRow(), 1),
								tablaTodas.getValueAt(tTodas.getSelectedRow(), 2),
								tablaTodas.getValueAt(tTodas.getSelectedRow(), 3));
				}
				else if (cuando.equalsIgnoreCase("hoy")){
					if (tHoy.getSelectedRow() >= 0)
						controlador.requestRemoveReserva(tablaHoy.getValueAt(tHoy.getSelectedRow(), 0),  
								tablaHoy.getValueAt(tHoy.getSelectedRow(), 1),
								tablaHoy.getValueAt(tHoy.getSelectedRow(), 2),
								tablaHoy.getValueAt(tHoy.getSelectedRow(), 3));
				}
				else if (cuando.equalsIgnoreCase("semana")){
					if(tSemana.getSelectedRow() >= 0)
						controlador.requestRemoveReserva(tablaSemana.getValueAt(tSemana.getSelectedRow(), 0), 
								tablaSemana.getValueAt(tSemana.getSelectedRow(), 1),
								tablaSemana.getValueAt(tSemana.getSelectedRow(), 2),
								tablaSemana.getValueAt(tSemana.getSelectedRow(), 3));
				}
				
					//tablaTodas.getSelectedRow();
				//borrarReserva();					
			}
		});
		opciones.add(anyadir);
		opciones.add(editar);
		opciones.add(borrar);
	}
	
	public void actualizaVerReservas(){
//		carga los datos en las tablas
		this.tablaTodas.actualizarTabla(this.controlador.getReservas(-1));
		this.tablaHoy.actualizarTabla(this.controlador.getReservas(0));
		this.tablaSemana.actualizarTabla(this.controlador.getReservas(1));
		//this.tablaMes.actualizarTabla(this.controlador.getReservas(2));
	}
	
	private GUIController controlador;
	
	@Override
	public void cambioOcurrido(ArrayList<ReservaInfo> reservaHoy, ArrayList<ReservaInfo> reservaSemana, ArrayList<ReservaInfo> reservasSiempre) {
		tablaHoy.actualizarTabla(reservaHoy);
		tablaSemana.actualizarTabla(reservaSemana);
		tablaTodas.actualizarTabla(reservasSiempre);
	}
	private JFrame ventanaVerReservas;
	private JTable tTodas;
	private JTable tHoy;
	private JTable tSemana;
	TableReservas tablaTodas;
	TableReservas tablaHoy;
	TableReservas tablaSemana;
	//TableReservas tablaMes;
}
