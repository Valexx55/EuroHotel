package controlador;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;


import javax.swing.JComboBox;
import javax.swing.JComponent;



import vista.SeleccionarClienteEditar;
import vista.FormularioInsCliente;
import vista.FormularioInsFactura;
import vista.MensajesDeInfo;
import vista.SeleccionaClienteFacturar;
import vista.SeleccionarFacturaEditar;
import vista.imprimir.Imprimir;


import modelo.Cliente;
import modelo.Concepto;
import modelo.Factura;
import modelo.NumeroFactura;




@SuppressWarnings("all")
public class EscuchaEventos implements WindowListener, MouseListener , FocusListener, ActionListener {
	
	private Factura facturaNew = null;
	private Factura facturaOld = null;
	private Cliente cliOld = null;
	private Cliente cliNew = null;
	
	private Cliente cliFac = null;
	private String dniFact = null;
	private String dniEdit = null;
	private int nConceptoSelect = -1;
	private Concepto conceptOnEdit = null;
	private String numFact = null;
	private boolean cliOnEdit = false;
	
	private static EscuchaEventos ev = new EscuchaEventos ();
	
	private EscuchaEventos ()
	{
	}
	
	public static EscuchaEventos getInstanceEscuchaEventos ()
	
	{
	return ev;
	}
	

	private int numEvento (Component componente)
	{
		Eventos evento = Eventos.getInstance();
				
		return (evento.getNumEvento(componente.getName())); 
		
	}
	
	
	public void mouseClicked(MouseEvent e) 
	{
		boolean berrorCli = false;
		int numErrorCli = 0;
		int numErroConcepto = 0;
		FormularioInsCliente fInsCli;
		FormularioInsFactura finsfac;
		Concepto concept;
		String claveDni = null;
		String claveFactura = null;
		
		
	switch (numEvento((JComponent)e.getSource())) 
		{

		/*aceptar Ficha cliente*/case 0:
			
			fInsCli = FormularioInsCliente.getInstance();
			if (!FormularioInsCliente.estaVacio())
			{
			cliNew = Cliente.capturarCliente();
			if (cliOnEdit) //el cliente no es Nuevo
				{
					if (!cliNew.igual(cliOld)) //se ha modificado el cliente
						{
							if ((numErrorCli =cliNew.clienteValido()) == 0)
								{
								/*Cliente.BorrarCliente(cliOld.getDni()); 
								cliNew.insertarCliente(); e inserto el nuevo, modificado*/
								cliNew.actualizarCliente(cliOld.getDni());
								
								MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpExitosa);
								}
							else { //eror de formato
								MensajesDeInfo.informar(fInsCli, numErrorCli);
								berrorCli = true;
								//FormularioInsCliente.MostrarCliente(cliNew);
								}
					 
						}
				} else //es un cliente Nuevo, se le da la opción de guardarlos
					{
						if ((numErrorCli =cliNew.clienteValido()) == 0)
									{
									cliNew.insertarCliente(); //e inserto el nuevo, modificado
									MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpExitosa);
									}
								else //si hay error, se informa 
									{MensajesDeInfo.informar(fInsCli, numErrorCli);
									berrorCli =true;
									//FormularioInsCliente.MostrarCliente(cliNew);
								}
					}
					if (!berrorCli)
						{
						cliOnEdit = false;
						fInsCli.limpiarVentana();
						fInsCli.setVisible(false);
						
						}
			}else { //le da a aceptar cuando el formualario está vacío
				MensajesDeInfo.informar(FormularioInsCliente.getInstance(), MensajesDeInfo.FormCliVacio);
			}
			fInsCli = null;
			cliOnEdit = false;
			cliOld = null;
		break;
		/*aceptar Ficha factura*/case 1:
			
			finsfac = FormularioInsFactura.getInstance();
			
			if (!FormularioInsFactura.estaVacio())
			{
			facturaNew = Factura.capturarFactura();
			if (facturaOld != null) //la factura no es nueva
				{
					if (!facturaNew.igual(facturaOld)) //se ha modificado la factura
						{
							Factura.BorrarFactura(facturaOld.getLnumFactura()); // borro el cliente anterior
							if (facturaNew.insertar())
							{
								
								MensajesDeInfo.informar(finsfac, MensajesDeInfo.OpExitosa);
								finsfac.limpiarVentana();
								finsfac.setVisible(false);
								
							} 
							else MensajesDeInfo.informar(finsfac, MensajesDeInfo.errorBD);
						}
				} else //es un cliente Nuevo, se le da la opción de guardarlos
					{
						
						facturaNew.insertar();//e inserto el nuevo, modificado
						MensajesDeInfo.informar(finsfac, MensajesDeInfo.OpExitosa);
						finsfac.limpiarVentana();
						finsfac.setVisible(false);
						
					}
			}else { //le da a aceptar cuando el formualario está vacío
				MensajesDeInfo.informar(finsfac, MensajesDeInfo.FormFacVacio);
			}
			finsfac = null;
			facturaOld = null;
			
