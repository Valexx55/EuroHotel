package modelo;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.BoldAction;

import controlador.MisExcepciones;
import controlador.SesionBD;

import vista.FormularioInsCliente;
import vista.FormularioInsFactura;

@SuppressWarnings("all")
public class Factura {
	
	private Long lnumFactura;
	private String fecexp;
	private String dniCliente;
	private Double dimporte;
	private Vector<Concepto> vListaConceptos;
	
	public static Vector<Long> RecuperarListadoFacturasEntreFechas ()
	{
		Vector<Long> listaFacturas = null;
		ResultSet rs = null;
		Connection conexion = null;
		SesionBD sesionBD = null;
		Statement st = null;
		
		Date d = new Date ();
		d.
		sesionBD = SesionBD.getSesion();
		conexion = sesionBD.getConexion();
		
		try {
			st = conexion.createStatement();
			String straux = "SELECT NUMERO FROM FACTURA ORDER BY (NUMERO) ASC";
			rs = st.executeQuery(straux);
			if (rs.next()) {listaFacturas = new Vector<Long>();}
			do 
			{
				listaFacturas.add(rs.getLong("NUMERO"));
			}while (rs.next());
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			
			try {
					SesionBD.liberarRecursos(rs, st);
					
				} catch (Exception e) {
					MisExcepciones.RegistrarExcepcion("Error al liberar recuros", false);
				}
		}
		
		return listaFacturas;
	}
	
	public static Vector<Long> RecuperarListadoFacturasEntreFechasNumeros (String numinf, String numsup)
	{
		Vector<Long> listaFacturas = null;
		ResultSet rs = null;
		Connection conexion = null;
		SesionBD sesionBD = null;
		Statement st = null;
		
		
		
		sesionBD = SesionBD.getSesion();
		conexion = sesionBD.getConexion();
		
		try {
			st = conexion.createStatement();
			long limInf = Long.parseLong(numinf);
			long limsup = Long.parseLong(numsup);
			 
			String straux = "SELECT * from factura where numero between "+numinf+" and "+numsup; 




			rs = st.executeQuery(straux);
			if (rs.next()) {listaFacturas = new Vector<Long>();}
			do 
			{
				listaFacturas.add(rs.getLong("NUMERO"));
			}while (rs.next());
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			
			try {
					SesionBD.liberarRecursos(rs, st);
					
				} catch (Exception e) {
					MisExcepciones.RegistrarExcepcion("Error al liberar recuros", false);
				}
		}
		
		return listaFacturas;
	}
	
	
	public static Vector<Long> RecuperarListadoFacturas ()
	{
		Vector<Long> listaFacturas = null;
		ResultSet rs = null;
		Connection conexion = null;
		SesionBD sesionBD = null;
		Statement st = null;
		
		
		sesionBD = SesionBD.getSesion();
		conexion = sesionBD.getConexion();
		
		try {
			st = conexion.createStatement();
			String straux = "SELECT NUMERO FROM FACTURA ORDER BY (NUMERO) ASC";
			rs = st.executeQuery(straux);
			if (rs.next()) {listaFacturas = new Vector<Long>();}
			do 
			{
				listaFacturas.add(rs.getLong("NUMERO"));
			}while (rs.next());
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			
			try {
					SesionBD.liberarRecursos(rs, st);
					
				} catch (Exception e) {
					MisExcepciones.RegistrarExcepcion("Error al liberar recuros", false);
				}
		}
		
		return listaFacturas;
	}
	
	public  boolean insertar ()
	
