package nac.estac.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nac.estac.bo.UsuarioBO;
import nac.estac.entities.Usuario;


	@WebServlet(urlPatterns = "/login")
	public class LoginServlet extends HttpServlet{

		private static final long serialVersionUID = -4893569432445997382L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			String login = req.getParameter("email");
			String senha = req.getParameter("senha");
			
			Usuario usuario = new Usuario(login, senha);
			
			out.println("<html><body>");
			
			usuario = new UsuarioBO().autenticar(usuario);
			
			if(usuario != null) {
				out.println("<h2>Usuário " + usuario.getNome() + " autenticado com sucesso.</h2>");
				Cookie cookie = new Cookie("usuario.logado", usuario.getNome());
				resp.addCookie(cookie);
				resp.sendRedirect("cadastrar-veiculo.html");
			} else {
				resp.sendRedirect("cadastro.html"); 
			}
			
			out.println("<a href=\"index.html\">Voltar para Home</a>");
			out.println("</body></html>");
			out.flush();
			out.close();
		}
	
}
