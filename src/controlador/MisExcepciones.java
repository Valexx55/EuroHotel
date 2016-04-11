package controlador;


import java.io.*;
import java.util.Calendar;
import java.lang.Exception; 
import vista.MensajesDeInfo;
import vista.MenuPpal;

@SuppressWarnings("all")
public class MisExcepciones {

	static final String ficheroDeErrores= "errores.txt";
	
	private static void mostrarRastro (PrintWriter fichero)
	{
		StackTraceElement pilaEjecu [];
		Throwable t = new Throwable ();
		pilaEjecu = t.getStackTrace();
		 
		fichero.println ("'\t'+___________________");
		fichero.println ("'\t'+|. . .Rastro . . .| //Nombre del m�todo (n� de l�nea)");
		fichero.println ();
				
		for (int tamanio = (pilaEjecu.length-1); tamanio!=1  ; tamanio--)
			{
				if (!pilaEjecu [tamanio].isNativeMethod())
					fichero.println ('\t'+pilaEjecu [tamanio].getClassName()+"."+pilaEjecu [tamanio].getMethodName() + " ("+pilaEjecu [tamanio].getLineNumber()+")");
							
			}
	}

	public static void RegistrarExcepcion (String descripcion, boolean abortar)
	{
		/*FileWriter fo = null;
		try {
			fo = new FileWriter (ficheroDeErrores, true);// CON LA OPCI�N TRUE ES A�ADIR TIPO APPEND
			PrintWriter fichero = new PrintWriter (fo);
			fichero.println('\t'+"----------------------------");
			fichero.println('\t'+ Calendar.getInstance().getTime().toString());
			fichero.println('\t'+"----------------------------");
			fichero.println();
			fichero.println();
			fichero.println('\t'+descripcion);
			fichero.println();
			fichero.println();
			mostrarRastro(fichero);
			fichero.println();fichero.println();fichero.println();
			
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (abortar)
			{
					MensajesDeInfo.informar(new MenuPpal (), MensajesDeInfo.Aborto);
					System.exit(-1);
			}*/
		 //esto deber�a ser una llamada finally o lo q sea para salir y cerrar los ficheros y las cosas que hay abiertas
		//TODO DEBER�AMOS INCLUIR EN EL FICHERO DE LOG DE ERRORES LA FECHA DEL ERROR
		//Y NO ESTAR�A DE M�S LA L�NEA DE C�DIGO FUENTE Y LA CLASE DONDE SUCEDE EL ERROR
	}
}
