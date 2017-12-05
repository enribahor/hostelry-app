package is.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class About extends JFrame {
	public About() {
				
		JLabel[] labels = new JLabel[6];
		this.setLayout(new GridLayout(6,1));
		labels[0] = new JLabel("	Equipo de desarrollo: ");	
		labels[1]  = new JLabel("	Enrique Ballesteros Horcajo");	
		labels[2]  = new JLabel("	Jesús Aguirre Pemán");	
		labels[3]  = new JLabel("	Jaime Dan Porras Rhee");	
		labels[4]  = new JLabel("	Iker Prado Rujas");	
		labels[5]  = new JLabel("	Alejandro Villarín Prieto");			
		for (JLabel l : labels) this.add(l);
		this.setSize(350, 220);
		this.setVisible(true);

	}
	static final String LINE_SEPARATOR = System.getProperty("line.separator");

}
