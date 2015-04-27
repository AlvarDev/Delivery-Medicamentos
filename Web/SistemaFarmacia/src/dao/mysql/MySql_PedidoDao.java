package dao.mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import beans.DetallePedidoBean;
import beans.DistritoBean;
import beans.EstadoPedidoBean;
import beans.PedidoBean;
import beans.PersonaBean;
import beans.PresentacionxMedicamentoBean;
import beans.RolBean;
import beans.UsuarioBean;
import dao.interfaces.I_Pedido;
import daofactory.MySQLDaoFactory;

public class MySql_PedidoDao extends MySQLDaoFactory implements I_Pedido{

	@Override
	public UsuarioBean validar(String usuario, String clave) throws Exception {
		// TODO Auto-generated method stub
		UsuarioBean objusuario=null;
		try {
			
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="select * from t_usuario a INNER JOIN t_rol b ON a.codRol=b.codRol INNER JOIN t_persona c ON a.codUsuario=c.codPersona where a.usuario='"+usuario+"' and a.clave='"+clave+"' and a.estado='1'";
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				objusuario= new UsuarioBean();
				objusuario.setCodUsuario(rs.getInt("a.codUsuario"));
				objusuario.setUsuario(rs.getString("a.usuario"));
				objusuario.setClave(rs.getString("a.clave"));
				objusuario.setCodRol(rs.getInt("a.codRol"));
				
				RolBean objrol=new RolBean();
				objrol.setCodRol(rs.getInt("b.codRol"));
				objrol.setDescripcion(rs.getString("b.descripcion"));
				objusuario.setRol(objrol);
				
				PersonaBean objpersona=new PersonaBean();
				objpersona.setCodPersona(rs.getInt("c.codPersona"));
				objpersona.setNombres(rs.getString("c.nombres"));
				objpersona.setApellidoPaterno(rs.getString("c.apellidoPaterno"));
				objpersona.setApellidoMaterno(rs.getString("c.apellidoMaterno"));
				objpersona.setDni(rs.getString("c.dni"));
				objpersona.setFechaNacimiento(rs.getString("c.fechaNacimiento"));
				objpersona.setCelular(rs.getInt("c.celular"));
				objpersona.setDireccion(rs.getString("c.direccion"));
				objpersona.setCorreo(rs.getString("c.correo"));
				objpersona.setCodDistrito(rs.getInt("c.codDistrito"));
				objpersona.setLatitud(rs.getDouble("c.latitud"));
				objpersona.setLongitud(rs.getDouble("c.longitud"));
				objpersona.setCodigoPostal(rs.getInt("c.codigoPostal"));
				objusuario.setPersona(objpersona);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return objusuario;
	}

	@Override
	public Vector<PedidoBean> obtenerPedidosPendientes(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
				Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
				try {
					Connection con=MySQLDaoFactory.obtenerConexion();
					Statement stmt=con.createStatement();
					String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and c.descripcion='Pendiente' ORDER BY a.fechaPedido,a.horaPedido";
					ResultSet rs= stmt.executeQuery(query);
					while( rs.next() ){
						PedidoBean obj = new PedidoBean();
						
						obj.setCodPedido(rs.getString("a.codPedido"));
						obj.setFechaPedido(rs.getString("a.fechaPedido"));
						obj.setHoraPedido(rs.getString("a.horaPedido"));
						obj.setMontoTotal(rs.getDouble("a.montoTotal"));
						obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
						obj.setCodPersona(rs.getInt("a.codPersona"));
						obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
					//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
						
						PersonaBean persona=new PersonaBean();
						persona.setCodPersona(rs.getInt("b.codPersona"));
						persona.setNombres(rs.getString("b.nombres"));
						persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
						persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
						persona.setCodDistrito(rs.getInt("b.codDistrito"));
						obj.setPersona(persona);
						
						EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
						estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
						estadoPedido.setDescripcion(rs.getString("c.descripcion"));
						obj.setEstadoPedido(estadoPedido);
						
						pedidos.add(obj);
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.print(e.getMessage());
				}
				return pedidos;
	}

	@Override
	public Vector<PedidoBean> obtenerPedidosProceso(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and c.descripcion='En Proceso' ORDER BY a.fechaPedido,a.horaPedido";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PedidoBean obj = new PedidoBean();
				
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
				
				pedidos.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return pedidos;
	}

	@Override
	public Vector<PedidoBean> obtenerHistorialPedidos(int codDistrito)
			throws Exception {
		// TODO Auto-generated method stub
		Vector<PedidoBean> pedidos = new Vector<PedidoBean>();
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and (c.descripcion='Terminado' or c.descripcion='Cancelado') ORDER BY a.fechaPedido, a.horaPedido ";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				PedidoBean obj = new PedidoBean();
				
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
				
				pedidos.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return pedidos;
	}

	@Override
	public boolean actualizarPedidoPendiente(PedidoBean pedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"' where codPedido='"+pedido.getCodPedido()+"'";
			int filas=stmt.executeUpdate(query);
			if(filas>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public boolean actualizarPedidoProceso(PedidoBean pedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			//String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"', horaFinPedido=curTime() where codPedido='"+pedido.getCodPedido()+"'";
			String query="update  t_pedido set codEstadoPedido='"+pedido.getCodEstadoPedido()+"' where codPedido='"+pedido.getCodPedido()+"'";
			int filas=stmt.executeUpdate(query);
			if(filas>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public Vector<DetallePedidoBean> obtenerDetalle(String codPedido) throws Exception {
		// TODO Auto-generated method stub
		Vector<DetallePedidoBean> detallespedido = new Vector<DetallePedidoBean>();
		try {
			
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			//String query="Select * from t_detallepedido a INNER JOIN t_pedido b ON a.codPedido=b.codPedido INNER JOIN t_presentacionxmedicamento c ON a.codMedicamento=c.codMedicamento and a.codCantidadxPresentacion=c.codCantidadxPresentacion INNER JOIN t_persona d ON b.codPersona=d.codPersona INNER JOIN t_distrito e ON d.codDistrito=e.codDistrito INNER JOIN t_estadopedido f ON b.codEstadoPedido=f.codEstadoPedido INNER JOIN t_sucursal_presentacionxmedicamentos g ON a.codSucursal=g.codSucursal and a.codMedicamento=g.codMedicamento and a.codCantidadxPresentacion=g.codCantidadxPresentacion  where a.codPedido='"+codPedido+"'";
			String query="Select * from t_detallepedido a INNER JOIN t_pedido b ON a.codPedido=b.codPedido INNER JOIN t_presentacionxmedicamento c ON a.codMedicamento=c.codMedicamento and a.codCantidadxPresentacion=c.codCantidadxPresentacion INNER JOIN t_persona d ON b.codPersona=d.codPersona INNER JOIN t_distrito e ON d.codDistrito=e.codDistrito INNER JOIN t_estadopedido f ON b.codEstadoPedido=f.codEstadoPedido where a.codPedido='"+codPedido+"'";
			ResultSet rs= stmt.executeQuery(query);
			while( rs.next() ){
				DetallePedidoBean obj= new DetallePedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setCodMedicamento(rs.getInt("a.codMedicamento"));
				obj.setCodCantidadxPresentacion(rs.getInt("a.codCantidadxPresentacion"));
				obj.setCodSucursal(rs.getInt("a.codSucursal"));
				obj.setCantidad(rs.getInt("a.cantidad"));
				obj.setPrecioTotal(rs.getDouble("a.precioTotal"));
				
				PedidoBean pedido=new PedidoBean();
				pedido.setCodPedido(rs.getString("b.codPedido"));
				pedido.setFechaPedido(rs.getString("b.fechaPedido"));
				pedido.setHoraPedido(rs.getString("b.horaPedido"));
			//	pedido.setHoraFinPedido(rs.getString("b.horaFinPedido"));
				pedido.setCodEstadoPedido(rs.getInt("b.codEstadoPedido"));
				pedido.setMontoTotal(rs.getDouble("b.montoTotal"));
				pedido.setMontoCancelar(rs.getDouble("b.montoCancelar"));
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("f.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("f.descripcion"));
				pedido.setEstadoPedido(estadoPedido);
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("d.codPersona"));
				persona.setNombres(rs.getString("d.nombres"));
				persona.setApellidoPaterno(rs.getString("d.ApellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("d.ApellidoMaterno"));
				persona.setDireccion(rs.getString("d.direccion"));
				persona.setCodDistrito(rs.getInt("d.codDistrito"));
				
				DistritoBean distrito=new DistritoBean();
				distrito.setCodDistrito(rs.getInt("e.codDistrito"));
				distrito.setNombre(rs.getString("e.nombre"));
				
				persona.setDistrito(distrito);
				
				pedido.setPersona(persona);
				
				obj.setPedido(pedido);
				
				PresentacionxMedicamentoBean presentacionxmedicamento=new PresentacionxMedicamentoBean();
				presentacionxmedicamento.setAbreviatura(rs.getString("c.abreviatura"));
				presentacionxmedicamento.setPrecio(rs.getDouble("c.precio"));
				
				obj.setPresentacionxmedicamento(presentacionxmedicamento);
				
				//SucursalPresentacionxMedicamentoBean sucpresmed=new SucursalPresentacionxMedicamentoBean();
				//sucpresmed.setStock1(rs.getInt("g.stock1"));
				//sucpresmed.setStock2(rs.getInt("g.stock2"));
				
				//obj.setSucursalxpresentacionxmedicamento(sucpresmed);
				
				detallespedido.add(obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return detallespedido;
	}

	@Override
	public PedidoBean buscarPedido(String codPedido, int codDistrito) throws Exception {
		// TODO Auto-generated method stub
		PedidoBean obj=null;
		try {
			
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="select * from t_pedido a INNER JOIN t_persona b ON a.codPersona=b.codPersona INNER JOIN t_estadopedido c ON a.codEstadoPedido=c.codEstadoPedido where b.codDistrito='"+codDistrito+"' and a.codPedido='"+codPedido+"'";
			ResultSet rs= stmt.executeQuery(query);
			if( rs.next() ){
				obj = new PedidoBean();
				obj.setCodPedido(rs.getString("a.codPedido"));
				obj.setFechaPedido(rs.getString("a.fechaPedido"));
				obj.setHoraPedido(rs.getString("a.horaPedido"));
				obj.setMontoTotal(rs.getDouble("a.montoTotal"));
				obj.setMontoCancelar(rs.getDouble("a.montoCancelar"));
				obj.setCodPersona(rs.getInt("a.codPersona"));
				obj.setCodEstadoPedido(rs.getInt("a.codEstadoPedido"));
			//	obj.setHoraFinPedido(rs.getString("a.horaFinPedido"));
				
				PersonaBean persona=new PersonaBean();
				persona.setCodPersona(rs.getInt("b.codPersona"));
				persona.setNombres(rs.getString("b.nombres"));
				persona.setApellidoPaterno(rs.getString("b.apellidoPaterno"));
				persona.setApellidoMaterno(rs.getString("b.apellidoMaterno"));
				persona.setCodDistrito(rs.getInt("b.codDistrito"));
				obj.setPersona(persona);
				
				EstadoPedidoBean estadoPedido=new EstadoPedidoBean();
				estadoPedido.setCodEstadoPedido(rs.getInt("c.codEstadoPedido"));
				estadoPedido.setDescripcion(rs.getString("c.descripcion"));
				obj.setEstadoPedido(estadoPedido);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return obj;
	}

	@Override
	public boolean generaBoleta(String codPedido) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="insert into t_boleta (codPedido) values ('"+codPedido+"')";
			int filas=stmt.executeUpdate(query);
			if(filas>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean confirmarPedido(int codSucursal, int codMedicamento,int codCantxPresentacion,int stc) throws Exception {
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock1=stock1-"+stc+" where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
			int filas=stmt.executeUpdate(query);
			if(filas>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean actualizarStock2(int codSucursal, int codMedicamento,int codCantxPresentacion) throws Exception {
		boolean flag=false;
		try {
			Connection con=MySQLDaoFactory.obtenerConexion();
			Statement stmt=con.createStatement();
			String query="update  t_sucursal_presentacionxmedicamentos set stock2=stock1 where codSucursal='"+codSucursal+"' and codMedicamento='"+codMedicamento+"' and codCantxPresentacion='"+codCantxPresentacion+"';";
			int filas=stmt.executeUpdate(query);
			if(filas>0){
				flag=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}
		return flag;
	}
}
