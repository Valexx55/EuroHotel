package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import controlador.MisExcepciones;
import controlador.SesionBD;

import vista.FormularioInsCliente;
import vista.MensajesDeInfo;

@SuppressWarnings("all")
public class Cliente {
	
	private static final char caracterSepFecha = '-';
	
	private static final int minLonguitudFecha = 8;
	
	
	private String  strDni;
	private String dateFnac;
	private String dateFexp;
	private String strNombre;
	private String strApellidos;
	private String strDomicilio;
	private char charSexo;
	private String strTelefono; 
	private String strEmail;
	private String cp;
	private String pais;
	private String localidad;
	private String provincia;
	
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	//TODO ESTO TIENE QUE ESTAR EN OTRA CLASE !!!
	public static String componerFecha (String anio, String mes, String dia)
	{
		String fecha = null;
		
		fecha = (anio + caracterSepFecha + mes + caracterSepFecha + dia);
		if (fecha.length() < minLonguitudFecha) fecha = null;
		return fecha;
	}
	public static Vector<String> RecuperarListadoNIFs ()
	{
		Vector<String> listaDnis = null;
		ResultSet rs = null;
		Connection conexion = null;
		SesionBD sesionBD = null;
		Statement st = null;
		
		
		//TODO RECUERAR TODOS LOS CIFS DE LA TABLA CLIENTE Y DEVOLVERLOS EN UNA LISTA
		sesionBD = SesionBD.getSesion();
		conexion = sesionBD.getConexion();
		
		try {
			st = conexion.createStatement();
			String straux = "SELECT DNI FROM CLIENTE ORDER BY (DNI) ASC";
			rs = st.executeQuery(straux);
			if (rs.next()) {listaDnis = new Vector<String>();}
			do 
			{
				listaDnis.add(rs.getString("DNI"));
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
		
		return listaDnis;
	}
	/*
	 * sedeben niciar los campos a null, por si no se completan luego con ningún valor, 
	 * no de fallos en la inserción*/
	public Cliente ()
	{
		strDni = "";
		dateFnac = "";
		dateFexp = "";
		strNombre = "";
		strApellidos = "";
		strDomicilio = "";
		strTelefono = ""; 
		strEmail = "";
		cp = "";
		pais = "";
		localidad = "";
		provincia = "";
	}
	
	public Cliente (boolean valor)
	{
		strDni = "53130985-H";
		dateFnac = "20-24-1975";
		dateFexp = "20-24-1975";
		strNombre = "Valeriano";
		strApellidos = "Moreno de la Fuente García";
		strDomicilio = "Urb de la Fuentebella 456 3º A Apdo 3";
		strTelefono = "659884963"; 
		strEmail = "valerianomoreno@almnunos.uc3m.es";
		cp = "28985";
		pais = "España";
		localidad = "Guadalajara";
		provincia = "Almuñecar";
		
	}
	/**
	 * 
	 * @param cli
	 * @return devuelve verdadero si ambos objetos contienen idéntica información
	 */
	public boolean igual (Cliente cli)
	{
		return ((this.getApellidos().equals(cli.getApellidos())) && (this.getSexo() == cli.getSexo()) && (this.getCp().equals(cli.getCp())) && (this.getDireccion().equals(cli.getDireccion())) && (this.getDni().equals(cli.getDni())) && (this.getEmail().equals(cli.getEmail())) && (this.getFexp().equals(cli.getFexp())) && (this.getFnac().equals(cli.getFnac())) && (this.getLocalidad().equals(cli.getLocalidad())) && (this.getNombre().equals(cli.getNombre())) && (this.getPais().equals(cli.getPais())) && (this.getTelefono().equals(cli.getTelefono())));
	}
	public static Cliente capturarCliente ()
	
	{
		Cliente cli = new Cliente ();
		String fexp = null;
		String fnac = null;
				
		fexp = Cliente.componerFecha (FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaExpAnio), FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaExpMes), FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaExpDia)); 
		fnac = Cliente.componerFecha (FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaNacAnio), FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaNacMes), FormularioInsCliente.getCampo (FormularioInsCliente.campoFechaNacDia));
		
		
		cli.setApellidos (FormularioInsCliente.getCampo(FormularioInsCliente.campoApellidos));
		cli.setDireccion (FormularioInsCliente.getCampo (FormularioInsCliente.campoDireccion));
		cli.setEmail (FormularioInsCliente.getCampo (FormularioInsCliente.campoEmail));
		cli.setNombre (FormularioInsCliente.getCampo (FormularioInsCliente.campoNombre));
		cli.setFexp (fexp);
		cli.setFnac (fnac);
		cli.setTelefono (FormularioInsCliente.getCampo (FormularioInsCliente.campoTelefono));
		cli.setIntDni (FormularioInsCliente.getCampo (FormularioInsCliente.campoDni));
		cli.setCp (FormularioInsCliente.getCampo (FormularioInsCliente.campoCodPostal));
		cli.setPais (FormularioInsCliente.getCampo (FormularioInsCliente.campoPais));
		cli.setLocalidad (FormularioInsCliente.getCampo (FormularioInsCliente.campoLocalidad));
		cli.setSexo(FormularioInsCliente.getCampo (FormularioInsCliente.campoSexo).charAt(0));
		cli.setProvincia(FormularioInsCliente.getCampo(FormularioInsCliente.campoProvincia));
		
		return cli;
		
		
			
	}
	public boolean insertarCliente ()
	
	{

		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;
		boolean boolDev = true;
		

		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			//String fnacaux = this.getFnac();
			//String fexpaux = this.getFexp();
			String instr = "INSERT INTO CLIENTE (DNI, NOMBRE, APELLIDOS, DOMICILIO, FECNAC, FECEXP, SEXO, TELEFONO, EMAIL, CP, LOCALIDAD, PAIS, PROVINCIA) " + 	"VALUES ('"+ this.getDni()+"','"+ this.getNombre()+"','"+ this.getApellidos() +"','"+ this.getDireccion()+"','"+this.getFnac()+"','"+this.getFexp()+"','"+this.getSexo()+"','"+this.getTelefono()+"','"+this.getEmail()+"','"+this.getCp()+"','"+this.getLocalidad()+"','"+this.getPais()+"','"+this.getProvincia()+"')";
			statement.execute(instr);
			conex.commit();
		} catch (Exception e) {
			e.printStackTrace();
			MisExcepciones.RegistrarExcepcion("Error al insertar un cliente ", false);
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
	//TODO CAMBIAR DE CLASE
	public static String obtenerDia (String fecha) /*la fecha se recibe en formato DD-MM-AAAA*/
	{
		String dia = null;
		if (fecha != null)
			dia = obtenerCadenaNentreChar (3, caracterSepFecha , fecha);
		
		return dia;
		
	}
	
	public static String obtenerAnio (String fecha)
	{
		String anio = null;
		if (fecha != null)
				anio = obtenerCadenaNentreChar (1, caracterSepFecha , fecha);

		
		return anio;
		
	}
	
	private static String obtenerCadenaNentreChar (int n, char c, String cad)
	{
		String cadaux = "";
		int car = 0;
		int longi = cad.length();
		char caractual;
		int posinicio = 0;
		int pos = 1; //voy por la priemra subcadena
		boolean sigue = true;
		
		
		while (n != pos) //me posiciono en el primer caracter que compne la subdande devuelta
		{
			posinicio = cad.indexOf(c+"", posinicio);
			posinicio = posinicio + 1;
			pos ++;
		}
		car = posinicio;
		
		while ((car < longi)&& (sigue))
		{
			caractual = cad.charAt(car);
			sigue = (caractual != c);
			if (sigue)
				cadaux = cadaux + cad.charAt(car);
			car++;
		}
		return cadaux;
		
	}
	
	public static String obtenerMes (String fecha)
	{
		String mes = null;
		
		
		if (fecha != null)
			mes = obtenerCadenaNentreChar (2, caracterSepFecha , fecha);
		
		return mes;
	}
	//TODO CAMBIAR DE CLASE
	public static String convertirFechaAString (java.sql.Date fecha)
	{
		String fdev = null;
		Calendar calendario = Calendar.getInstance();
		Integer intdia = null;
		Integer intmes = null;
		Integer intanio = null;
		String strDia = null;
		String strMes = null;
		
		calendario.setTime(fecha);
		intmes = new Integer (calendario.get(Calendar.MONTH) + 1);
		intanio = new Integer (calendario.get(Calendar.YEAR));
		intdia = new Integer (calendario.get(Calendar.DATE));
		
		//hay que formatear el mes y el día, incluyendo un cero si fuera necesario
		
		strDia = intdia.toString();
		strMes = intmes.toString();
		
		if (strDia.length() == 1) strDia = "0" + strDia;
		if (strMes.length() == 1) strMes = "0" + strMes;

		fdev = intanio.toString() + caracterSepFecha +  strMes + caracterSepFecha + strDia;  
		
		return fdev;
	}
	
	public  Cliente (ResultSet rs)
	{
		try
			{
			setApellidos(rs.getString("APELLIDOS"));
			setDireccion(rs.getString("DOMICILIO"));
			setEmail(rs.getString("EMAIL"));
			setNombre(rs.getString("NOMBRE"));
			setFexp(rs.getString("FECEXP"));
			setFnac(rs.getString ("FECNAC"));
			setTelefono(rs.getString("TELEFONO"));
			setIntDni(rs.getString("DNI"));
			setCp(rs.getString("CP"));
			setPais(rs.getString("PAIS"));
			setLocalidad(rs.getString("LOCALIDAD"));
			setSexo(rs.getString("SEXO").charAt(0));
			setProvincia(rs.getString("PROVINCIA"));
			
			}
			catch (Exception e) {
				MisExcepciones.RegistrarExcepcion("Error al recuprar un cliente de la base de datos", false);
			}
	
	}
	
	public static Cliente RecuperarCliente (String Dni)
	{
	SesionBD sesion = SesionBD.getSesion();
	ResultSet rs = null;
	Connection conex = null;
	Statement statement = null;;

	try {
		conex = sesion.getConexion();
		statement = conex.createStatement();
		String straux = "Select * from CLIENTE where CLIENTE.DNI = '" +Dni+ "'";
		rs = statement.executeQuery(straux);
		if (rs.next())
			return (new Cliente (rs));
		else return null;
	} catch (SQLException e) {
		MisExcepciones.RegistrarExcepcion("Error al comprobar si un cliente ya existe ", false);
		return null;
	}
	
	finally 
		{
			SesionBD.liberarRecursos(rs, statement);
		}
	}
	
	public static boolean BorrarCliente (String Dni)
	{
		
		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;
		boolean bres = true;
		
		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			String straux = "DELETE FROM CLIENTE where CLIENTE.DNI = '" +Dni +"'";
			statement.execute(straux);
			conex.commit();
			
		} catch (SQLException e) {
			MisExcepciones.RegistrarExcepcion("Error al borrar el cliente con dni" +Dni, false);
			bres = false;
		}
		finally 
			{
				SesionBD.liberarRecursos(null, statement);
			}
		return bres;
	}
	
	public boolean actualizarCliente (String dniAnt)
	{
		SesionBD sesion = SesionBD.getSesion();
		Connection conex = null;
		Statement statement = null;
		boolean buldev = true;

		try {
			
			conex = sesion.getConexion();
			statement = conex.createStatement();
			this.getDni ();
			String straux = "UPDATE CLIENTE SET NOMBRE = '"+ this.getNombre()+"',APELLIDOS = '"+ this.getApellidos()+"',DOMICILIO= '"+ this.getDireccion()+"',FECNAC= '"+ this.getFnac()+"' ,FECEXP= '"+ this.getFexp()+"',SEXO= '"+ this.getSexo()+"',TELEFONO='"+ this.getTelefono()+"',EMAIL= '"+ this.getEmail()+"',CP= '"+ this.getCp()+"',LOCALIDAD= '"+ this.getLocalidad()+"',PAIS= '"+ this.getPais()+"',PROVINCIA= '"+ this.getProvincia()+"', DNI = '"+ this.getDni()+"' WHERE DNI =  '"+ dniAnt+"'";
			statement.execute(straux); 
			conex.commit();
		} catch (Exception e) {
			e.printStackTrace();
			MisExcepciones.RegistrarExcepcion("Error al insertar un cliente ", false);
			buldev =  false;
		}
		finally 
			{
					SesionBD.liberarRecursos(null, statement);
			}
		return buldev;
	}
	public static Cliente existeCliente (String Dni)
	{
		SesionBD sesion = SesionBD.getSesion();
		ResultSet rs = null;
		Connection conex = null;
		Statement statement = null;;
		
		Cliente cli = null;
		

		try {
			conex = sesion.getConexion();
			statement = conex.createStatement();
			String straux = "Select * from CLIENTE where CLIENTE.DNI = '" +Dni +"'";
			rs = statement.executeQuery(straux);
			if (rs.next())
				cli =  new Cliente(rs); 
			return cli;
		} catch (SQLException e) 
			{
			MisExcepciones.RegistrarExcepcion("Error al comprobar si un cliente ya existe ", false);
			return cli;
		}
		finally 
				{
					SesionBD.liberarRecursos(rs, statement);
				}
				
	}
	
	private boolean esUnaLetra (char i)
	{
		return ( ((i >= 'A') && (i <= 'Z')) || ((i >= 'a') && (i <= 'z')) || ((i == 'ñ') || (i == 'Ñ')|| (i == 'ú')|| (i == 'Ú')|| (i == 'ó')|| (i == 'Ó')|| (i == 'í')|| (i == 'Í')|| (i == 'é')|| (i == 'É')|| (i == 'á')|| (i == 'Á')));
	}
	
	private boolean esUnBlanco (char i)
	{
		return (i == ' ');
	}
	
	private boolean cadenaAlfabetica (String cad)
	{
		boolean vabene = true;
		
		if ((cad != null)|| (!cad.isEmpty()))
			for (int i = 0, longi = cad.length(); ((i < longi) && (vabene)); i++)
				vabene = (  esUnBlanco (cad.charAt(i)) || esUnaLetra (cad.charAt(i))); 
	
		return vabene;
		
	}
	
	private boolean esDivisible (int n, int porn)
	{
		return ((n % porn)==0);
	}
	
	private boolean anioBisiesto (int anio)
	{
		boolean abisiesto = true;
		
		if (esDivisible(anio, 4))
			{
				if (esDivisible(anio, 100))
					{
						abisiesto = esDivisible(anio, 400);
					}
			} else abisiesto = false;
		
		return abisiesto;
	}
	
	private boolean mes31 (int mes)
	{
		return ((mes == 1)||(mes == 3)||(mes == 5)||(mes == 7)||(mes == 8)||(mes == 10)||(mes == 12));
		
	}
	private boolean mes30 (int mes)
	{
		return ((mes == 4)||(mes == 6)||(mes == 9)||(mes == 11));
	}
	
	private boolean fechaCorrectaAB (int mes, int dia)
	{
		boolean correcto = false;
		
		if (mesCorrecto (mes))
		{
		if (mes30(mes))
		{
			correcto = (dia > 0) && (dia < 31);	
			}
		else if (mes31(mes))
			{
				correcto = (dia > 0) && (dia < 32);
			}
		else //estamos en feb bisiesto
		{
			correcto = (dia > 0) && (dia < 30);
		}
		}	 
		return correcto;
	}
	
	private boolean mesCorrecto (int mes)
	{
		return ((mes > 0) && (mes < 13));
	}
	private boolean fechaCorrectaANB (int mes, int dia)
	{
		boolean correcto = false;
		
		if (mesCorrecto (mes))
		{
			if (mes30(mes))
			{
			correcto = (dia > 0) && (dia < 31);	
			}
			else if (mes31(mes))
			{
				correcto = (dia > 0) && (dia < 32);
			}
				else //estamos en feb bisiesto
				{
					correcto = (dia > 0) && (dia < 29);
				}
		}	 
		return correcto;
	
	}
	
	private boolean fechaFormatoCorrecto (String Fecexp)
	{
		int anio = Integer.parseInt(Cliente.obtenerAnio(Fecexp));
		int mes = Integer.parseInt(Cliente.obtenerMes(Fecexp));
		int dia = Integer.parseInt(Cliente.obtenerDia(Fecexp));
		boolean fcorrecta = false;
		
		if (anioBisiesto(anio))
		{
			fcorrecta =  fechaCorrectaAB(mes, dia);
		} else
			{
			fcorrecta =  fechaCorrectaANB(mes, dia);
			}
		
		return (fcorrecta);
	}
	
	
	private boolean fechasValido (String Fecexp, String Fnac)
	{
		boolean fbien = true;
		int diaExp = 0;
		int diaNac = 0;
		int mesNac = 0;
		int mesExp = 0;
		int anioNac = 0;
		int anioExp = 0;
		boolean fecexpnull = false;
		boolean fecnacnull = false;
		boolean fecexpbien = true;
		boolean fecnacbien = true;
		Calendar calendarioNac = null;
		Calendar calendarioExp = null;
		
		
		try
		{
			if ((Fecexp != null) &&  (!Fecexp.isEmpty()))
			{
			mesExp = Integer.parseInt(Cliente.obtenerMes(Fecexp));
			anioExp = Integer.parseInt(Cliente.obtenerAnio(Fecexp));
			diaExp = Integer.parseInt(Cliente.obtenerDia(Fecexp));
			} else fecexpnull = true;
						
			if ((Fnac != null) && (!Fnac.isEmpty()))
			{
				mesNac = Integer.parseInt (Cliente.obtenerMes(Fnac));
				diaNac = Integer.parseInt (Cliente.obtenerDia(Fnac));
				anioNac = Integer.parseInt (Cliente.obtenerAnio(Fnac));
			} else fecnacnull = true;
			
		}
		catch (Exception e) {
			fbien = false;
			// TODO: handle exception
		}
		
		if (fbien)
		{
			if (!fecexpnull)
				{
				calendarioExp = Calendar.getInstance();
				calendarioExp.set(Calendar.MONTH, mesExp);
				calendarioExp.set(Calendar.DATE, diaExp);
				calendarioExp.set(Calendar.YEAR, anioExp);
				}
		
			if (!fecnacnull)
				{
				calendarioNac = Calendar.getInstance();
				calendarioNac.set(Calendar.MONTH, mesNac);
				calendarioNac.set(Calendar.DATE, diaNac);
				calendarioNac.set(Calendar.YEAR, anioNac);
				}
		
		
		if (!fecexpnull)
			{
			fecexpbien = (fechaFormatoCorrecto (Fecexp)); 
			}
		
		if (!fecnacnull)
		{
			fecnacbien = (fechaFormatoCorrecto (Fnac)); 
		}
		
		fbien = ((fecexpbien)&& (fecnacbien));
		
		
		if ((fbien) && (!fecexpnull)&& (!fecnacnull))
		 fbien = calendarioNac.before(calendarioExp);
		
		}
			
		return fbien;
	}
			
		
	private boolean cadenaNumerica (String cad)
	{
		boolean vabene = true;
		
		if ((cad != null)&& (!cad.isEmpty()))
			try{
				Long.parseLong(cad);
			}catch (Exception e)
			{
				vabene = false;
			}
	
		return vabene;
	}
	
	private boolean emailValido (String correo)
	{
		System.out.println(correo);//TODO BORRAR
		return ((correo == null) ||  (correo.isEmpty()) || correo.indexOf("@") != -1); //devuelve menos uno si no hay una arroba
	}
	private boolean dniValido (String strDni)
	{
		return (strDni != null) && (!strDni.isEmpty());
	}
	
	public int clienteValido ()
	{
	
		if (dniValido (this.getDni()))
			if (emailValido(this.getEmail()))
				if (cadenaNumerica(this.getTelefono()))
					if (cadenaAlfabetica(this.getApellidos()))
						if (cadenaAlfabetica(this.getNombre()))
							if (cadenaNumerica(this.getCp()))
								if (cadenaAlfabetica(this.getPais()))
									if (cadenaAlfabetica(this.getLocalidad()))
										if (fechasValido(this.getFexp(), this.getFnac()))
											return 0;
										else return MensajesDeInfo.errorCliFecha;
									else return MensajesDeInfo.errorCliLocalidad;
								else return MensajesDeInfo.errorCliPais;
							else return MensajesDeInfo.errorCliCp;
						else return MensajesDeInfo.errorCliNombre;
					else return MensajesDeInfo.errorCliApellidos;
				else return MensajesDeInfo.errorCliTelefono;
			else return MensajesDeInfo.errorCliMail;
		else return MensajesDeInfo.errorCliDni;
	}
	
	public String getDni() {
		return strDni;
	}
	public void setIntDni(String intDni) {
		this.strDni = intDni;
	}

	public String getFnac() {
		if (dateFnac == null) 
			dateFnac = "";
		return dateFnac;
	}
	public void setFnac (String dateFnac) {
		this.dateFnac = dateFnac;
	}
	public String getFexp() {
		if (dateFexp == null) 
			dateFexp = "";
		return dateFexp;
	}
	public void setFexp(String dateFexp) {
		this.dateFexp = dateFexp;
	}
	public String getNombre() {
		return strNombre;
	}
	
	public String getNombre4Impresion() {
		return strNombre+" "+strApellidos;
	}
	public void setNombre(String strNombre) {
		this.strNombre = strNombre;
	}
	
	public void setApellidos (String strApellidos) {
		this.strApellidos = strApellidos;
	}
	public String getApellidos() {
		return strApellidos;
	}
	
	public char getSexo() {
		return charSexo;
	}
	public void setSexo(char charSexo) {
		this.charSexo = charSexo;
	}
	public String getDireccion() {
		return strDomicilio;
	}
	
	public String getDireccion4Imprimir() {
		return strDomicilio + " " +cp+ " "+ localidad;
	}
	
	public void setDireccion(String strDireccion) {
		this.strDomicilio = strDireccion;
	}
	
	public String getTelefono() {
		return strTelefono;
	}
	
	public void setTelefono(String strTelefono) {
		this.strTelefono = strTelefono;
	}
	public String getEmail() {
		return strEmail;
	}
	public void setEmail(String strEmail) {
		this.strEmail = strEmail;
	}


	
	/*public void MostrarCliente ()
	{
		System.out.println("Apell " + this.getApellidos());
		System.out.println("CP " + this.getCp());
		System.out.println("Direcc " + this.getDireccion());
		System.out.println("DNI " + this.getDni());
		System.out.println("EMAIL " + this.getEmail() );
		System.out.println("LOCALIDAD " + this.getLocalidad());
		System.out.println("NOMBRE " + this.getNombre());
		System.out.println("PAIS " + this.getPais());
		System.out.println("PROVINCIA " + this.getProvincia());
		System.out.println("TELEFONO " + this.getTelefono());
		System.out.println("SEX " + this.getCharSexo());
		System.out.println("FEXP " + this.getFexp());
		System.out.println("FNAC " + this.getFnac());
			
	}*/
	
	
	
}
