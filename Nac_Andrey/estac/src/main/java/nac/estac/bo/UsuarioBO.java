package nac.estac.bo;

import nac.estac.dao.UsuarioDAO;
import nac.estac.entities.Usuario;

public class UsuarioBO {

	public Usuario autenticar(Usuario u) {
		Usuario usuario = new UsuarioDAO().consultarPorLogin(u.getEmail());
		if(usuario == null) return null;
		if(!usuario.getSenha().equals(u.getSenha())) return null;
		return usuario;
	}
	
	public boolean validarUsuario(Usuario u) {
		return u.getNome() != null && 
				u.getEmail() != null && 
				u.getSenha() != null; 
	}
	public boolean validarNome(String nome) {
		if (nome.length() >= 2 && nome.length() <= 30) {
			// u.setNome(nome);
			return true;
		} else {
			return false;
		}
	}
		
	public boolean validarSenha(String senha) {
		if (senha.length() == 6) {
			// u.setSenha(senha);
			return true;
		} else {
			return false;
		}
	}
	
}
