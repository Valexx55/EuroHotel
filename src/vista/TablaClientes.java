package vista;
import java.util.Vector;

@SuppressWarnings("all")
public class TablaClientes extends Tabla {
	
	public String [] nombreColumnasConcepto = {"DNI","Nombre","Apellidos"};
	
	public TablaClientes (Vector filas, Vector columnas) {
		super (filas, columnas);
	}

}
