package com.unirios.gspi.entities.Enuns;

public enum Estados {
	
	Acre(1),
	Alagoas(2),
	Amapa(3),
	Amazonas(4),
	Bahia(5),
	Ceara(6),
	Distrito_Federal(7),
	Espirito_Santo(8),
	Goias(9),
	Maranhão(10),
	Mato_grosso(11),
	Mato_grosso_do_sul(12),
	Minas_Gerais(13),
	Para(14),
	Paraiba(15),
	Parana(16),
	Pernambuco(17),
	Piaui(18),
	Rio_de_Janeiro(19),
	Rio_Grande_do_Norte(20),
	Rio_Grande_do_Sul(21),
	Rondonia(22),
	Roraima(23),
	Santa_Catarina(24),
	Sao_Paulo(25),
	Sergipe(26),
	Tocantins(27);
	
	private int code;
	
	private Estados(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Estados valueOf(int code) {
		for(Estados value : Estados.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalede status");
	}
}
