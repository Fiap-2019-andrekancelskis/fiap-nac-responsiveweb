package nac.estac.entities;

import java.util.Date;

public class Valet {

	private Veiculo veiculo;
	private Date entrada;
	private Date saida;
	private double preco;

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Valet(Veiculo veiculo, Date entrada, Date saida, double preco) {
		super();
		this.veiculo = veiculo;
		this.entrada = entrada;
		this.saida = saida;
		this.preco = preco;
	}


}
