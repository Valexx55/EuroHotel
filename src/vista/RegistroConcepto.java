package vista;

import java.util.Vector;

@SuppressWarnings("serial")
public class RegistroConcepto extends Registro {
	
	int numeroConcepto;
	float importe;
	String descripcion;
	
	

	public RegistroConcepto (int numeroconcepto, float importe, String descripcion ) {
		contenedor = new Vector<Object> ();
		//datos = new V
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public int getNumeroConcepto() {
		return numeroConcepto;
	}

	public void setNumeroConcepto(int numeroConcepto) {
		this.numeroConcepto = numeroConcepto;
	}
}
