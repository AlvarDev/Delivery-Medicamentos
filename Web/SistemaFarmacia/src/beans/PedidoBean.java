package beans;

public class PedidoBean {
	String codPedido;
	String fechaPedido;
	String horaPedido;
	//String horaFinPedido;
	Double montoTotal;
	Double montoCancelar;
	int codPersona;
	int codEstadoPedido;
	PersonaBean persona;
	EstadoPedidoBean estadoPedido;
	public String getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public String getHoraPedido() {
		return horaPedido;
	}
	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}
	//public String getHoraFinPedido() {
		//return horaFinPedido;
	//}
	//public void setHoraFinPedido(String horaFinPedido) {
		//this.horaFinPedido = horaFinPedido;
	//}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Double getMontoCancelar() {
		return montoCancelar;
	}
	public void setMontoCancelar(Double montoCancelar) {
		this.montoCancelar = montoCancelar;
	}
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public int getCodEstadoPedido() {
		return codEstadoPedido;
	}
	public void setCodEstadoPedido(int codEstadoPedido) {
		this.codEstadoPedido = codEstadoPedido;
	}
	public EstadoPedidoBean getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(EstadoPedidoBean estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	public PersonaBean getPersona() {
		return persona;
	}
	public void setPersona(PersonaBean persona) {
		this.persona = persona;
	}
	
}
