package beans;

public class SucursalBean {
	int codSucursal;
	String direccion;
	int codDistrito;
	double latitud;
	double longitud;
	int codigoPostal;
	DistritoBean distrito;
	public int getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public DistritoBean getDistrito() {
		return distrito;
	}
	public void setDistrito(DistritoBean distrito) {
		this.distrito = distrito;
	}
}
