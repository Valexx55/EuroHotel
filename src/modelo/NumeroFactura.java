package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Calendar;
import controlador.MisExcepciones;
@SuppressWarnings("all")
public class NumeroFactura 
{
		
	
	private static Long numeroFactura;
	private static final String rutaArchivNumFactura = "nfact.txt";
	private static final int numdigitosfactura = 4;
	
	
	private static String obtenerAnioActual ()
	{
		final int desfaseaniojava = 1900;
		
		return (new Integer (Calendar.getInstance().getTime().getYear()+desfaseaniojava)).toString();
	}
	
	private static void setNumeroFactura (Long nf)
	{
		numeroFactura = nf;
		
		try
			{
			PrintWriter pr = new PrintWriter (new File (rutaArchivNumFactura));
			pr.println(numeroFactura.toString());	
			pr.close();
			}	
		catch (Exception e) {
			MisExcepciones.RegistrarExcepcion("Error al fijar el número de la factura", true);
			}
	}
	
	private static Long generarNumeroAnioNuevo ()
	{
		String anio = obtenerAnioActual();
		
		for (int i = 0; i < numdigitosfactura; i++) 
			{
				anio = anio + "0";
			}
		return (new Long (anio));
	}
	
	
	
	private static boolean seHaProducidoCambioDeaño (String anioFactura, String anioActual)
	{
		return (anioFactura.compareTo(anioActual)!=0);
	}
	
	private static Long siguienteNumero (Long nFactura)
	{
		nFactura++;
		return nFactura;
	}
	
	private static Long obtenerNumFactActual ()
	{
		Long nf = null;
			
				
		try
			{
			BufferedReader br = new BufferedReader (new FileReader (new File (rutaArchivNumFactura)));
			nf = new Long (Long.parseLong(br.readLine()));
			br.close();
			}
		catch (Exception e) {
			MisExcepciones.RegistrarExcepcion("Error al obtener el número de la factura", true);
		}
		
		return nf;
	}
	
	public static Long getNumeroFactura ()
	
	{
		
		final int posinianio = 0; //posición de la cadena del fichero que representa la fehca donde empieza el año 
		final int posfinanio = 4; //posición de la cadena del fichero que representa la fehca donde finaliza el añ
		
		numeroFactura = obtenerNumFactActual ();
		
						
		if (!seHaProducidoCambioDeaño (numeroFactura.toString().substring(posinianio, posfinanio), obtenerAnioActual()))
			{
			setNumeroFactura(siguienteNumero (numeroFactura));
			}
		else //hemos cambiado de año ! -> cambia la serie 
			{
			setNumeroFactura(generarNumeroAnioNuevo ());
			}
		return numeroFactura;
	}
	
	public static void decrementarContadorFactura ()
	{
		final int posinianio = 0; //posición de la cadena del fichero que representa la fehca donde empieza el año 
		final int posfinanio = 4; //posición de la cadena del fichero que representa la fehca donde finaliza el añ
		
		numeroFactura = obtenerNumFactActual ();
		
		numeroFactura = numeroFactura -1;
						
		setNumeroFactura(numeroFactura);
		
		
	}
	
}
