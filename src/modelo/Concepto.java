package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import controlador.MisExcepciones;
import controlador.SesionBD;

@SuppressWarnings("all")
public class Concepto {
	
	
	private Float limporte;
	private String strDescripcion;
	
	public Concepto (String importe, String descripcion)
	{
		limporte = new Float(importe);
		strDescripcion = descripcion;
	}
	
	public Float getLimporte() {
		return limporte;
	}
	public void setLimporte(Float limporte) {
		this.limporte = limporte;
	}
	public String getStrDescripcion() {
		return strDescripcion;
	}
	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}
	
	/*public ArrayList<Concepto> obtenerConcepto (String idFactura)
	{
		
		
	}*/
	
	//DESCRIPCION IMPORTE ORDEN FACTURA
	public static boolean escribirConceptos (Vector<Concepto> vConceptos, Long nFactura)
	{
		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;
		boolean boolDev = true;
		Concepto concept = null;
		
		conex = sesion.getConexion();
		try {
			statement = conex.createStatement();
		
			for (int i = 0; i< vConceptos.size();i++)
			{
				concept = (Concepto) vConceptos.get(i);
				statement.executeUpdate("INSERT INTO CONCEPTO (DESCRIPCION, IMPORTE, ORDEN, FACTURA) " + 	"VALUES ('"+ concept.strDescripcion+"',"+ concept.limporte+","+ i +","+ nFactura+")");
				conex.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MisExcepciones.RegistrarExcepcion("Error al insertar un concepto ", false);
			boolDev = false;
			}
		finally{
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
	
	public Concepto (ResultSet rs)
	{
		try
		{
		this.strDescripcion = rs.getString("DESCRIPCION");
		this.limporte = rs.getFloat("IMPORTE");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Vector<Concepto> obtenerConceptos (Long nFactura)
	{
		Vector<Concepto> vconcepto = new Vector<Concepto> ();
		SesionBD sesion = SesionBD.getSesion();
		ResultSet rs = null;
		Connection conex = null;
		Statement statement = null;
		String strQ = "Select * from CONCEPTO where CONCEPTO.FACTURA = " +nFactura+ " ORDER BY ORDEN ASC";

		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			rs = statement.executeQuery(strQ);
			while (rs.next())
				{
				vconcepto.add(new Concepto (rs));
				}
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al comprobar si un cliente ya existe ", false);
			return null;
		}
		
		finally 
			{
				SesionBD.liberarRecursos(rs, statement);
			}
		return vconcepto;
		}

}
