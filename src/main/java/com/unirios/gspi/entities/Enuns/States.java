package com.unirios.gspi.entities.Enuns;

public enum States {
	
	AC(1, "Acre"),
	AL(2, "Alagoas"),
	AP(3, "Amapa"),
	AM(4, "Amazonas"),
	BA(5,"Bahia"),
	CE(6,"Ceara"),
	DF(7,"Distrito Federal"),
	ES(8,"Espirito_Santo"),
	GO(9,"Goias"),
	MA(10,"Maranhão"),
	MT(11, "Mato_grosso"),
	MS(12, "Mato_grosso_do_sul"),
	MG(13,"Minas_Gerais"),
	PA(14,"Para"),
	PB(15,"Paraiba"),
	PR(16,"Parana"),
	PE(17,"Pernambuco"),
	PI(18, "Piaui"),
	RJ(19,"Rio_de_Janeiro"),
	RN(20,"Rio_Grande_do_Norte"),
	RS(21,"Rio_Grande_do_Sul"),
	RO(22,"Rondonia"),
	RR(23,"Roraima"),
	SC(24, "Santa_Catarina"),
	SP(25,"Sao_Paulo"),
	SE(26,"Sergipe"),
	TO(27,"Tocantins");
	
	private int code;
	private String nome;
	
	private States(int code, String nome) {
		this.code = code;
		this.nome = nome;
	}
	
	public int getCode() {
		return code;
	}
	public String getNome() {
		return nome;
	}
	
	public static States valueOf(int code) {
		for(States value : States.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalede status");
	}
}
