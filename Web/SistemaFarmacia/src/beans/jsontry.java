package beans;

import com.google.gson.Gson;

public class jsontry {

	
	
	public static void main(String args[]){
		
		UsuarioBean usuario = new UsuarioBean();
		usuario.setUsuario("Jgomez");
		usuario.setClave("james");
		usuario.setCodRol(1);
		usuario.setEstado(0);
		usuario.setPersona(null);
		usuario.setRol(null);
		usuario.setCodUsuario(0);
		
		
		System.out.println("[]"+new Gson().toJson(usuario));
	}
	
}
