/*
 * FormularioInsFactura.java
 *
 * Created on 15 de enero de 2008, 15:30
 */

package vista;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import modelo.Cliente;
import modelo.Concepto;
import modelo.Factura;

import org.jdesktop.layout.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import com.sun.org.apache.regexp.internal.recompile;

import controlador.EscuchaEventos;
import controlador.Eventos;



/**
 *
 * @author  BH000125
 */



@SuppressWarnings("all")
public class FormularioInsFactura extends javax.swing.JDialog {
    
	
	public static boolean estaVacio ()
	{
		return (vFilas.size() == 0);
	}
	private static FormularioInsFactura finsfac = new FormularioInsFactura (new MenuPpal (), true);
	
	public static String campoNfactura = "NUMFACTURA";
	public static String campoDniCli = "DNICLI";
	public static String campoFechaExpDia = "FECEXPD";
	public static String campoFechaExpMes = "FECEXPM";
	public static String campoFechaExpAnno = "FECEXPA";
	public static String campoImporteTotal = "IMPORTETOTAL";
	public static String campoConceptos = "CONCEPTOS";
	
	
	public static void MostrarFactura (Factura factura)
	{
		
		dni.setText(factura.getTcliente());
		numeroFactura.setText(factura.getLnumFactura().toString());
		diaEmision.setText(Cliente.obtenerDia(factura.getFecexp()));
		mesEmision.setText(Cliente.obtenerMes(factura.getFecexp()));
		annoEmision.setText(Cliente.obtenerAnio(factura.getFecexp()));
		Cliente cliaux = Cliente.RecuperarCliente(factura.getTcliente());
		apellidoTitularFactura.setText(cliaux.getApellidos());
		nombreTitularFactura.setText(cliaux.getNombre());
		totalFactura.setText(factura.getImporte().toString());
		vFilas = factura.getVListaConceptos(); 
		RepintarPanelConceptos();
		
	}
	public static Object getCampo (String campo)
	{
		Vector <Concepto> v = new Vector<Concepto>();
		Vector vaux = null;
		
		if (campo.equals(FormularioInsFactura.campoNfactura)) return (numeroFactura.getText());
		if (campo.equals(FormularioInsFactura.campoDniCli)) return (dni.getText()); 
		if (campo.equals(FormularioInsFactura.campoFechaExpDia)) return (diaEmision.getText()); 
		if (campo.equals(FormularioInsFactura.campoFechaExpMes)) return (mesEmision.getText());
		if (campo.equals(FormularioInsFactura.campoFechaExpAnno)) return (annoEmision.getText());
		if (campo.equals(FormularioInsFactura.campoImporteTotal)) return (totalFactura.getText()); 
		if (campo.equals(FormularioInsFactura.campoConceptos)) 
			{
			Float importeAux = null;
			for (int i = 0; i < vFilas.size(); i++)
				{
				vaux = (Vector)vFilas.get(i);
				importeAux = (Float)vaux.get(1);
				v.add (new Concepto (importeAux.toString(), vaux.get(0).toString())); 
				}
				return v; 
			}
		
		return null; //esta instrucción no tiene sentido es por el puto IDE
	}
	public static FormularioInsFactura getInstance ()
	{
		return finsfac;
	}
	
	public int obtenerNumConceptoSeleccionado ()
	{
		return (tablaConceptos.getSelectedRow());
	}
	
	public void anniadirConcepto(Concepto concepto, int posicion)
	{
		Vector vectorAux = new Vector (2);
		
		vectorAux.add(concepto.getStrDescripcion());
		vectorAux.add(concepto.getLimporte());
		
		vFilas.remove(posicion);
		vFilas.add(posicion, vectorAux);
		
	}
	
	public void sumarYRestarYActualizarImporte (float importeAntiguo, float importeNuevo)
	{
		float nuevoValor;
		
		nuevoValor = Float.parseFloat(getTotalFactura()) + importeNuevo -importeAntiguo;
		setTotalFactura(String.valueOf(nuevoValor));
	}
	
	public void ponerConceptoEnEdicion (Concepto concept)
	{
		importeConcepto.setText(concept.getLimporte().toString());
		descripcionConcepto.setText(concept.getStrDescripcion());
	}
	
	public void eliminarConceptoSeleccionado ()
	{
		vFilas.remove(obtenerNumConceptoSeleccionado());
	}
	
	
	public Concepto obtenerConceptoSeleccionado ()
	{
		Concepto concept = null;
		String desc = null;
		Float importe = null; 
		
		TableModel tm = null;
		tm = tablaConceptos.getModel();
		desc = (String) tm.getValueAt (tablaConceptos.getSelectedRow(), 0);
		importe = (Float) tm.getValueAt (tablaConceptos.getSelectedRow(), 1);
		concept = new Concepto (importe.toString(), desc);
		
		return concept;
	}
	
