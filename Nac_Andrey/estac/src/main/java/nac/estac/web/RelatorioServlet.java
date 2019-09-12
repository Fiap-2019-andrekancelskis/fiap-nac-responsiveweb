package nac.estac.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nac.estac.bo.ValetBO;
import nac.estac.dao.VeiculoDAO;
import nac.estac.entities.Valet;
import nac.estac.entities.Veiculo;

@WebServlet(urlPatterns = "/relatorio")
public class RelatorioServlet extends HttpServlet {

	private static final long serialVersionUID = -3318074259645232320L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<html><body>");
		SimpleDateFormat sdf = new SimpleDateFormat();
		Veiculo v = new Veiculo();
		ValetBO vbo = new ValetBO();

		sdf.applyPattern("HH:mm");
		Date entra;

		try {

			entra = sdf.parse(req.getParameter("entrada"));
			Date sai = sdf.parse(req.getParameter("saida"));

			double preco = 0;
			v.setMarca(req.getParameter("marca"));
			v.setModelo(req.getParameter("modelo"));
			v.setPlaca(req.getParameter("placa"));
			Valet va = new Valet(v, entra, sai, preco);

			out.println("<p>histórico de veículos de hoje</p>");
			ArrayList<Valet> valets = new VeiculoDAO().consultarTodos();
			for (Valet valet : valets) {
				double valor = vbo.calcularPagamento(va.getEntrada(), va.getSaida());
				va.setPreco(valor);
				out.println("<tr><td>" + valet.getVeiculo().getPlaca() + "</td>" + "<td>"
						+ valet.getVeiculo().getMarca() + "</td><td>" + valet.getVeiculo().getModelo() + "</td><td>"
						+ valet.getPreco() + "</td></tr>");
				out.println(valet.getVeiculo().getPlaca());

			}

			out.println("<a href=\"cadastrar-veiculo.html\">Atualizar novo veiculo</a>");
			out.println("</body></html>");
			out.flush();
			out.close();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
