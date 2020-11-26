package com.unirios.gspi.filtros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class FiltroOrdemServico {

	private String nomeCliente;
	
	private String assunto;
	
	private Integer situacao;
	
	private String dataInicial;
	
	private String dataFinal;
	
	
}
