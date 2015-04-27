package beans;

public class UsuarioBean {
	int codUsuario;
	String usuario;
	String clave;
	int codRol;
	int estado;
	RolBean rol;
	PersonaBean persona;
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getCodRol() {
		return codRol;
	}
	public void setCodRol(int codRol) {
		this.codRol = codRol;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public RolBean getRol() {
		return rol;
	}
	public void setRol(RolBean rol) {
		this.rol = rol;
	}
	public PersonaBean getPersona() {
		return persona;
	}
	public void setPersona(PersonaBean persona) {
		this.persona = persona;
	}
}
