package vista.imprimir;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;

import modelo.Cliente;
import modelo.Concepto;
import modelo.DatosImpresion;
import modelo.Factura;

public class Imprimir 

{

	
	
	

	public static Font fuenteDatosEmpresa1 = new Font("Dialog", Font.PLAIN, 12);
	public static Font fuenteDatosEmpresa2 = new Font("Dialog", Font.PLAIN, 10);
	public static Font fuenteDatosEmpresaNegrita = new Font("Dialog", Font.BOLD, 12);
	public static Font fuenteDatosCliente = new Font("Dialog", Font.PLAIN, 15);
	public static Font fuenteDatosFactura = new Font("Dialog", Font.PLAIN, 15);
	public static Font fuenteDatosFactura2 = new Font("timesi", Font.PLAIN, 12);
	
	private static ImageIcon iconoempresa = new ImageIcon ("\\imagenes\\logsl.jpg");
	//private static ImageIcon lineafactura = new ImageIcon ("\\imagenes\\lineafactura.jpg");
	
	
	private static int longImporte = 11;
	
	private static String componerImporte4Imprimir (String importe)
	{
		
		importe = importe + DatosImpresion.MonedaFactura;
		int longi = importe.length();
		
		while (longi < longImporte)
		{
			importe = " " + importe;
			longi++;
			
		}
		
		return importe;
	}
	
	public static void Factura (Factura factura)
	{
		///Cliente cliaux = Cliente.RecuperarCliente(factura.getTcliente());
		boolean tino = false;
		
		
		
		try 
		{
			PrintJob pj = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "ClienteEuromorada", null);
			
			Graphics pagina = pj.getGraphics();
			Cliente cliente = new Cliente(tino);
			/*cABECERA */
			pagina.setFont(fuenteDatosEmpresa1);
			
			pagina.drawString(DatosImpresion.NombreEmpresa, 10, 30);
			pagina.setFont(fuenteDatosEmpresa2);
			pagina.drawString(DatosImpresion.CIFEmpresa, 10, 45);
			pagina.drawString(DatosImpresion.DomicilioSocial, 10, 60);
			pagina.drawString(DatosImpresion.DomicilioSocial2, 10, 75);
			
			pagina.setFont(fuenteDatosEmpresa1);
			pagina.drawString(DatosImpresion.NombreEstablecimiento, 375, 30);
			pagina.setFont(fuenteDatosEmpresa2);
			pagina.drawString(DatosImpresion.DireccionEstablecimiento, 470, 45);
			pagina.drawString(DatosImpresion.DireccionEstablecimiento2, 450, 60);
			
			pagina.drawImage(iconoempresa.getImage(), 165, 15,null, null);
			
			
			
			pagina.setFont(fuenteDatosFactura2);
			pagina.drawString(DatosImpresion.NumeroFactura, 375, 165);
			pagina.drawString(factura.getLnumFactura().toString(), 460, 165);
			
			/*fin cABECERA */
			
			/*cLIENTE*/
					
			pagina.setFont(fuenteDatosEmpresa2);
			
			
			
			pagina.drawString(DatosImpresion.NombreCliente, 70, 220);
			pagina.drawString(cliente.getNombre4Impresion(), 135, 220);
			
			pagina.drawString(DatosImpresion.DNICliente, 70, 235);
			pagina.drawString(cliente.getDni(), 135, 235);
			
			pagina.drawString(DatosImpresion.DireccionCliente, 70, 250);
			pagina.drawString(cliente.getDireccion4Imprimir(), 135, 250);
			
			//pagina.drawImage(lineafactura.getImage(), 10, 270,null, null);//no sale la línea??
			
			pagina.setFont(fuenteDatosEmpresaNegrita);
			
			/*fin cLIENTE*/
			
			/*cONCETPOS*/
			pagina.drawString(DatosImpresion.ConceptoFactura, 135, 320);
			pagina.drawString(DatosImpresion.ImporteFactura, 420, 320);
			
			pagina.setFont(fuenteDatosFactura2);
			Vector<Concepto> listaConceptos = factura.getVListaConceptos4imprimir();
			int longi = listaConceptos.size();
			Concepto conceptoaux = null;
			int YconceptoInicial = 355;
			int YconceptoIncremento = 20;
			int Yconcepto = YconceptoInicial; 
			String strAuxImporte = null;
			
			for (int i = 0; i < longi ;i++)
			{
				conceptoaux = listaConceptos.get(i); 
				
				pagina.drawString(conceptoaux.getStrDescripcion(), 120, Yconcepto);
				strAuxImporte = componerImporte4Imprimir (conceptoaux.getLimporte().toString());
				pagina.drawString(strAuxImporte, 410, Yconcepto);
				
				Yconcepto = Yconcepto + YconceptoIncremento;
				
			}
			
			
			Yconcepto = Yconcepto + 35;
			pagina.setFont(fuenteDatosEmpresaNegrita);
			pagina.drawString(DatosImpresion.TotalFactura, 370, Yconcepto);
			strAuxImporte = componerImporte4Imprimir (factura.getImporte().toString());
			pagina.drawString(strAuxImporte, 420, Yconcepto);
			/*fin cONCETPOS*/
			
			/*Pie*/
			
			pagina.setFont(fuenteDatosFactura2);
			pagina.drawString(DatosImpresion.MensajePieFactura, 70, 740);
			pagina.drawString(DatosImpresion.MensajeComercial, 70, 760);
			pagina.drawString(DatosImpresion.TelefonoEmpresa, 70, 800);
			pagina.drawString(DatosImpresion.CorreoEmpresa, 200, 800);
			pagina.drawString(DatosImpresion.WebEmpresa, 380, 800);

			
			/*Pie*/
			
			

			
			
			pagina.dispose();
			pj.end();
		}
		catch (Exception e) {
			
		}

		
		
	}
	
	
		

}
