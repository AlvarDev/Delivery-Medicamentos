package dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.BeanUsuarioAndroid;
import beans.UsuarioBean;
import dao.interfaces.I_Usuario;
import daofactory.MySQLDaoFactory;

public class MySql_UsuarioDao extends MySQLDaoFactory implements I_Usuario {

	@Override
	public boolean registrarUsuario(BeanUsuarioAndroid user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		try {
			Connection con = MySQLDaoFactory.obtenerConexion();
			String sql="INSERT into t_persona " +
					"(nombres,apellidoPaterno,apellidoMaterno,dni,fechaNacimiento,celular,direccion,correo,codDistrito,latitud,longitud,codigoPostal)" +
					"VALUES('"+user.getNombres()+"','"+user.getApellidoPaterno()+"','"+user.getApellidoMaterno()+"'" +
							",'"+user.getDni()+"','"+user.getFechaNacimiento()+"','"+user.getCelular()+"'" +
							",'"+user.getDireccion()+"','"+user.getCorreo()+"','"+user.getCodDitrito()+"'" +
							",'"+user.getLatitud()+"','"+user.getLongitud()+"','"+user.getCodigoPostal()+"')";
			Statement stmt=con.createStatement();
			//Id en persona autogenerado -> Validar el id
			int id=stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			if(id!=0){
				//Captar el id
				ResultSet rs = null;
				rs = stmt.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
				//Fin captar
				//Query para usuario
				String query = "INSERT into t_usuario" +
							"(codUsuario,usuario,clave,codRol,estado)" +
							"VALUES('"+id+"','"+user.getUsuario()+"','"+user.getClave()+"','"+user.getCodRol()+"','"+user.getEstado()+"')";
				int filas = stmt.executeUpdate(query);
					if(filas>0){
						flag=true;
					}else{
						flag=false;
					}
			}else{
			 flag = false;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al ingresar usuario/Android: "+e);
		}
		
		return flag;
	}


}
