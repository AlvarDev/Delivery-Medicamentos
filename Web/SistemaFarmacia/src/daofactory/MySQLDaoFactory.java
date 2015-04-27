package daofactory;
import java.sql.Connection;
import java.sql.DriverManager;

import dao.interfaces.I_Pedido;
import dao.interfaces.I_Usuario;
import dao.mysql.MySql_PedidoDao;
import dao.mysql.MySql_UsuarioDao;
public class MySQLDaoFactory extends DAOFactory{
		
	public static Connection obtenerConexion(){
		
		Connection con=null;
		
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/bd_farmacia";
			String user = "root";
			String password ="root";
			con = DriverManager.getConnection(url,user,password);
		
		} catch (Exception e) {
		// TODO: handle exception
		
			System.out.print(e.getMessage());
			System.out.print("Connection Failed!");
			e.printStackTrace();		
		}
		return con;
	
	}
	
	public I_Pedido getPedidoDAO(){
		return new MySql_PedidoDao();
	}

	@Override
	public I_Usuario getUsuarioDao() {
		// TODO Auto-generated method stub
		return new MySql_UsuarioDao();
	}
	
}
