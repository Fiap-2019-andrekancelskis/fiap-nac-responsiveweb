package nac.estac.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nac.estac.bo.UsuarioBO;
import nac.estac.dao.UsuarioDAO;
import nac.estac.entities.Usuario;

@WebServlet(urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");

		Usuario usuario = new Usuario(nome, email, senha);
		UsuarioBO bo = new UsuarioBO();

		out.println("<html><body>");

		if (bo.validarUsuario(usuario) && bo.validarSenha(senha) == true && bo.validarNome(nome) == true) {
			new UsuarioDAO().adicionar(usuario);
			resp.sendRedirect("index.html");
		} else {
			out.println("<h2 style=\"color:red;\"> É preciso "
					+ "informar todos os campos do usuário, senha com 6 digitos e nome entre 2 a "
					+ "30caracteres</h2>");
		}

		out.println("<a href=\"cadastro.html\">Cadastrar novamente</a>");
		out.println("</body></html>");
		out.flush();
		out.close();

	}

}
