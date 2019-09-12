package nac.estac.dao;

import java.util.ArrayList;

import nac.estac.entities.Valet;


public class VeiculoDAO {
	
	private static ArrayList<Valet> valets = new ArrayList<Valet>();
	
	public void adcionarVeiculo(Valet valet) {
//		if(v != null) 
		valets.add(valet);
	}
	
	public ArrayList<Valet> consultarTodos() {
		return valets;
	}
	
	public Valet consultarPlaca(String pla) {
		for(Valet v: valets) {
			if(v.getVeiculo().getPlaca().equals(pla)) {
				return v;
			}
		}
		return null;
	}
}