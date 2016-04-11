package vista;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("all")
public class PruebasTabla extends JTable {
	 
	public PruebasTabla (Vector filas, Vector columnas)
	{
		super(filas, columnas);
	}
	
}