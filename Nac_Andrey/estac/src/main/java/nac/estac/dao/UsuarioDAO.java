package nac.estac.dao;

import java.util.ArrayList;

import nac.estac.entities.Usuario;

public class UsuarioDAO {

private static ArrayList<Usuario> USUARIOS = new ArrayList<>();
	
	static {
		USUARIOS.add(new Usuario("Andrey", "andrey", "123"));
	}
	
	public void adicionar(Usuario u) {
		USUARIOS.add(u);
	}
	
	public ArrayList<Usuario> consultarTodos() {
		return USUARIOS;
	}
	
	public Usuario consultarPorLogin(String login) {
		for(Usuario u : USUARIOS) {
			if(u.getEmail().equals(login)) {
				return u;
			}
		}
		return null;
	}
	
}
