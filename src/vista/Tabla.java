package vista;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.util.Vector;


@SuppressWarnings("all")
public abstract class Tabla extends JTable {
	 
	Font fuente = new Font ("Arial", 14 ,14 );
	int anchoCols [];
	int numCols;
	String campos [];
	Registro datos;
	
		
	
	public Tabla (Vector datos, Vector columnas)
	{
		super(datos, columnas);
		establecerFuente (new Font ("Arial", 14, 14));
		establacerSeleccionUnica();
	}
	
	public void establacerSeleccionUnica ()
	{
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void establecerFuente (Font fuente)
	{
		setFont(fuente);
	}
	
	public void fijarColumnaRedimensionable (String nomColumna, boolean redimensionable)
	{
		getColumn(nomColumna).setResizable(redimensionable);
	}
	
	public void fijarAnchoColumna (String nomColumna, int ancho)
	{
		
		/*Esto es redundate, deber�a ser setWidth, pero no iba*/
		getColumn(nomColumna).setMaxWidth(ancho);
		getColumn(nomColumna).setMinWidth(ancho);
	}
		
	/*Incre�ble, pero cierto....Reescribiendo este m�todo, provocas que no
	 * se puedan editar ninguna de las celdas de una tabla...
	 * IMPLICACIONES IMPORTANTES en JAVA
	 * Al heredar de JTable, la MV o sabe dios qui�n, invoca mediante callbacks
	 * autom�ticamente a este m�todo cuando se detectan operaciones sobre un objeto
	 * de este tipo que implican la comprobaci�n de esta caracter�stica*/
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; 
    }
	
}