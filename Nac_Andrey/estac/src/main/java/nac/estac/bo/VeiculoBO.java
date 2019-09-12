package nac.estac.bo;

import nac.estac.entities.Veiculo;

public class VeiculoBO {

	Veiculo v = new Veiculo();
	public boolean validarPlaca(String placa) {
		if(placa.length() == 7) {
			return true;
		}
		return false;
	}
	public boolean validarDados(Veiculo v) {
		if(v.getMarca() != null || v.getModelo() != null || v.getPlaca() != null) {
			return true;
		}else {
			return false;
		}
	}
}