	{
		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;
		boolean boolDev = true;
		
		//NUMERO FEC-EXP IMPORTE DNI
		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			String sql = "INSERT INTO FACTURA (NUMERO, FECEXP, IMPORTE, DNI) VALUES ("+ this.getLnumFactura()+",'"+ this.getFecexp()+"',"+ this.getImporte() +",'"+ this.getTcliente()+"')";
			statement.execute(sql);
			Concepto.escribirConceptos(vListaConceptos, this.lnumFactura);
			conex.commit();
		} catch (Exception e) {
			e.printStackTrace();
			MisExcepciones.RegistrarExcepcion("Error al insertar una factura", false);
			boolDev = false;
		}
		finally 
			{
					if (statement != null)
						try
							{
							statement.close();
							}
							catch (Exception e) {
								MisExcepciones.RegistrarExcepcion("Error al liberar recursos", false);
							}
			}
		return boolDev;
		
	}
	public Factura ()
	{
		
	}
	//NUMERO FEC-EXP IMPORTE DNI
	public Factura (ResultSet rs)
	{
		
		try	
		{this.fecexp = rs.getString("FECEXP");
		this.dniCliente = rs.getString("DNI");
		this.dimporte = rs.getDouble("IMPORTE");
		this.lnumFactura = rs.getLong("NUMERO");
		this.vListaConceptos = Concepto.obtenerConceptos (this.lnumFactura);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	
	public Factura (boolean v)
	{
		
		try	
		{
		double impor = 30264.52;
		this.fecexp = "22-22-2002";
		this.dniCliente = "53130984-H";
		this.dimporte = impor; 
		this.lnumFactura = Long.parseLong("20090033");
		this.vListaConceptos = Concepto.obtenerConceptos (this.lnumFactura);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	
	
	public static Factura recuperar (Long numFactura)
	{
		SesionBD sesion = SesionBD.getSesion();
		ResultSet rs = null;
		Connection conex = null;
		Statement statement = null;;

		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			String straux = "Select * from FACTURA where FACTURA.NUMERO = " +numFactura;
			rs = statement.executeQuery(straux);
			if (rs.next())
				return (new Factura (rs));
			else return null;
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al comprobar si un cliente ya existe ", false);
			return null;	
		}
	}
	
	public static Factura capturarFactura ()
	
	{
		Factura factura = new Factura ();
		
		String fexp = null;
		
		fexp = Cliente.componerFecha (FormularioInsFactura.getCampo (FormularioInsFactura.campoFechaExpAnno).toString(), FormularioInsFactura.getCampo (FormularioInsFactura.campoFechaExpMes).toString(), FormularioInsFactura.getCampo (FormularioInsFactura.campoFechaExpDia).toString()); 
		
		factura.setFecexp(fexp);
		factura.setImporte(Double.parseDouble(FormularioInsFactura.getCampo(FormularioInsFactura.campoImporteTotal).toString()));
		factura.setLnumFactura (Long.parseLong(FormularioInsFactura.getCampo(FormularioInsFactura.campoNfactura).toString()));
		factura.setVListaConceptos ((Vector<Concepto>)(FormularioInsFactura.getCampo(FormularioInsFactura.campoConceptos)));
		factura.setTcliente(FormularioInsFactura.getCampo(FormularioInsFactura.campoDniCli).toString());
		
		return factura;
	}
	
	public static boolean BorrarFactura (Long Nfact)
	{
		
		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;;
		boolean bres = true;
		
		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			String straux = "DELETE FROM FACTURA where FACTURA.NUMERO = " +Nfact;
			statement.execute(straux);
			conex.commit();
			
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al borrar la factura nº " +Nfact, false);
			bres = false;
		}
		finally 
			{
				SesionBD.liberarRecursos(null, statement);
			}
		return bres;
	}
	
	public boolean actualizarFactura ()
	{//para evitar complicaciones y por motivos de tiempo, actualizar factura es borrarla y meterla de nuevo

		try {
			
			Factura.BorrarFactura(this.getLnumFactura());
			this.insertar(); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			MisExcepciones.RegistrarExcepcion("Error al insertar un cliente ", false);
			return false;
		}
	}
	
	public Long getLnumFactura() {
		return lnumFactura;
	}
	public void setLnumFactura(Long lnumFactura) {
		this.lnumFactura = lnumFactura;
	}
	public String getFecexp() {
		return fecexp;
	}
	public void setFecexp(String fecexp) {
		this.fecexp = fecexp;
	}
	public String getTcliente() {
		return dniCliente;
	}
	public void setTcliente(String tcliente) {
		this.dniCliente = tcliente;
	}
	public Double getImporte() {
		return dimporte;
	}
	public void setImporte(Double importe) {
		this.dimporte = importe;
	}
	
	public Vector<Concepto> getVListaConceptos4imprimir() {
		
		return (vListaConceptos);
		
	}
	
	public Vector getVListaConceptos() {
		
		Concepto concepto = null;
		Vector vDev = new Vector (vListaConceptos.size());
		
		
		for (int i = 0; i < vListaConceptos.size(); i++)
			{
				concepto =  vListaConceptos.get(i);
				Vector vectorAux = new Vector (2);
				vectorAux.add(concepto.getStrDescripcion());
				vectorAux.add(concepto.getLimporte());
				vDev.add(vectorAux);
			}
		return vDev;
	}
	public void setVListaConceptos(Vector<Concepto> listaConceptos) {
		vListaConceptos = listaConceptos;
	}
	public boolean igual (Factura f)
	{
		boolean booldev = false;
		Vector v1 = this.getVListaConceptos();
		Vector v1Aux = null; 
		Vector v2 = f.getVListaConceptos();
		Vector v2Aux = null;
		Concepto c1aux = null;
		Concepto c2aux = null;
		
		String ff1 = f.getFecexp();
		String ff2 = this.getFecexp();
		
		if (ff1.equals(ff2))
		{
		
		if (v1.size() ==  v2.size())
		{
			int j = 0;
			boolean sigue = true;
			while (j < v1.size() && sigue)
			{
				v1Aux = (Vector)v1.get(j);
				v2Aux = (Vector)v2.get(j);
				sigue = ((((String)v1Aux.get(0)).equals((String)v2Aux.get(0))) && 	(((Float)v1Aux.get(1)).floatValue() == ((Float)v2Aux.get(1)).floatValue())); 
				j++;
			}
			booldev = sigue;
			
			}
		}
		
		return booldev;
	}
	

}
