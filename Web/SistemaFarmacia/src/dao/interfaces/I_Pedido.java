package dao.interfaces;

import java.util.Vector;

import beans.DetallePedidoBean;
import beans.PedidoBean;
import beans.UsuarioBean;

public interface I_Pedido {
	public UsuarioBean validar(String usuario, String clave) throws Exception;
	public Vector<PedidoBean> obtenerPedidosPendientes(int codDistrito) throws Exception ;
	public Vector<PedidoBean> obtenerPedidosProceso(int codDistrito) throws Exception ;
	public Vector<PedidoBean> obtenerHistorialPedidos(int codDistrito) throws Exception ;
	public boolean actualizarPedidoPendiente(PedidoBean pedido) throws Exception ;
	public boolean actualizarPedidoProceso(PedidoBean pedido) throws Exception ;
	public Vector<DetallePedidoBean> obtenerDetalle (String codPedido) throws Exception ;
	public PedidoBean buscarPedido (String codPedido, int codDistrito) throws Exception ;
	public boolean generaBoleta (String codPedido) throws Exception ;
	public boolean confirmarPedido (int codSucursal,int codMedicamento, int codCantxPresentacion, int stc) throws Exception;
	public boolean actualizarStock2 (int codSucursal,int codMedicamento, int codCantxPresentacion) throws Exception;
}
