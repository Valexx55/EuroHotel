package controlador;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Eventos {
	
	public final static String aceptarFichaCliente = "GFC"; 
	public final static String aniadirConceptoFactura = "ACF"; 
	public final static String cancelarFichaCliente = "CFC";
	public final static String imprimirFichaCliente = "IFC";
	public final static String buscarReaperturaCliente = "BRC";
	public final static String aceptarFichaFactura = "AFF";
	public final static String cancelarFichaFactura = "CFF";
	public final static String imprimirFichaFactura = "IFF";
	public final static String buscarReaperturaFactura = "BRF";
	public final static String listarFacturaPorNum = "LFXN";
	public final static String listarFacturaPorFecha = "LFXF";
	public final static String listarFacturasPrimerTrim = "LF1T";
	public final static String listarFacturasSegundoTrim = "LF2T";
	public final static String listarFacturasTercerTrim = "LF3T";
	public final static String listarFacturasCuartoTrim = "LF4T";
	public final static String cajaTextoDni = "CTDNI";
	public final static String modificarConceptoFactura = "MCF";
	public final static String eliminarConceptoFactura = "ECF";
	public final static String eliminarFichaCliente = "EFC";
	public final static String eliminarFactura = "EF";
	public final static String FormularioCliente = "ExitFormCli";
	public final static String crearFacturaCliente = "CrearFactCli";
	public final static String clickComboEditCli = "ComboDNIEditCli";
	public final static String clickComboFactCli = "ComboDNIFactCli";
	
	public final static String clickComboFactEdit = "ComboFacturaEdit";
	
	
	
	
	
	private static List<String> listaNombresEventos = null; 
	private static Eventos instancia = new Eventos();
	
	public static Eventos getInstance ()
	{
		return instancia;
	}
	
	
	
	
	private Eventos () 
	
	{
		
	listaNombresEventos = new ArrayList<String> ();
		
	listaNombresEventos.add (this.aceptarFichaCliente);
	listaNombresEventos.add (this.aceptarFichaFactura);
	listaNombresEventos.add (this.aniadirConceptoFactura);
	listaNombresEventos.add (this.buscarReaperturaCliente);
	listaNombresEventos.add (this.buscarReaperturaFactura);
	listaNombresEventos.add (this.cajaTextoDni);
	listaNombresEventos.add (this.cancelarFichaCliente);
	listaNombresEventos.add (this.cancelarFichaFactura);
	listaNombresEventos.add (this.eliminarConceptoFactura);
	listaNombresEventos.add (this.imprimirFichaCliente);
	listaNombresEventos.add (this.imprimirFichaFactura);
	listaNombresEventos.add (this.listarFacturaPorFecha);
	listaNombresEventos.add (this.listarFacturaPorNum);
	listaNombresEventos.add (this.listarFacturasCuartoTrim);
	listaNombresEventos.add (this.listarFacturasPrimerTrim);
	listaNombresEventos.add (this.listarFacturasSegundoTrim);
	listaNombresEventos.add (this.listarFacturasTercerTrim);
	listaNombresEventos.add (this.modificarConceptoFactura);
	listaNombresEventos.add (this.eliminarFichaCliente);
	listaNombresEventos.add (this.eliminarFactura);
	listaNombresEventos.add (this.FormularioCliente);
	listaNombresEventos.add (this.crearFacturaCliente);
	listaNombresEventos.add (this.clickComboEditCli);
	listaNombresEventos.add (this.clickComboFactCli);
	listaNombresEventos.add (this.clickComboFactEdit);
	 
		
	}
	
	public int getNumEvento (String nombreEvento)
	{
		System.out.println(nombreEvento);
		return listaNombresEventos.indexOf(nombreEvento);
	}
	
	public static void mostrarLista ()
	{
		for (int i = 0; (i < listaNombresEventos.size()); i++)
		{
			System.out.println(i + " = " + listaNombresEventos.get(i).toString());
		}
		
	}
	
	

}
