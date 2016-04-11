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
		
		/*Esto es redundate, debería ser setWidth, pero no iba*/
		getColumn(nomColumna).setMaxWidth(ancho);
		getColumn(nomColumna).setMinWidth(ancho);
	}
		
	/*Increíble, pero cierto....Reescribiendo este método, provocas que no
	 * se puedan editar ninguna de las celdas de una tabla...
	 * IMPLICACIONES IMPORTANTES en JAVA
	 * Al heredar de JTable, la MV o sabe dios quién, invoca mediante callbacks
	 * automáticamente a este método cuando se detectan operaciones sobre un objeto
	 * de este tipo que implican la comprobación de esta característica*/
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; 
    }
	
}