package beans;

public class CantidadxPresentacionBean {
	int codCantidadxPresentacion;
	int cantidad;
	int codPresentacion;
	int estado;
	PresentacionBean presentacion;
	public int getCodCantidadxPresentacion() {
		return codCantidadxPresentacion;
	}
	public void setCodCantidadxPresentacion(int codCantidadxPresentacion) {
		this.codCantidadxPresentacion = codCantidadxPresentacion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCodPresentacion() {
		return codPresentacion;
	}
	public void setCodPresentacion(int codPresentacion) {
		this.codPresentacion = codPresentacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public PresentacionBean getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(PresentacionBean presentacion) {
		this.presentacion = presentacion;
	}
}
