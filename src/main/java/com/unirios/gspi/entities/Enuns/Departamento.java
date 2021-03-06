package com.unirios.gspi.entities.Enuns;

public enum Departamento {
	
	ADIMINISTRATION(1, "Administraçao"),
	ATTENDANCE(2, "Atendimento"),
	SUPPORT(3,"Suporte");
	
	private int cod;
	private String descricao;
	
	private Departamento (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Departamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Departamento x : Departamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