	public static void RepintarPanelConceptos ()
	{
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		tablaConceptos = new TablaConceptos (vFilas, vConceptos);
		tablaConceptos.getColumn(nombreColumunaConcepto).setMaxWidth(260);
        tablaConceptos.getColumn(nombreColumunaConcepto).setMinWidth(260);
        tablaConceptos.getColumn(nombreColumunaImporte).setMaxWidth(60);
        tablaConceptos.getColumn(nombreColumunaImporte).setMinWidth(60);
        tablaConceptos.getColumn(nombreColumunaImporte).setCellRenderer(tcr);
        tablaConceptos.getColumn(nombreColumunaConcepto).setCellRenderer(tcr);
        tablaConceptos.setGridColor (new Color(102, 102, 255));
        tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPanelConceptos.setViewportView(tablaConceptos);
		scrollPanelConceptos.repaint();
		
		
	}
	
	
	
    /** Creates new form FormularioInsFactura */
    private FormularioInsFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //dibujarTablaConceptos(new Tabla (null, {"Nº", "Descripción", "Importe"}))
    }
    

    private void initComponents () {
        botonAceptarFactura = new javax.swing.JButton();
        botonCancelarFactura = new javax.swing.JButton();
        botonImprimirFactura = new javax.swing.JButton();
        botonEliminarFactura = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
       
        
        dni = new javax.swing.JTextField();
        apellidoTitularFactura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        nombreTitularFactura = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        numeroFactura = new javax.swing.JTextField();
        
        jLabel14 = new javax.swing.JLabel();
        diaEmision = new javax.swing.JTextField();
        mesEmision = new javax.swing.JTextField();
        annoEmision = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        totalFactura = new javax.swing.JTextField();
        euroLabel = new JLabel(); 
        euroLabel2 = new JLabel();
        panelConceptos = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        descripcionConcepto = new javax.swing.JTextField();
        importeConcepto = new javax.swing.JTextField();
        botonAniadir = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ficha Factura");
        botonAceptarFactura.setText("Aceptar");
        botonAceptarFactura.setName(Eventos.aceptarFichaFactura);
        botonAceptarFactura.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());

        botonCancelarFactura.setText("Cancelar");
        botonCancelarFactura.setName(Eventos.cancelarFichaFactura);
        botonCancelarFactura.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());

        botonImprimirFactura.setText("Imprimir");
        botonImprimirFactura.setName(Eventos.imprimirFichaFactura);
        botonImprimirFactura.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());
        
        
        botonEliminarFactura.setText("Eliminar");
        botonEliminarFactura.setName(Eventos.eliminarFactura);
        botonEliminarFactura.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Datos Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        jPanel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("DNI/CIF");
        jLabel55.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Nombre");
        jLabel56.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        dni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        dni.setEditable(false);

        apellidoTitularFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellidoTitularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        apellidoTitularFactura.setEditable(false);
        
        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Apellidos");
        jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        nombreTitularFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombreTitularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        nombreTitularFactura.setEditable(false);
        
        GroupLayout jPanel15Layout = new GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .add(26, 26, 26) //con esto se consigue determinar el margen izquierdo
                .add(jPanel15Layout.createParallelGroup(GroupLayout.TRAILING)
                    .add(jLabel55, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .add(jLabel11, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                .add(9, 9, 9) // la distancia entre las etiquitas de la izquierda y sus cjas de texto
                .add(jPanel15Layout.createParallelGroup(GroupLayout.LEADING)
                    .add(jPanel15Layout.createSequentialGroup()
                        .add(dni, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                        .add(22, 22, 22)
                        .add(jLabel56, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(nombreTitularFactura, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                    .add(apellidoTitularFactura, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel15Layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel55)
                    .add(dni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(jLabel56)
                    .add(nombreTitularFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(jPanel15Layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(apellidoTitularFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE)) //.addContainerGap(22, Short.MAX_VALUE))
        );
       

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Datos Factura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        jPanel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("N\u00famero");
        jLabel57.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        numeroFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numeroFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        numeroFactura.setEditable(false);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Fecha exp");
        jLabel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        diaEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diaEmision.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        mesEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mesEmision.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        annoEmision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        annoEmision.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Total");
        jLabel58.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        totalFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        totalFactura.setEditable(false);
        
        euroLabel.setText("\u20ac");
        euroLabel.setFont(new Font ("Arial", 14, 14));
        
        euroLabel2.setText("\u20ac");
        euroLabel2.setFont(new Font ("Arial", 14, 14));
        
        GroupLayout jPanel16Layout = new GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jPanel16Layout.createParallelGroup(GroupLayout.TRAILING)
                    .add(jLabel58, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .add(jPanel16Layout.createSequentialGroup()
                        .add(jLabel57, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .add(9, 9, 9)
                        .add(numeroFactura, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                        .add(37, 37, 37)
                        .add(jLabel14, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
                .add(16, 16, 16)
                .add(jPanel16Layout.createParallelGroup(GroupLayout.LEADING)
                    .add(jPanel16Layout.createSequentialGroup()
                        .add(diaEmision, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(mesEmision, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(annoEmision, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                    .add(jPanel16Layout.createSequentialGroup()
                        .add(totalFactura, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.RELATED)
                        .add(euroLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel16Layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel57)
                    .add(numeroFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14)
                    .add(diaEmision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(mesEmision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(annoEmision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                .add(jPanel16Layout.createParallelGroup(GroupLayout.BASELINE)
                    .add(jLabel58)
                    .add(totalFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .add(euroLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelConceptos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Conceptos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
        panelConceptos.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Descripci\u00f3n");
        jLabel53.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Importe");
        jLabel54.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));

        descripcionConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        descripcionConcepto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        importeConcepto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        importeConcepto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        
        

        //botonAniadir.setText("A\u00f1adir");
        botonAniadir.setText("A");
        botonAniadir.setToolTipText("Añadir concepto");
        botonAniadir.setFont(new Font("Tahoma",1, 13));
        botonAniadir.setForeground(new Color(102, 102, 255));
        botonAniadir.setName(Eventos.aniadirConceptoFactura);
        botonAniadir.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());

        //botonModificar.setText("Modificar");
        botonModificar.setText("M");
        botonModificar.setFont(new Font("Tahoma", 1, 13));
        botonModificar.setForeground(Color.GREEN);
        botonModificar.setToolTipText("Modificar concepto");
        botonModificar.setName(Eventos.modificarConceptoFactura);
        botonModificar.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());

        //botonBorrar.setText("Borrar");
        botonBorrar.setText("B");
        botonBorrar.setToolTipText("Borrar concepto");
        botonBorrar.setFont(new Font("Tahoma", 1, 13));
        botonBorrar.setForeground(Color.RED);
        botonBorrar.setName(Eventos.eliminarConceptoFactura);
        botonBorrar.addMouseListener(EscuchaEventos.getInstanceEscuchaEventos());
        
        vConceptos = new Vector ();
        vConceptos.add (nombreColumunaConcepto);
        vConceptos.add (nombreColumunaImporte);
        vFilas = new Vector ();
        
        /*vFilasImporte.add("c1");
        vFilasImporte.add("100");
        vFilasConcepto.add("c5");
        vFilasConcepto.add("500");
        vFilas.add (vFilasImporte);
        vFilas.add (vFilasImporte);
        vFilas.add (vFilasImporte);
        vFilas.add (vFilasImporte);
        vFilas.add (vFilasConcepto);
        vFilas.add (vFilasConcepto);
        vFilas.add (vFilasConcepto);*/
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		//table.getColumnModel().getColumn(column).setCellRe nderer(tcr);
        
        tablaConceptos = new TablaConceptos (vFilas, vConceptos);
        tablaConceptos.getColumn(nombreColumunaConcepto).setMaxWidth(260);
        tablaConceptos.getColumn(nombreColumunaConcepto).setMinWidth(260);
        tablaConceptos.getColumn(nombreColumunaImporte).setMaxWidth(60);
        tablaConceptos.getColumn(nombreColumunaImporte).setMinWidth(60);
        tablaConceptos.getColumn(nombreColumunaImporte).setCellRenderer(tcr);
        tablaConceptos.getColumn(nombreColumunaConcepto).setCellRenderer(tcr);
        //tablaConceptos.getColumn(nombreColumunaImporte).
        tablaConceptos.setGridColor (new Color(102, 102, 255));
        
        tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //scrollPanelConceptos = new JScrollPane (tablaConceptos);
        scrollPanelConceptos = new JScrollPane (tablaConceptos);
        
        JPanel jp = new JPanel ();
        jp.setSize(2, 2);
        jp.setBackground(new Color(102, 102, 255));
        scrollPanelConceptos.setCorner(JScrollPane.UPPER_RIGHT_CORNER, jp);
        scrollPanelConceptos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, false));
        //scrollPanelConceptos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelConceptos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelConceptos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       // scrollPanelConceptos.setSize(338, 20);
        //scrollPanelConceptos.setBorder (new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 1, true));
       // scrollPanelConceptos.

        GroupLayout panelConceptosLayout = new GroupLayout(panelConceptos);
        panelConceptos.setLayout(panelConceptosLayout);
        
        
        panelConceptosLayout.setHorizontalGroup(
        		panelConceptosLayout.createParallelGroup(GroupLayout.LEADING)
        		.add (panelConceptosLayout.createSequentialGroup()
        				.add( 26, 26, 26)
        				.add (panelConceptosLayout.createParallelGroup()
        						.add (jLabel53, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        						.add (jLabel54, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
        						.add(botonModificar, 50,50,50)
        						.add(botonAniadir, 50,50,50)
        						.add(botonBorrar, 50,50,50)
        					  )
        				.add (9, 9, 9)
        				.add (panelConceptosLayout.createParallelGroup()
        								.add (descripcionConcepto, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
        								.add (panelConceptosLayout.createSequentialGroup()
        										.add (importeConcepto, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        										.add (7, 7, 7)
        										.add(euroLabel2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        								)
        								.add (scrollPanelConceptos, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
        						)
        				.addContainerGap (30, Short.MAX_VALUE) //49
        				)
        );
        
        
        
        panelConceptosLayout.setVerticalGroup(
        		panelConceptosLayout.createParallelGroup(GroupLayout.LEADING)
        		.add(panelConceptosLayout.createSequentialGroup()
        				.addContainerGap()
        				
        				.add (panelConceptosLayout.createParallelGroup()
        						.add (jLabel54)
        						.add (importeConcepto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.add (euroLabel2)
        				)
        				.add (10, 10, 10)
        				.add (panelConceptosLayout.createParallelGroup(GroupLayout.LEADING)
        						.add(jLabel53)
        						.add(descripcionConcepto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				)
        				
        				.add (14, 14, 14)
        				.add (panelConceptosLayout.createParallelGroup()
        						.add (panelConceptosLayout.createSequentialGroup()
        								.add (botonAniadir)
                						.add (botonBorrar)
                						.add (botonModificar)
        								)
        						.add (scrollPanelConceptos)
        				)
        				.addContainerGap(37, Short.MAX_VALUE)
        			)
        		);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(botonAceptarFactura)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(botonImprimirFactura)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(botonCancelarFactura)
                .addPreferredGap(LayoutStyle.RELATED)
                .add(botonEliminarFactura)
                .add(69, 69, 69))
            .add(layout.createSequentialGroup()
                .add(18, 18, 18)
                .add(layout.createParallelGroup(GroupLayout.TRAILING, false)
                    .add(GroupLayout.LEADING, panelConceptos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(GroupLayout.LEADING, jPanel15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(GroupLayout.LEADING, jPanel16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
                .add(jPanel15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .add(12, 12, 12)
                .add(panelConceptos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .add(17, 17, 17)
                .add(layout.createParallelGroup(GroupLayout.BASELINE)
                	.add(botonAceptarFactura)
                    .add(botonImprimirFactura)
                    .add(botonCancelarFactura)
                    .add(botonEliminarFactura))
                .addContainerGap(10, Short.MAX_VALUE))
        );
		pack();
    }
    
    
    /**
     * @param args the command line arguments
    */ 
    
    
    public static void limpiarVentana()
	{
    	     diaEmision.setText(""); 
    	     mesEmision.setText("");
    	     annoEmision.setText("");
    	     nombreTitularFactura.setText("");
    	     apellidoTitularFactura.setText("");
    	     dni.setText("");//esta es el dni, que hay cambiar por el ComboBOX
    	     numeroFactura.setText("");
    	     totalFactura.setText("");
    	     vFilas = new Vector ();
    	     DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    		 tcr.setHorizontalAlignment(SwingConstants.CENTER);
    		 tablaConceptos = new TablaConceptos (vFilas, vConceptos);
    	     tablaConceptos.getColumn(nombreColumunaConcepto).setMaxWidth(260);
    	     tablaConceptos.getColumn(nombreColumunaConcepto).setMinWidth(260);
    	     tablaConceptos.getColumn(nombreColumunaImporte).setMaxWidth(60);
    	     tablaConceptos.getColumn(nombreColumunaImporte).setMinWidth(60);
    	     tablaConceptos.getColumn(nombreColumunaImporte).setCellRenderer(tcr);
    	     tablaConceptos.getColumn(nombreColumunaConcepto).setCellRenderer(tcr);
    	     //tablaConceptos.getColumn(nombreColumunaImporte).
    	     tablaConceptos.setGridColor (new Color(102, 102, 255));
    	     tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	     scrollPanelConceptos.setViewportView(tablaConceptos);
    		 scrollPanelConceptos.repaint();
	}
    
    
    private javax.swing.JButton botonAceptarFactura;
    private javax.swing.JButton botonImprimirFactura;
    private javax.swing.JButton botonCancelarFactura;
    private javax.swing.JButton botonEliminarFactura;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonAniadir;
    private javax.swing.JLabel euroLabel;
    private javax.swing.JLabel euroLabel2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel panelConceptos;
    private static javax.swing.JTextField diaEmision;
    private static javax.swing.JTextField mesEmision;
    private static javax.swing.JTextField annoEmision;
    private static javax.swing.JTextField nombreTitularFactura;
    private static javax.swing.JTextField apellidoTitularFactura;
    private static javax.swing.JTextField dni;//esta es el dni, que hay cambiar por el ComboBOX
    private static javax.swing.JTextField numeroFactura;
    private static javax.swing.JTextField totalFactura;
    private static TablaConceptos tablaConceptos;
    private static JScrollPane scrollPanelConceptos;
    private static Vector vFilas;
    private static Vector vConceptos;
    

    private static javax.swing.JTextField descripcionConcepto; //descripicón 1
    private static javax.swing.JTextField importeConcepto; // importe 1
    
    private static final String nombreColumunaConcepto = "Descripción";
    private static final String nombreColumunaImporte = "Importe";

    public String getDescripcionConcepto ()
    {
    	return descripcionConcepto.getText();
    }
    
    public String getImporteConcepto ()
    {
    	return importeConcepto.getText();
    }
    
    public void limpiarConcepto ()
    {
    	descripcionConcepto.setText("");
    	importeConcepto.setText("");
    }
    
    
    
	public String getDiaEmision() {
		return diaEmision.getText();
	}



	public void setDiaEmision(String diaEmision) {
		this.diaEmision.setText(diaEmision);
	}



	public String getMesEmision() {
		return mesEmision.getText();
	}



	public void setMesEmision(String mesEmision) {
		this.mesEmision.setText(mesEmision);
	}



	public String getAnnoEmision() {
		return annoEmision.getText();
	}



	public void setAnnoEmision(String annoEmision) {
		this.annoEmision.setText(annoEmision);
	}



	public String getNombreTitularFactura() {
		return nombreTitularFactura.getText();
	}



	public void setNombreTitularFactura(String nombreTitularFactura) {
		this.nombreTitularFactura.setText(nombreTitularFactura);
	}



	public String getApellidoTitularFactura() {
		return apellidoTitularFactura.getText();
	}



	public void setApellidoTitularFactura(
			String apellidoTitularFactura) {
		this.apellidoTitularFactura.setText(apellidoTitularFactura);
	}



	public String getDni() {
		return dni.getText();
	}



	public void setDni(String dni) {
		this.dni.setText(dni);
	}



	public String getNumeroFactura() {
		return numeroFactura.getText();
	}



	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura.setText(numeroFactura);
	}



	public String getTotalFactura() {
		return totalFactura.getText();
	}


	public void sumaryActualizarImporteTotal (float importeConcepto)
	{
		float nuevoTotal;
		
		nuevoTotal = importeConcepto + Float.parseFloat(getTotalFactura());
		setTotalFactura(String.valueOf(nuevoTotal));
	}
	
	public void restaryActualizarImporteTotal (float importeConcepto)
	{
		float nuevoTotal;
		
		nuevoTotal = Float.parseFloat(getTotalFactura()) - importeConcepto;
		setTotalFactura(String.valueOf(nuevoTotal));
	}
	
	public void setTotalFactura(String totalFactura) {
		this.totalFactura.setText(totalFactura);
	}

	public void anniadirConcepto (Concepto concepto)
	{
		Vector vectorAux = new Vector (2);
		
		vectorAux.add(concepto.getStrDescripcion());
		vectorAux.add(concepto.getLimporte());
		
		vFilas.add(vectorAux);
	}
	
	/**
	 * 
	 * 
	 * @param descripcion
	 * @param importe
	 * @return 
	 * 		0 --> válido
	 * 		1 --> concepto vacío
	 * 		2 --> importe no numérico
	 */
	public int conceptoValido (String descripcion ,String importe)
	{
		int retVal = 0;
		
		if (descripcion.length() == 0) retVal = 12; //esto debería ser de informar o de otra clase errores
			else 
				try
				{
					Float.parseFloat(importe);
				}
				catch (Exception e) {
					retVal = 13;
				}
		return retVal;
	}
    
}
