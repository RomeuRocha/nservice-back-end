package com.unirios.gspi.entities.Enuns;

public enum Status {
	ANALISE(1,"Analise"),
	AGENDADO(2, "Agendado"),
	ASSUMIDO(3,"Assumido"),
	CONCLUIDO(4, "Concluído"),
	CANCELADO(5,"Cancelado");
	
	private int cod;
	private String descricao;
	
	private Status (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static Status toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Status x : Status.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
