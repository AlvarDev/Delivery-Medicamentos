package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.DetallePedidoBean;
import beans.PedidoBean;
import beans.UsuarioBean;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;

@WebServlet("/Pedido")
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(accion.equals("PedidosPendientes")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosPendientes", pedidosPendientes);
					request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("PedidosEnProceso")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> pedidosProceso=pedidodao.obtenerPedidosProceso(user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosProceso", pedidosProceso);
					request.getRequestDispatcher("/Tecnico/pedidosProceso.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("HistorialDePedidos")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<PedidoBean> historialpedidos=pedidodao.obtenerHistorialPedidos(user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("historialpedidos", historialpedidos);
					request.getRequestDispatcher("/Tecnico/historialPedidos.jsp").forward(request, response);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}else if(accion.equals("DetallePedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					Vector<DetallePedidoBean> detallePedidos=pedidodao.obtenerDetalle(request.getParameter("id"));
					System.out.print(detallePedidos.size());
					request.setAttribute("detallePedidos", detallePedidos);
					request.getRequestDispatcher("/Tecnico/detallePedido.jsp").forward(request, response);
					
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String accion=request.getParameter("accion");
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(accion.equals("generarBoleta")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=new PedidoBean();
					pedido.setCodPedido(request.getParameter("idpedido"));
					pedido.setCodEstadoPedido(2);
					pedidodao.actualizarPedidoPendiente(pedido);
					pedidodao.generaBoleta(request.getParameter("idpedido"));
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosPendientes", pedidosPendientes);
					request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}			
			}else if(accion.equals("finalizarPedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=new PedidoBean();
					pedido.setCodPedido(request.getParameter("idpedido"));
					pedido.setCodEstadoPedido(Integer.parseInt(request.getParameter("slt_estado")));
					pedidodao.actualizarPedidoProceso(pedido);
					Vector<DetallePedidoBean>detallepedido=pedidodao.obtenerDetalle(request.getParameter("idpedido"));
					if(pedido.getCodEstadoPedido()==4){
						for(int i=0;i<detallepedido.size();i++){
							pedidodao.confirmarPedido(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion(), detallepedido.get(i).getCantidad());
							pedidodao.actualizarStock2(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion());
						}
					}else{
						for(int i=0;i<detallepedido.size();i++){
							pedidodao.actualizarStock2(detallepedido.get(i).getCodSucursal(), detallepedido.get(i).getCodMedicamento(), detallepedido.get(i).getCodCantidadxPresentacion());
						}
					}
					Vector<PedidoBean> pedidosProceso=pedidodao.obtenerPedidosProceso(user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					request.setAttribute("pedidosProceso", pedidosProceso);
					request.getRequestDispatcher("/Tecnico/pedidosProceso.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}
			}else if(accion.equals("buscarPedido")){
				try{
					DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
					I_Pedido pedidodao=dao.getPedidoDAO();
					PedidoBean pedido=pedidodao.buscarPedido(request.getParameter("txt_busqueda"),user.getPersona().getCodDistrito());
					Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
					if(pedidosPendientes.size()>0){
						request.setAttribute("notificacion", "true");
					}else{
						request.setAttribute("notificacion", "false");
					}
					if(pedido!=null){
						request.setAttribute("titulo", "Pedido Encontrado");
					}else{
						request.setAttribute("titulo", "No se encontro el pedido");
					}
					request.setAttribute("pedido", pedido);
					request.getRequestDispatcher("/Tecnico/buscarPedido.jsp").forward(request, response);
				}catch (Exception e) {
					// TODO: handle exception
					out.print(e.getMessage());
				}
			}
		}else{
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
