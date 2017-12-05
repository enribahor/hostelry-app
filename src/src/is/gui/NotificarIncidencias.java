package is.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NotificarIncidencias extends JFrame {
	public NotificarIncidencias(GUIController controller) {
		this.controlador = controller;

		JPanel panel = new JPanel(new BorderLayout());
		JButton boton = new JButton("Send");
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notificar("Se ha enviado la notificacion");
			}
		});
		this.add(panel);
		panel.add(new JTextField("Escriba su problema y mandelo") {
			public boolean isEditable() {
				return false;
			}
		},
				BorderLayout.NORTH);
		panel.add(boton, BorderLayout.SOUTH);
		text = new JTextArea();
		panel.add(text);

		this.setSize(350, 220);
		this.setVisible(true);

	}

	private void notificar(String message) {
		JOptionPane.showMessageDialog(this, message);
		this.dispose();
	}

	private JTextArea text;
	private GUIController controlador;
}
