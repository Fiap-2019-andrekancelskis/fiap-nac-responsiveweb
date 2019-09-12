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
import nac.estac.bo.VeiculoBO;
import nac.estac.dao.VeiculoDAO;
import nac.estac.entities.Valet;
import nac.estac.entities.Veiculo;

@WebServlet(urlPatterns = "/estacionamento")
public class EstacionamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		try {
			out.println("<html>");
			out.println("<head> <meta name=\"viewport\" content=\"width=device-width, user-scalable=no\">"
					+ "<link type=\"text/css\" rel=\"stylesheet\" href=\"css/history.css\">");
			out.println("</head> <body>");
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			VeiculoDAO dao = new VeiculoDAO();
			Veiculo v = new Veiculo();
			ValetBO vbo = new ValetBO();
			VeiculoBO veibo = new VeiculoBO();

			sdf.applyPattern("HH:mm");
			Date entra = sdf.parse(req.getParameter("entrada"));
			Date sai = sdf.parse(req.getParameter("saida"));

			double preco = 0;
			v.setMarca(req.getParameter("marca"));
			v.setModelo(req.getParameter("modelo"));
			v.setPlaca(req.getParameter("placa"));
			Valet va = new Valet(v, entra, sai, preco);

			
			double total = 0;
			if (vbo.validarCheckinCheckout(va) == false) {
				out.println("<p>Horário inválido, tente novamente</p>");
			}
			if (veibo.validarPlaca(v.getPlaca()) == false) {
				out.println("<p>placa inválida, tente novamente colocando 7 dígitos</p>");
			}
			if (veibo.validarDados(v) == false) {
				out.println("Todos os campos deverão ser preenchidos!");
			}
			if (vbo.validarCheckinCheckout(va) == true && veibo.validarPlaca(v.getPlaca()) == true
					&& veibo.validarDados(v) == true) {
				dao.adcionarVeiculo(va);
				ArrayList<Valet> valets = new VeiculoDAO().consultarTodos();
				
				out.println("<h1>Histórico de veículos de hoje</h1>");
				
				
				for (Valet valet : valets) {
					
					double valor = vbo.calcularPagamento(va.getEntrada(), va.getSaida());
					va.setPreco(valor);
					total += va.getPreco();
					
					out.println("<div class=\"wrapper-item\">\n" + 
							"    <h3>Placa: <strong> "+ valet.getVeiculo().getPlaca() +" </strong> </h3>\n" + 
							"    <h3>Marca do Carro: <strong>"+ valet.getVeiculo().getMarca() +"</strong> </h3>\n" + 
							"    <h3>Modelo do carro: <strong>"+ valet.getVeiculo().getModelo() +"</strong> </h3>\n" + 
							"    <h3>Hora de entrada: <strong> "+ sdf.format(valet.getEntrada()) +" </strong> </h3>\n" + 
							"    <h3>Hora de saída: <strong>"+ sdf.format(valet.getSaida()) +"</strong> </h3>\n" + 
							"    <h3>Valor a ser Pago: <strong>"+ valet.getPreco() +"</strong> </h3>\n" + 
							"</div>");
					out.println("<div class='content-fixed'> <h5 class='total'> Lucro de hoje <strong>R$ "+ total +"</strong><h5>");
					out.println("<a href=\"cadastrar-veiculo.html\">Atualizar novo veiculo</a></div>");
				}
			}
			
			out.println("</body></html>");
			out.flush();
			out.close();
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Conversão falha");
		}
	}
}