		break;
		/*aniadir concepto factura*/case 2:
			finsfac = FormularioInsFactura.getInstance();
			if ((numErroConcepto = finsfac.conceptoValido(finsfac.getDescripcionConcepto(), finsfac.getImporteConcepto()))==0)
			{
				concept = new Concepto (finsfac.getImporteConcepto(), finsfac.getDescripcionConcepto());
				if (nConceptoSelect != -1) //se está editando un concepto
				{
					finsfac.anniadirConcepto(concept, nConceptoSelect);
					finsfac.sumarYRestarYActualizarImporte (conceptOnEdit.getLimporte(), concept.getLimporte());
					nConceptoSelect = -1; //flag No modificando = true
				} else {
					finsfac.anniadirConcepto(concept);
					finsfac.sumaryActualizarImporteTotal (concept.getLimporte());
				}
				FormularioInsFactura.RepintarPanelConceptos();
				
			} else MensajesDeInfo.informar(finsfac, numErroConcepto);
			finsfac.limpiarConcepto();
		break;
		
		/*busacr Reapertura Cliente*/case 3:
			claveDni = SeleccionarClienteEditar.getElementoSeleccionado();
			SeleccionarClienteEditar bcli = SeleccionarClienteEditar.getInstance();
			bcli.visibilizar(false);
			cliOld = Cliente.existeCliente (claveDni);
			fInsCli = FormularioInsCliente.getInstance();
			FormularioInsCliente.MostrarCliente(cliOld);
			cliOnEdit = true;
			fInsCli.visibilizar(true);
			
			
		break;
		/*buscar Reapertura Factura*/case 4:
			claveFactura = SeleccionarFacturaEditar.getElementoSeleccionado();
			SeleccionarFacturaEditar bfra = SeleccionarFacturaEditar.getInstance();
			bfra.visibilizar(false);
			facturaOld = Factura.recuperar(Long.parseLong(claveFactura));
			FormularioInsFactura.MostrarFactura(facturaOld);
			finsfac = FormularioInsFactura.getInstance();
			finsfac.setVisible(true);
		
		break;
		/*caja texto dni*/case 5:
			
