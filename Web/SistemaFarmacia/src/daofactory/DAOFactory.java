package daofactory;
import dao.interfaces.I_Pedido;
import dao.interfaces.I_Usuario;

public abstract class DAOFactory {

	public static final int MYSQL =1;
	public static final int SQLSERVER =2;
	public static final int ORACLE =3;
		
	public static DAOFactory getDAOFactory(int factory){
		switch(factory){
		case MYSQL: return new MySQLDaoFactory();
		case SQLSERVER:
			//return new SQLSERVERDAOFactory();
		case ORACLE:
			//return new ORACLEDAOFactory();
		default:
			return null;
		}
	}
	
	public abstract I_Pedido getPedidoDAO();
	public abstract I_Usuario getUsuarioDao();
}