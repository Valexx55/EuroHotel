package vista;

import java.util.Enumeration;
@SuppressWarnings("all")
public class BarraDeMenu {

	
	
		
	/** Para indicar el orden en que aparece el men�
	 * El men� de los clientes ocupar� la primera posici�n en la barra de men�, 
	 * mientras que el men� de la factura ocupar� la segunda posici�n 
	*/
	private static final int MENUCLIENTE = 0;
	private static final int MENUFACTURA = 1;
	
	/*
	 * Estas constantes definen los nombres de las opciones de los men�s
	 */
	private static final String OPCION1BARRADEMENU = "Clientes";
	private static final String OPCION2BARRADEMENU = "Facturas";
	
	
	/*
	 * Este array contiene los nombres de las opciones de los men�s
	 */
	public static final String [] opcionesPrincipales = {OPCION1BARRADEMENU, OPCION2BARRADEMENU};
	
	/*
	 * Este array de enteros contiene el ORDEN de las opciones del men�.
	 * Lo recorreremos para generar las opciones del men� ordenadamente.
	 * Si deseamos modificar el orden de los men�s, bastar� con tocar este
	 * array o las constantes que lo componen
	 */
	public static final int [] opciones = {MENUCLIENTE, MENUFACTURA};
	
	
	/*
	 * Estas cadenas contienen la opci�n de los submen�s
	 */
	
	public static final String OPCION1CLIENTE = "Crear Cliente"; //OPCI�N 1
	public static final String OPCION2CLIENTE = "Reapertura Cliente"; //OPCI�N 2
	
	public static final String OPCION1FACTURA= "Crear Factura"; //OPCI�N 3
	public static final String OPCION2FACTURA = "Reapertura Factura"; //OPCI�N 4
	
	/*
	 * Este array de array de string contiene en cada posici�n (cada subarray) las
	 * opciones correspondientes a la opci�n -i�sima del array opciones
	 */
	public static final String [][] opcionesSecundarias = {{OPCION1CLIENTE, OPCION2CLIENTE}, {OPCION1FACTURA, OPCION2FACTURA}};

	
	public static int traduceOpcionANUm (String opcion)
	{
		if (opcion.equals(OPCION1CLIENTE)) return 1;
		else if (opcion.equals(OPCION2CLIENTE)) return 2;
		else if (opcion.equals(OPCION1FACTURA)) return 3;
		else if (opcion.equals(OPCION2FACTURA)) return 4;
		else return 0;
	}
	
	public static String[] getOpcionesPrincipales() {
		return opcionesPrincipales;
	}

	public static int[] getOpciones() {
		return opciones;
	}

	public static String[][] getOpcionesSecundarias() {
		return opcionesSecundarias;
	}
	
	public static String getNumOpcionPpal (int numopcion)
	{
		return (opcionesPrincipales[numopcion]);
	}
}
