package beans;

import java.io.Serializable;

public class BeanUsuarioAndroid implements Serializable{

	//Atributos propios de la persona
	private int codPersona;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String dni;
	private String fechaNacimiento;
	private int celular;
	private String direccion;
	private String correo;
	private int codDitrito;
	private long latitud;
	private long longitud;
	private int codigoPostal;
	//Atributos propios del usuario
	private int codUsuario;
	private String usuario;
	private String clave;
	private int codRol;
	private int estado;
	/**
	 * @return the codPersona
	 */
	public int getCodPersona() {
		return codPersona;
	}
	/**
	 * @param codPersona the codPersona to set
	 */
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the celular
	 */
	public int getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(int celular) {
		this.celular = celular;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * @return the codDitrito
	 */
	public int getCodDitrito() {
		return codDitrito;
	}
	/**
	 * @param codDitrito the codDitrito to set
	 */
	public void setCodDitrito(int codDitrito) {
		this.codDitrito = codDitrito;
	}
	/**
	 * @return the latitud
	 */
	public long getLatitud() {
		return latitud;
	}
	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(long latitud) {
		this.latitud = latitud;
	}
	/**
	 * @return the longitud
	 */
	public long getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(long longitud) {
		this.longitud = longitud;
	}
	/**
	 * @return the codigoPostal
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}
	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	/**
	 * @return the codUsuario
	 */
	public int getCodUsuario() {
		return codUsuario;
	}
	/**
	 * @param codUsuario the codUsuario to set
	 */
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the codRol
	 */
	public int getCodRol() {
		return codRol;
	}
	/**
	 * @param codRol the codRol to set
	 */
	public void setCodRol(int codRol) {
		this.codRol = codRol;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanUsuarioAndroid [codPersona=" + codPersona + ", nombres="
				+ nombres + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + ", celular="
				+ celular + ", direccion=" + direccion + ", correo=" + correo
				+ ", codDitrito=" + codDitrito + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", codigoPostal=" + codigoPostal
				+ ", codUsuario=" + codUsuario + ", usuario=" + usuario
				+ ", clave=" + clave + ", codRol=" + codRol + ", estado="
				+ estado + "]";
	}
	
	
	
	
}
