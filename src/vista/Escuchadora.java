package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Escuchadora implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		System.out.println("ha pasado algo" + e.getActionCommand()); 
		
	}

}
