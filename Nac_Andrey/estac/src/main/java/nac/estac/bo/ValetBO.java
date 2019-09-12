package nac.estac.bo;

import java.util.Date;

import nac.estac.entities.Valet;



public class ValetBO {

	public boolean validarCheckinCheckout(Valet v) {
		if (v.getEntrada().before(v.getSaida())) {
			return true;
		} else {
			return false;
		}

	}

	public double calcularPagamento(Date entrada, Date saida) {
		double preco = 0.0;
		long tempoPermanecia = (saida.getTime() - entrada.getTime()) / 1000 / 60;
		long horas = tempoPermanecia / 60;
		long minutos = tempoPermanecia % 60;
		if (this.temHorasAdicionais(this.qtdeHorasAdicionais(horas))) {
			preco = 4.00;
			preco = preco + this.qtdeHorasAdicionais(horas) * 2.50;
			if (minutos > 0) {
				preco = preco + 2.50;
			}
		} else {
			preco = 4;
		}
		return preco;
	}

	public long qtdeHorasAdicionais(long horas) {
		return horas - 6;
	}

	public boolean temHorasAdicionais(long horasAdicionais) {
		return horasAdicionais > 0;
	}

}
