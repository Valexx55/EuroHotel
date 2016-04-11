/*
 * FormularioInsCliente.java
 *
 * Created on 14 de enero de 2008, 17:49
 */

package vista;

import java.awt.Font;

import modelo.Cliente;
import controlador.EscuchaEventos;
import controlador.Eventos;


@SuppressWarnings("all")
public class FormularioInsCliente extends javax.swing.JDialog {
    

	public static String campoDni = "DNI";
	public static String campoNombre = "NOMBRE";
	public static String campoApellidos = "APE";
	public static String campoFechaNacDia = "FECNACDIA";
	public static String campoFechaNacMes = "FECNACMES";
	public static String campoFechaNacAnio = "FECNACANIO";
	public static String campoFechaExpDia = "FECEXPDIA";
	public static String campoFechaExpMes = "FECEXPMES";
	public static String campoFechaExpAnio = "FECEXPANIO";
	public static String campoDireccion = "DIRECC";
	public static String campoLocalidad = "LOCAL";
	public static String campoCodPostal = "CP";
	public static String campoProvincia = "PROV";
	public static String campoPais = "PAIS";
	public static String campoEmail = "EMAIL";
	public static String campoTelefono = "TELF";
	public static String campoSexo = "SEX";
	
	private static FormularioInsCliente fins = new FormularioInsCliente (new MenuPpal(), true);
	
	/**
	 * 
	 * @return un booleno que indica si el formulario está vacío
	 */
	public static boolean estaVacio ()
	{
		return	((cajaTextoDni.getText().isEmpty()) && (cajaTextoNombre.getText().isEmpty()) && (cajaTextoApellidos.getText().isEmpty()) && (cajaTextoDiaNac.getText().isEmpty()) && (cajaTextoMesNac.getText().isEmpty()) && (cajaTextoAnioNac.getText().isEmpty()) && (cajaTextoDiaExp.getText().isEmpty()) && (cajaTextoMesExp.getText().isEmpty()) && (cajaTextoAnioExp.getText().isEmpty()) && (cajaTextoCP.getText().isEmpty()) && (cajaTextoPais.getText().isEmpty()) && (cajaTextoEmail.getText().isEmpty()) && (cajaTextoTelefono.getText().isEmpty()) && (cajaTextoLocalidad.getText().isEmpty()) &&(cajaTextoProvincia.getText().isEmpty()) && (cajaTextoDireccion.getText().isEmpty())); 
	}
	
	public static FormularioInsCliente getInstance ()
	{
		return fins;
	}
	
	public static void limpiarVentana ()
	{
		cajaTextoDni.setText("");
		cajaTextoNombre.setText(""); 
		cajaTextoApellidos.setText(""); 
		cajaTextoDiaNac.setText(""); 
		cajaTextoMesNac.setText(""); 
		cajaTextoAnioNac.setText(""); 
		cajaTextoDiaExp.setText(""); 
		cajaTextoMesExp.setText(""); 
		cajaTextoAnioExp.setText(""); 
		cajaTextoCP.setText("");
		cajaTextoPais.setText("");
		cajaTextoEmail.setText("");
		cajaTextoTelefono.setText("");
		cajaTextoLocalidad.setText("");
		cajaTextoProvincia.setText("");
		cajaTextoDireccion.setText("");
	
	
	}
	
	public void visibilizar (boolean visible)
	{
		this.setVisible(visible);
	}
		
		
	
