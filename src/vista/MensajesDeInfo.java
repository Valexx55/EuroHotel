package vista;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MensajesDeInfo extends JOptionPane {
	
	
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	public static final int ModificacionCli = 0;
	public static final int EliminarCli = 1;
	public static final int CrearCli = 2;
	
	public static final int ModificacionFac = 3;
	public static final int EliminarFac = 4;
	public static final int CrearFac = 5;
	
	public static final int NEFac = 6;
	public static final int NECli = 7;
	public static final int OpCancelada = 8;
	public static final int Aborto = 9;
	public static final int OpExitosa = 10;
	public static final int FormCliVacio = 11;
	
	
	public static final int IFD = 12;
	public static final int IFI = 13;
	public static final int errorBD = 14;
	public static final int FormFacVacio = 15;
	
	public static final int errorCliFecha = -1;
	public static final int errorCliLocalidad = -2;
	public static final int errorCliCp = -3;
	public static final int errorCliPais = -4;
	public static final int errorCliNombre = -5;
	public static final int errorCliApellidos = -6;
	public static final int errorCliTelefono = -7;
	public static final int errorCliMail = -8;
	public static final int errorCliDni = -9;
	
	public static final int sinFacturas = -10;
	public static final int sinClientes = -11;
	
	
	
		
	private static final String MensajeAskModCli = "Cliente modificado, �desea actulizarlo?";
	private static final String MensajeAskDelCli = "�De verdad quiere eliminar al cliente?";
	private static final String MensajeAskInsCli = "Cliente nuevo, �desea almacenarlo?";
	
	
		
	private static final String MensajeAskModFra = "Factura modificada, �desea actulizarla?";
	private static final String MensajeAskDelFra = "�De verdad quiere eliminar la factura?";
	private static final String MensajeAskInsFra = "Factura nueva, �desea almacenarla?";
	
	private static final String MensajeNEFra = "\t"+"Factura inexsistente";
	private static final String MensajeNECli = "El cliente introducido no existe";
	private static final String MensajeOpCancelada = "Operaci�n cancelada";
	private static final String MensajeOpExitosa = "Operaci�n realizada con �xito";
	private static final String MensajeAborto = "Error fatal. El programa debe finalizar";
	private static final String MensajeFomularioVacio = "No puede guardar un formulario vac�o";
	
	private static final String MensajeFormFacVacio = "No puede guardar una factura vac�a";
	
	private static final String MensajeIFI = "Error formato importe (use punto para decimales)";
	private static final String MensajeIFD = "La descripci�n no puede estar vac�a";
	private static final String MensajeErrorBD = "Error de base de datos. Operaci�n No realizada "; 
	
	private static final String MensajeErrorApellidos = "Formato apellidos incorrecto";
	private static final String MensajeErrorNoFact = "No hay ninguna factura almacenada";
	private static final String MensajeErrorCli = "No hay ning�n cliente almacenado";
	
	private static final String MensajeErrorCP = "Formato c�digo postal incorrecto";
	private static final String MensajeErrorFecha = "Formato de fechas incorrecto";
	private static final String MensajeErrorLocalidad = "Formato Localidad incorrecto";
	private static final String MensajeErrorMail = "Formato E-mail incorrecto";
	private static final String MensajeErrorNombre = "Formato Nombre incorrecto";
	private static final String MensajeErrorPais = "Formato Pa�s incorrecto";
	private static final String MensajeErrorTelf = "Formato tel�fono incorrecto";
	private static final String MensajeErrorDni = "Formato DNI  incorrecto";
	
	
	
	private static final String tituloConfir = "Mensaje de confirmaci�n";
	private static final String tituloInfor = "Mensaje de informaci�n";
	
	
		
	
	/**
	 * La funci�n muestra un cuadro de di�logo que solitica la aprobaci�n
	 * del usuario para realizar determinada operaci�n
	 * 
	 * @param ventanaPadre Indica la ventana sobre la que aparecer� �sta
	 * @param tConfirmacion indica qu� tipo de confirmaci�n se solicita, si bien
	 * es de creaci�n, modificaci�n o eliminaci�n y sobre que tipo de objeto
	 * factura, o cliente
	 * @return 0 = No 1 = Si -1 = Cerrar / cancelar
	 */
	public static int pedirConfirmacion (Component ventanaPadre, int tConfirmacion)
	{
		
		ArrayList<String> botones = new ArrayList<String> ();
		
		
		botones.add("No"); //la opci�n 0
		botones.add("S�"); // la opci�n 1
		
		String mensaje = null;
		int opcion = 0; 
		
		switch (tConfirmacion) {
			case ModificacionCli:
				mensaje = MensajeAskModCli ;
			break;

			case ModificacionFac:
				mensaje = MensajeAskModFra;
			break;
			case CrearCli:
				mensaje = MensajeAskInsCli ;
			break;
			case CrearFac:
				mensaje = MensajeAskInsFra;
			break;
			case EliminarCli:
				mensaje = MensajeAskDelCli;
			break;
			case EliminarFac:
				mensaje = MensajeAskDelFra;
			break;
			
			

			default:
				/*logear el error*/
			break;
		}
		
		opcion = JOptionPane.showOptionDialog(ventanaPadre, mensaje, tituloConfir, DEFAULT_OPTION, INFORMATION_MESSAGE, null, botones.toArray(), (Object) botones.get(1));
		return opcion;
	}
	
	
	/**
	 * La funci�n muestra un cuadro de di�logo que informa al usuario
	 * del resultado de alguna operaci�n
	 * 
	 * @param ventanaPadre Indica la ventana sobre la que aparecer� �sta y de la cual
	 * hereder� su formato y estilo
	 * @param tInformacion Indica qu� tipo de informaci�n se solicita mostrar (constante de la misma clase)
	 * 
	 */
	public static void informar (Component ventanaPadre, int tInformacion)
	{
		
		ArrayList<String> botones = new ArrayList<String> ();
		
		
		botones.add("Aceptar"); //la opci�n 0
				
		String mensaje = null;
		int tmensaje = 0;
		
		switch (tInformacion) {
		
		
			case sinClientes:
				mensaje = MensajeErrorCli;
				tmensaje = INFORMATION_MESSAGE;	
			break;
			case sinFacturas:
				mensaje = MensajeErrorNoFact;
				tmensaje = INFORMATION_MESSAGE;	
			break;
			case errorCliApellidos:
				mensaje = MensajeErrorApellidos;
				tmensaje = ERROR_MESSAGE;	
				break;
			case errorCliCp:
				mensaje = MensajeErrorCP;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliFecha:
				mensaje = MensajeErrorFecha;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliLocalidad:
				mensaje = MensajeErrorLocalidad;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliMail:
				mensaje = MensajeErrorMail;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliNombre:
				mensaje = MensajeErrorNombre;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliPais:
				mensaje = MensajeErrorPais;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorCliTelefono:
				mensaje = MensajeErrorTelf;
				tmensaje = ERROR_MESSAGE;
				break;
			
			case errorCliDni:
				mensaje = MensajeErrorDni;
				tmensaje = ERROR_MESSAGE;
				break;
			case Aborto:
				mensaje = MensajeAborto;
				tmensaje = ERROR_MESSAGE;
			break;
			case NECli:
				mensaje = MensajeNECli;
				tmensaje = INFORMATION_MESSAGE;
			break;

			case NEFac:
				mensaje = MensajeNEFra;
				tmensaje = INFORMATION_MESSAGE;
			break;
			case OpCancelada:
				mensaje = MensajeOpCancelada;
				tmensaje = ERROR_MESSAGE;
			break;
			case OpExitosa:
				mensaje = MensajeOpExitosa;
				tmensaje = INFORMATION_MESSAGE;
				break;
			case FormCliVacio:
				mensaje = MensajeFomularioVacio;
				tmensaje = INFORMATION_MESSAGE;
				break;
			case IFD:
				mensaje = MensajeIFD;
				tmensaje = ERROR_MESSAGE;
				break;
			case IFI:
				mensaje = MensajeIFI;
				tmensaje = ERROR_MESSAGE;
				break;
			case errorBD:
				mensaje = MensajeErrorBD;
				tmensaje = ERROR_MESSAGE;
				break;
			case FormFacVacio:
				mensaje = MensajeFormFacVacio;
				tmensaje = ERROR_MESSAGE;
			break;
				
			default:
				/*logear el error*/
			break;
		}
		
		JOptionPane.showOptionDialog(ventanaPadre, mensaje, tituloInfor, DEFAULT_OPTION, tmensaje, null, botones.toArray(), (Object) botones.get(0));
		
	}
	
	
}
