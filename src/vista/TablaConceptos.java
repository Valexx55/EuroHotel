package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JViewport;

@SuppressWarnings("all")
public class TablaConceptos extends JTable {
	
	
	
		public TablaConceptos(Vector filas, Vector Columnas) {
			super (filas, Columnas);
		}
		
		@Override
		public Dimension getPreferredScrollableViewportSize() {
			Dimension d = new Dimension (320, 30);
			return d;
		}
		public boolean getScrollableTracksViewportHeight() 
		{
		          Component parent = getParent();
		          if(parent instanceof JViewport)
		              return parent.getHeight() > getPreferredSize().height;
		 
		          return false;
		}
		@Override
		//así conseguimos que no se puedan modificar los conceptos
		//sólo por el botón de modificar
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			return false;
		}
		


}
