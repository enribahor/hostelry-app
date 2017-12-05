package is.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CantidadExistencias extends JFrame{
	public CantidadExistencias(GUIController controller){
		notificar("Disponible en la versión de pago");
	}
	
	private void notificar(String message){
		JOptionPane.showMessageDialog(this, message);
		this.dispose();
	}
}
