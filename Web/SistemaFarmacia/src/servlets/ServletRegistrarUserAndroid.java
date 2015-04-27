package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ResponseObject;

import beans.BeanUsuarioAndroid;
import beans.UsuarioBean;

import com.google.gson.Gson;

import dao.interfaces.I_Pedido;
import dao.interfaces.I_Usuario;
import daofactory.DAOFactory;

/**
 * Servlet implementation class ServletRegistrarUserAndroid
 */
@WebServlet("/regUserA")
public class ServletRegistrarUserAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrarUserAndroid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//StringBuilder		
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
		
		//Bean
	    
	    BeanUsuarioAndroid user = new Gson().fromJson(sb.toString(),BeanUsuarioAndroid.class);
	    
	    //Dao
	    DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		I_Usuario userdao = dao.getUsuarioDao();
		
		//Respuesta del dao
		boolean respuesta = userdao.registrarUsuario(user);
		
		//Objeto respuesta
		ResponseObject responseObj = new ResponseObject();
		responseObj.setSuccess(respuesta);
		
		//Response
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(new Gson().toJson(responseObj));
		
	}

}
