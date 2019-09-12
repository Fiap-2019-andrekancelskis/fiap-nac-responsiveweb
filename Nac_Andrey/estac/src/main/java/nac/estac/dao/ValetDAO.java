package nac.estac.dao;

import java.util.ArrayList;

import nac.estac.entities.Valet;



public class ValetDAO {

	private static ArrayList<Valet> VALET = new ArrayList<>();
	
	public ArrayList<Valet> consultarEstacionado(){
		return VALET;
	}
}
