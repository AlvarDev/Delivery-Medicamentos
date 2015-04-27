package beans;

public class DetallePedidoBean {
	String codPedido;
	int codSucursal;
	int codMedicamento;
	int codCantidadxPresentacion;
	int cantidad;
	double precioTotal;
	PedidoBean pedido;
	PresentacionxMedicamentoBean presentacionxmedicamento;
	//SucursalPresentacionxMedicamentoBean sucursalxpresentacionxmedicamento;
	public String getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(String codPedido) {
		this.codPedido = codPedido;
	}
	public int getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}
	public int getCodMedicamento() {
		return codMedicamento;
	}
	public void setCodMedicamento(int codMedicamento) {
		this.codMedicamento = codMedicamento;
	}
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
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public PedidoBean getPedido() {
		return pedido;
	}
	public void setPedido(PedidoBean pedido) {
		this.pedido = pedido;
	}
	public PresentacionxMedicamentoBean getPresentacionxmedicamento() {
		return presentacionxmedicamento;
	}
	public void setPresentacionxmedicamento(
			PresentacionxMedicamentoBean presentacionxmedicamento) {
		this.presentacionxmedicamento = presentacionxmedicamento;
	}
	//public SucursalPresentacionxMedicamentoBean getSucursalxpresentacionxmedicamento() {
		//return sucursalxpresentacionxmedicamento;
	//}
	//public void setSucursalxpresentacionxmedicamento(
		//	SucursalPresentacionxMedicamentoBean sucursalxpresentacionxmedicamento) {
		//this.sucursalxpresentacionxmedicamento = sucursalxpresentacionxmedicamento;
	//}
}
