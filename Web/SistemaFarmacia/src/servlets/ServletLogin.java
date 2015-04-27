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
import beans.PedidoBean;
import beans.UsuarioBean;
import dao.interfaces.I_Pedido;
import daofactory.DAOFactory;


@WebServlet("/Login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
		HttpSession session=request.getSession();
		UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");	
		if(user!=null){
			if(user.getRol().getDescripcion().equals("Tecnico")){
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Pedido userdao=dao.getPedidoDAO();
				Vector<PedidoBean> pedidosPendientes=userdao.obtenerPedidosPendientes(user.getPersona().getCodDistrito());
				if(pedidosPendientes.size()>0){
					request.setAttribute("notificacion", "true");
				}else{
					request.setAttribute("notificacion", "false");
				}
				request.setAttribute("pedidosPendientes", pedidosPendientes);
				request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
			
			}else if(user.getRol().getDescripcion().equals("Administrador")){
			
				request.getRequestDispatcher("/Administrador/pedidosPendientes.jsp").forward(request, response);
			
			}
		
		}else{
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		
		}
		}catch (Exception e) {
			out.print(e.getMessage());
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try{
			
			String uss=request.getParameter("user");
			String pass= request.getParameter("pass");
			if(uss.equals("")||pass.equals("")){
				request.setAttribute("mensaje", "Ingrese usuario y contraseña");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				I_Pedido pedidodao = dao.getPedidoDAO();
				UsuarioBean ses=pedidodao.validar(uss,pass);
				
				if(ses!=null){
					
					HttpSession session= request.getSession();
					session.setAttribute("usuario", ses);
					if(ses.getRol().getDescripcion().equals("Tecnico")){
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(ses.getPersona().getCodDistrito());
						if(pedidosPendientes.size()>0){
							request.setAttribute("notificacion", "true");
						}else{
							request.setAttribute("notificacion", "false");
						}
						request.setAttribute("pedidosPendientes", pedidosPendientes);
						request.getRequestDispatcher("/Tecnico/pedidosPendientes.jsp").forward(request, response);
					}else if(ses.getRol().getDescripcion().equals("Administrador")){
						Vector<PedidoBean> pedidosPendientes=pedidodao.obtenerPedidosPendientes(ses.getPersona().getCodDistrito());
						request.setAttribute("pedidosPendientes", pedidosPendientes);
						request.getRequestDispatcher("/Administrador/pedidosPendientes.jsp").forward(request, response);
					}
				
				}else{
						
					request.setAttribute("mensaje", "Datos Incorrectos");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				
				}
			}
		}catch (Exception e) {
			out.print(e.getMessage());
		}	
	}
}