		break;
		/*cancelar ficha cliente*/case 6:
			fInsCli = FormularioInsCliente.getInstance();
			if (!FormularioInsCliente.estaVacio())
			{
			cliNew = Cliente.capturarCliente();
			if (cliOnEdit) //el cliente no es Nuevo
				{
					if (!cliNew.igual(cliOld)) //se ha modificado el cliente
						{
							if (MensajesDeInfo.pedirConfirmacion(fInsCli, MensajesDeInfo.ModificacionCli)==1) //si le da la opción de guardarlo
								{
									if ((numErrorCli =cliNew.clienteValido()) == 0)
										{
										//Cliente.BorrarCliente(cliOld.getDni()); // borro el cliente anterior
										//cliNew.insertarCliente(); //e inserto el nuevo, modificado
										cliNew.actualizarCliente(cliOld.getDni());
										MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpExitosa);
										cliOnEdit = false;
										fInsCli.limpiarVentana();
										fInsCli.setVisible(false);
										}
									else 
										{MensajesDeInfo.informar(fInsCli, numErrorCli);
										berrorCli = true;
										//FormularioInsCliente.MostrarCliente(cliNew);
										}
								}
							else //no quiere guardar a u cliente nuevo
								{
								MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpCancelada);
								fInsCli.limpiarVentana();
								fInsCli.setVisible(false);
								} 
						} // no es un cliente nuevo y no se ha modificado, luego cierro
					else
					{
						MensajesDeInfo.informar(FormularioInsCliente.getInstance(), MensajesDeInfo.OpCancelada);
					}
						
				} else //es un cliente Nuevo, se le da la opción de guardarlos
					{
						if (MensajesDeInfo.pedirConfirmacion(fInsCli, MensajesDeInfo.ModificacionCli)==1) //si le da la opción de guardarlo
							{
								if ((numErrorCli =cliNew.clienteValido()) == 0)
									{
									cliNew.insertarCliente(); //e inserto el nuevo, modificado
									MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpExitosa);
									fInsCli.limpiarVentana();
									fInsCli.setVisible(false);
									}
								else 
									{
									MensajesDeInfo.informar(fInsCli, numErrorCli);
									FormularioInsCliente.MostrarCliente(cliNew);
									berrorCli = true;
									}
							}
							else // no quiere guardar los cambios, se cierra y ya
								{
								MensajesDeInfo.informar(FormularioInsCliente.getInstance(), MensajesDeInfo.OpCancelada);
								fInsCli.limpiarVentana();
								fInsCli.setVisible(false);
								}
					}
			if (!berrorCli)
				{
				cliOnEdit = false;
				fInsCli.limpiarVentana();
				fInsCli.setVisible(false);
				}
			fInsCli = null;
			cliOld = null;
			}else//el formulario está vacío
			{
				cliOnEdit = false;
				fInsCli.setVisible(false);
				fInsCli = null;
				cliOld = null;
				
				
			}
						
		break;
		/*cancelar ficha factura*/case 7:
			finsfac = FormularioInsFactura.getInstance();
			
			if (!FormularioInsFactura.estaVacio())
			{
			facturaNew = Factura.capturarFactura();
			if (facturaOld != null) //se estaba reeditando factura
				{
					if (!facturaNew.igual(facturaOld)) //se ha modificado la factura
						{
							if (MensajesDeInfo.pedirConfirmacion(finsfac, MensajesDeInfo.ModificacionFac)==1) //si le da la opción de guardarlo
								{
									
										Factura.BorrarFactura(facturaOld.getLnumFactura());
										facturaNew.insertar(); //e inserto el nuevo, modificado
										MensajesDeInfo.informar(finsfac, MensajesDeInfo.OpExitosa);
										finsfac.limpiarVentana();
										finsfac.setVisible(false);
									
								}
							 
						} // no es un cliente nuevo y no se ha modificado, luego cierro
					else
					{
						MensajesDeInfo.informar(FormularioInsCliente.getInstance(), MensajesDeInfo.OpCancelada);
					}
						
				} else //es una factura Nueva, se le da la opción de guardarlos
					{
						if (MensajesDeInfo.pedirConfirmacion(finsfac, MensajesDeInfo.ModificacionFac)==1) //si le da la opción de guardarlo
							{
								
									facturaNew.insertar(); //e inserto el nuevo, modificado
									MensajesDeInfo.informar(finsfac, MensajesDeInfo.OpExitosa);
									finsfac.limpiarVentana();
								
							} else {
								NumeroFactura.decrementarContadorFactura();
							}
							
					}
			} else {
				NumeroFactura.decrementarContadorFactura();
			}
			finsfac.limpiarVentana();	
			finsfac.setVisible(false);
				
				finsfac = null;
				facturaOld = null;
				
		
			
		break;
		
		/*eliminar concepto factura*/case 8:
			
			finsfac = FormularioInsFactura.getInstance();
			concept = finsfac.obtenerConceptoSeleccionado();
			finsfac.eliminarConceptoSeleccionado ();
			finsfac.restaryActualizarImporteTotal(concept.getLimporte());
			FormularioInsFactura.RepintarPanelConceptos();
			
		break;
		/*imprimir ficha cliente*/case 9:
			
		
		break;
		/*imprimir ficha factura*/case 10:
			finsfac = FormularioInsFactura.getInstance();
			
			if (!FormularioInsFactura.estaVacio())
			{
				facturaNew = Factura.capturarFactura();
				Imprimir.Factura(facturaNew);
			}
		
		break;
		/*listar factura por fecha*/case 11:
			
		break;
		/*listar factura por número*/case 12:
				
		break;
		/*listar factura 1 trimestre*/case 13:
			
		break;
	
		/*listar factura  2 trimestre*/case 14:
			
		break;
		/*listar factura 3 trimestre*/case 15:
			
		break;
		/*listar factura 4 trimestre*/case 16:
			
		break;
		/*modificar concepto factura*/case 17:
			
			finsfac = FormularioInsFactura.getInstance();
			nConceptoSelect = finsfac.obtenerNumConceptoSeleccionado();
			conceptOnEdit = finsfac.obtenerConceptoSeleccionado();
			finsfac.ponerConceptoEnEdicion (conceptOnEdit);
			
			
		break;
		/*eliminar Cliente*/case 18:
			fInsCli = FormularioInsCliente.getInstance();
			if (cliOnEdit)
			{
			cliNew = Cliente.capturarCliente();
			if (MensajesDeInfo.pedirConfirmacion(fInsCli, MensajesDeInfo.EliminarCli)==1) //desea eliminar
				{
				if (Cliente.existeCliente(cliNew.getDni())!=null) //el cliente existe
						Cliente.BorrarCliente(cliNew.getDni());
				MensajesDeInfo.informar(fInsCli, MensajesDeInfo.OpExitosa);
				cliOnEdit = false;
				cliOld = null;
				fInsCli.limpiarVentana();
				fInsCli.setVisible(false);
				} 
			else
				{
					MensajesDeInfo.informar (fInsCli, MensajesDeInfo.OpCancelada);
				}
			} else
			{
				cliOnEdit = false;
				cliOld = null;
				fInsCli.limpiarVentana();
				fInsCli.setVisible(false);
			}
			
			
			
			
		break;
		/*eliminar Factura*/case 19:
			finsfac = FormularioInsFactura.getInstance();
			facturaNew = Factura.capturarFactura();
			
			if (MensajesDeInfo.pedirConfirmacion(finsfac, MensajesDeInfo.EliminarFac)==1) //desea eliminar
				{
				if (Factura.recuperar(facturaNew.getLnumFactura())!=null) //el cliente existe
						Factura.BorrarFactura(facturaNew.getLnumFactura());
				MensajesDeInfo.informar(finsfac, MensajesDeInfo.OpExitosa);//TODO faltaría comprobar los posibles fallos de la interacción con la BD
				finsfac.limpiarVentana();
				finsfac.setVisible(false);
				facturaOld = null;
				} 
			else
				{
					MensajesDeInfo.informar (finsfac, MensajesDeInfo.OpCancelada);
				}
			
			
			break;
		/*case 20:
			
		break;*/
		/*seleccionada cliente a Facturar*/case 21:
			 String  strCad = SeleccionaClienteFacturar.getElementoSeleccionado();
			 SeleccionaClienteFacturar.getInstance().setVisible(false); //oculto el panel de búsqueda
			 finsfac = FormularioInsFactura.getInstance();
			 finsfac.setDni(SeleccionaClienteFacturar.getElementoSeleccionado());
			 finsfac.setNumeroFactura(NumeroFactura.getNumeroFactura().toString());
			 finsfac.setTotalFactura("0");
			 Cliente  cli = Cliente.RecuperarCliente(strCad);
			 finsfac.setNombreTitularFactura(cli.getNombre());
			 finsfac.setApellidoTitularFactura(cli.getApellidos());
			 Calendar calendario = Calendar.getInstance ();
			 finsfac.setDiaEmision(new Integer (calendario.get(Calendar.DATE)).toString());
			 finsfac.setMesEmision(new Integer (calendario.get(Calendar.MONTH)+1).toString());
			 finsfac.setAnnoEmision(new Integer (calendario.get(Calendar.YEAR)).toString());
			 finsfac.setVisible(true);
			
			
			break;
			
		
		default:
			MisExcepciones.RegistrarExcepcion("Evento de ratón inclasificable", false);
		break;
		}
	
					
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}


	
	public void focusGained(FocusEvent arg0) {
		
	}

	public void focusLost(FocusEvent arg0) {
		if (!cliOnEdit)
		{
			if ((numEvento(arg0.getComponent())) == 5)// ha entrado un dato en la caja de texto de dni
			{
				if ((cliOld = Cliente.existeCliente (FormularioInsCliente.getCampo(FormularioInsCliente.campoDni)))!= null)
				{
					cliOnEdit = true;
					FormularioInsCliente.MostrarCliente (cliOld);
					
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*System.out.println(e.getSource().toString());
		JComboBox c = (JComboBox) e.getSource();
		System.out.println("Seleecion actual " + c.getSelectedItem());
		System.out.println(" Posicion " + c.getSelectedIndex());
		c.setSelectedIndex(c.getSelectedIndex());*/
		//SeleccionarClienteEditar.getInstance().fijarDniSeleccionado(c.getSelectedIndex());
		//int nevente = numEvento ((JComponent)e.getSource());
		
		//System.out.println(nevente);
		
		String strNameObjectEdit = null;
				
		JComboBox c = (JComboBox) e.getSource();
		strNameObjectEdit = c.getName();
		
		if (strNameObjectEdit.equals(Eventos.clickComboEditCli))
			SeleccionarClienteEditar.setElementoSeleccionado(c.getSelectedItem().toString());
		else if (strNameObjectEdit.equals(Eventos.clickComboFactCli))
			SeleccionaClienteFacturar.setElementoSeleccionado(c.getSelectedItem().toString());
			else if (strNameObjectEdit.equals(Eventos.clickComboFactEdit))
				SeleccionarFacturaEditar.setElementoSeleccionado(c.getSelectedItem().toString());
		
		
	}

@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	cliOnEdit = false;
	cliOld = null;
	FormularioInsCliente.limpiarVentana();
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
}


}