	public static String getCampo (String campo)
	{
		
		if (campo.equals(FormularioInsCliente.campoDni)) return (cajaTextoDni.getText());
		if (campo.equals(FormularioInsCliente.campoNombre)) return (cajaTextoNombre.getText()); 
		if (campo.equals(FormularioInsCliente.campoApellidos)) return (cajaTextoApellidos.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaNacDia)) return (cajaTextoDiaNac.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaNacMes)) return (cajaTextoMesNac.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaNacAnio)) return (cajaTextoAnioNac.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaExpDia)) return (cajaTextoDiaExp.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaExpMes)) return (cajaTextoMesExp.getText()); 
		if (campo.equals(FormularioInsCliente.campoFechaExpAnio)) return (cajaTextoAnioExp.getText()); 
		if (campo.equals(FormularioInsCliente.campoCodPostal)) return (cajaTextoCP.getText()); 
		if (campo.equals(FormularioInsCliente.campoPais)) return (cajaTextoPais.getText());
		if (campo.equals(FormularioInsCliente.campoEmail)) return (cajaTextoEmail.getText());
		if (campo.equals(FormularioInsCliente.campoTelefono)) return (cajaTextoTelefono.getText());
		if (campo.equals(FormularioInsCliente.campoSexo)) return (botonSexo.getSelectedItem().toString());
		if (campo.equals(FormularioInsCliente.campoLocalidad)) return (cajaTextoLocalidad.getText());
		if (campo.equals(FormularioInsCliente.campoProvincia)) return (cajaTextoProvincia.getText());
		if (campo.equals(FormularioInsCliente.campoDireccion)) return (cajaTextoDireccion.getText());
		return null; //esta instrucción no tiene sentido es por el puto IDE
	}
	
	public static void MostrarCliente (Cliente cliente)
	{
		
		
		cajaTextoAnioExp.setText(String.valueOf (cliente.obtenerAnio(cliente.getFexp())));
		cajaTextoAnioNac.setText (String.valueOf (cliente.obtenerAnio(cliente.getFnac())));
		cajaTextoApellidos.setText (cliente.getApellidos());
		cajaTextoCP.setText (cliente.getCp());
		cajaTextoDiaExp.setText (String.valueOf (cliente.obtenerDia(cliente.getFexp())));
		cajaTextoDiaNac.setText (String.valueOf (cliente.obtenerDia(cliente.getFnac())));
		cajaTextoDireccion.setText (cliente.getDireccion());
		cajaTextoDni.setText (cliente.getDni());
		cajaTextoEmail.setText (cliente.getEmail());
		cajaTextoLocalidad.setText (cliente.getLocalidad());
		cajaTextoMesExp.setText (String.valueOf (cliente.obtenerMes(cliente.getFexp())));
		cajaTextoMesNac.setText (String.valueOf (cliente.obtenerMes(cliente.getFnac())));
		cajaTextoNombre.setText (cliente.getNombre());
		cajaTextoPais.setText (cliente.getPais());
		cajaTextoProvincia.setText (cliente.getProvincia());
		cajaTextoTelefono.setText (cliente.getTelefono());
		botonSexo.setSelectedItem(cliente.getSexo());
		
	}
	
    /** Creates new form FormularioInsCliente */
    private FormularioInsCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiarVentana();
        this.setName(Eventos.FormularioCliente);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cajaTextoDni = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cajaTextoNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cajaTextoApellidos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        botonSexo = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cajaTextoDiaNac = new javax.swing.JTextField();
        cajaTextoMesNac = new javax.swing.JTextField();
        cajaTextoAnioNac = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cajaTextoDiaExp = new javax.swing.JTextField();
        cajaTextoMesExp = new javax.swing.JTextField();
        cajaTextoAnioExp = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        cajaTextoDireccion = new javax.swing.JTextField();
        cajaTextoLocalidad = new javax.swing.JTextField();
        cajaTextoCP = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        cajaTextoProvincia = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        cajaTextoPais = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cajaTextoEmail = new javax.swing.JTextField();
        cajaTextoTelefono = new javax.swing.JTextField();
        aceptarCliente = new javax.swing.JButton();
        //imprimirCliente = new javax.swing.JButton();
        cancelarCliente = new javax.swing.JButton();
        eliminarCliente = new javax.swing.JButton();

        addWindowListener(EscuchaEventos.getInstanceEscuchaEventos());
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Ficha Cliente");
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        jPanel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DNI/CIF");
        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        cajaTextoDni.setName(Eventos.cajaTextoDni);
        cajaTextoDni.addFocusListener(EscuchaEventos.getInstanceEscuchaEventos());

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nombre");
        jLabel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Apellidos");
        jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoApellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoApellidos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Sexo");
        jLabel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        botonSexo.setFont(new java.awt.Font("Tahoma", 0, 12));
        botonSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "M" }));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Fecha nac");
        jLabel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoDiaNac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoDiaNac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoMesNac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoMesNac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoAnioNac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoAnioNac.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha exp");
        jLabel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoDiaExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoDiaExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoMesExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoMesExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoAnioExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoAnioExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(cajaTextoDni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(13, 13, 13)
                        .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cajaTextoNombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel5Layout.createSequentialGroup()
                                .add(cajaTextoDiaNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cajaTextoMesNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cajaTextoAnioNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(15, 15, 15)
                                .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cajaTextoDiaExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(cajaTextoApellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(6, 6, 6)
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5Layout.createSequentialGroup()
                                .add(cajaTextoMesExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(cajaTextoAnioExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5Layout.createSequentialGroup()
                                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(botonSexo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .add(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(cajaTextoDni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10)
                    .add(cajaTextoNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(16, 16, 16)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(cajaTextoApellidos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14)
                    .add(botonSexo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(cajaTextoDiaNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoMesNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoAnioNac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel12)
                    .add(cajaTextoDiaExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoMesExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoAnioExp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(26, 26, 26))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Domicilio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        jPanel11.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel44.setBackground(new java.awt.Color(255, 255, 255));
        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Direcci\u00f3n");
        jLabel44.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        jLabel45.setBackground(new java.awt.Color(255, 255, 255));
        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Localidad");
        jLabel45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Provincia");
        jLabel46.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoLocalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoLocalidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoCP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoCP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel47.setBackground(new java.awt.Color(255, 255, 255));
        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("C P");
        jLabel47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoProvincia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoProvincia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel48.setBackground(new java.awt.Color(255, 255, 255));
        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Pa\u00eds");
        jLabel48.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoPais.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoPais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cajaTextoDireccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cajaTextoProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 39, Short.MAX_VALUE)
                        .add(jLabel48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(12, 12, 12)
                        .add(cajaTextoPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cajaTextoLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(jLabel47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cajaTextoCP, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
                .add(12, 12, 12))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel44)
                    .add(cajaTextoDireccion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel47)
                    .add(cajaTextoCP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoLocalidad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel45))
                .add(14, 14, 14)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel46)
                    .add(cajaTextoProvincia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoPais, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel48))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Datos de contacto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        jPanel14.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("E-mail");
        jLabel53.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Tel\u00e9fono");
        jLabel54.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        cajaTextoEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        cajaTextoTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cajaTextoTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel14Layout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(jLabel53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel54, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cajaTextoEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 278, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cajaTextoTelefono, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(87, 87, 87))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel53)
                    .add(cajaTextoEmail, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(16, 16, 16)
                .add(jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel54)
                    .add(cajaTextoTelefono, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        aceptarCliente.setText("Aceptar");
        aceptarCliente.setName(Eventos.aceptarFichaCliente);
        aceptarCliente.addMouseListener (EscuchaEventos.getInstanceEscuchaEventos());

        /*imprimirCliente.setText("Imprimir");
        imprimirCliente.setName(Eventos.imprimirFichaCliente);
        imprimirCliente.addMouseListener (EscuchaEventos.getInstanceEscuchaEventos());
	*/
        cancelarCliente.setText("Cancelar");
        cancelarCliente.setName(Eventos.cancelarFichaCliente);
        cancelarCliente.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());
        
        eliminarCliente.setText("Eliminar");
        eliminarCliente.setName(Eventos.eliminarFichaCliente);
        eliminarCliente.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());
        
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(43, 43, 43)) //43, 43, 43
            .add(layout.createSequentialGroup()
            	.add(150, 150, 150) //por el efecto que provoca, parece que esta es la posición inicial de la fila de botones
                .add(aceptarCliente)
                .add(15, 15, 15)
                .add(cancelarCliente)
                .add(17, 17, 17)
                .add(eliminarCliente)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 152, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 139, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                	.add(aceptarCliente)
                    .add(cancelarCliente)
                    .add(eliminarCliente))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    
    /*public leerCliente ()
    {
    	
    	 
    	    private javax.swing.JComboBox jComboBox1;
    	    private javax.swing.JLabel jLabel10;
    	    private javax.swing.JLabel jLabel11;
    	    private javax.swing.JLabel jLabel12;
    	    private javax.swing.JLabel jLabel13;
    	    private javax.swing.JLabel jLabel14;
    	    private javax.swing.JLabel jLabel44;
    	    private javax.swing.JLabel jLabel45;
    	    private javax.swing.JLabel jLabel46;
    	    private javax.swing.JLabel jLabel47;
    	    private javax.swing.JLabel jLabel48;
    	    private javax.swing.JLabel jLabel53;
    	    private javax.swing.JLabel jLabel54;
    	    private javax.swing.JLabel jLabel9;
    	    private javax.swing.JPanel jPanel11;
    	    private javax.swing.JPanel jPanel14;
    	    private javax.swing.JPanel jPanel5;
    	    private javax.swing.JTextField cajaTextoDni;
    	    private javax.swing.JTextField jTextField2;
    	    private javax.swing.JTextField jTextField3;
    	    private javax.swing.JTextField jTextField4;
    	    private javax.swing.JTextField jTextField5;
    	    private javax.swing.JTextField jTextField59;
    	    private javax.swing.JTextField jTextField6;
    	    private javax.swing.JTextField jTextField60;
    	    private javax.swing.JTextField jTextField61;
    	    private javax.swing.JTextField jTextField62;
    	    private javax.swing.JTextField jTextField63;
    	    private javax.swing.JTextField jTextField68;
    	    private javax.swing.JTextField jTextField69;
    	    private javax.swing.JTextField jTextField7;
    	    private javax.swing.JTextField jTextField8;
    	    private javax.swing.JTextField jTextField9;
    	
    	
    }*/
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton eliminarCliente;
    private static javax.swing.JButton aceptarCliente;
    //private static javax.swing.JButton imprimirCliente;
    private static javax.swing.JButton cancelarCliente;
    private static javax.swing.JComboBox botonSexo;
    private static javax.swing.JLabel jLabel10;
    private static javax.swing.JLabel jLabel11;
    private static javax.swing.JLabel jLabel12;
    private static javax.swing.JLabel jLabel13;
    private static javax.swing.JLabel jLabel14;
    private static javax.swing.JLabel jLabel44;
    private static javax.swing.JLabel jLabel45;
    private static javax.swing.JLabel jLabel46;
    private static javax.swing.JLabel jLabel47;
    private static javax.swing.JLabel jLabel48;
    private static javax.swing.JLabel jLabel53;
    private static javax.swing.JLabel jLabel54;
    private static javax.swing.JLabel jLabel9;
    private static javax.swing.JPanel jPanel11;
    private static javax.swing.JPanel jPanel14;
    private static javax.swing.JPanel jPanel5;
    private static javax.swing.JTextField cajaTextoDni;/*-*/
    private static javax.swing.JTextField cajaTextoNombre;/*-*/
    private static javax.swing.JTextField cajaTextoApellidos;/*-*/
    private static javax.swing.JTextField cajaTextoDiaNac;/*-*/
    private static javax.swing.JTextField cajaTextoMesNac;/*-*/
    private static javax.swing.JTextField cajaTextoDireccion;
    private static javax.swing.JTextField cajaTextoAnioNac;
    private static javax.swing.JTextField cajaTextoLocalidad;
    private static javax.swing.JTextField cajaTextoCP;
    private static javax.swing.JTextField cajaTextoProvincia;
    private static javax.swing.JTextField cajaTextoPais;
    private static javax.swing.JTextField cajaTextoEmail;
    private static javax.swing.JTextField cajaTextoTelefono;
    private static javax.swing.JTextField cajaTextoDiaExp;
    private static javax.swing.JTextField cajaTextoMesExp;
    private static javax.swing.JTextField cajaTextoAnioExp;
    // End of variables declaration//GEN-END:variables


	public static String getCampoDni() {
		return campoDni;
	}


	public static String getCampoNombre() {
		return campoNombre;
	}


	public static String getCampoApellidos() {
		return campoApellidos;
	}


	public static String getCampoFechaNacDia() {
		return campoFechaNacDia;
	}


	public static String getCampoFechaNacMes() {
		return campoFechaNacMes;
	}


	public static String getCampoFechaNacAnio() {
		return campoFechaNacAnio;
	}


	public static String getCampoFechaExpDia() {
		return campoFechaExpDia;
	}


	public static String getCampoFechaExpMes() {
		return campoFechaExpMes;
	}


	public static String getCampoFechaExpAnio() {
		return campoFechaExpAnio;
	}


	public static String getCampoDireccion() {
		return campoDireccion;
	}


	public static String getCampoLocalidad() {
		return campoLocalidad;
	}


	public static String getCampoCodPostal() {
		return campoCodPostal;
	}


	public static String getCampoProvincia() {
		return campoProvincia;
	}


	public static String getCampoPais() {
		return campoPais;
	}


	public static String getCampoEmail() {
		return campoEmail;
	}


	public static String getCampoTelefono() {
		return campoTelefono;
	}
    
}
