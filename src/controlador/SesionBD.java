package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SesionBD {

	private Connection conexion;
	private static SesionBD sesion = new SesionBD();
	private String cadenaConexion = "jdbc:odbc:dbeurocli";
	
		
	
	public static SesionBD  getSesion ()
	{
		return sesion;
	}
	
	private SesionBD() {
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		conexion = DriverManager.getConnection(cadenaConexion);
		} catch (Exception e)
		{
			MisExcepciones.RegistrarExcepcion("Error al iniciar conexiones con la bd", true);
		}
	}
	
		
	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public ResultSet recuperarTodosLosDni ()
	{
		final String consultaDnis = "SELECT DNI FROM CLIENTE";
		
		Statement st;
		try {
			st = conexion.createStatement();
			return (st.executeQuery(consultaDnis));
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al recuperar los dni", true);
			return null; /*esto es por el puto compilador...no se llegará a ejecutar nunca*/
		}
	}
		
	public ResultSet recuperarFactura (Long numFactura)
	{
		final String consultaUnaFact = "SELECT * FROM FACTURA WHERE FACTURA.NUMERO = " +numFactura ;
		
		Statement st;
		try {
			st = conexion.createStatement();
			return (st.executeQuery(consultaUnaFact));
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al recuperar las factura " + numFactura, true);
			return null; /*esto es por el puto compilador...no se llegará a ejecutar nunca*/
	}
		
	}
			
			
	
	
	
	
	public static void liberarRecursos (ResultSet rs, Statement st)
	
	{
		try 
			{	if (rs != null) rs.close();
				if (st != null) st.close();
								}
		catch (Exception e) 
			{
				e.printStackTrace();
				MisExcepciones.RegistrarExcepcion("Error al liberar recursos", false);
			}
	}
	
} //llave finde clase
	//CONVIENE USAR RESULTSET COMO VALOR DEVUELTO POR
	//LOS MÉTODOS QUE AQUÍ SE DEFINAN....
	// DEFINIR MÉTODOS Y ARGUMENTOS SEGÚN NECEISDADES
	// Y DEVOLVERLOS EN UN RESULTSET

	
	

