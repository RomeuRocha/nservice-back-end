package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Adesao implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant data;
	
	@Getter @Setter
	private double valor;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "plano_id")
	private Plano plano;
	
	@Getter @Setter
	@OneToOne
	private Cancelamento cancelamento;
	
	@Getter @Setter
	@OneToOne
	private Endereco endereco;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Adesao(Long id, Instant data, double valor, Plano plano, Cancelamento cancelamento, Endereco endereco,
			Cliente cliente) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.plano = plano;
		this.cancelamento = cancelamento;
		this.endereco = endereco;
		this.cliente = cliente;
	}
	
	

}
