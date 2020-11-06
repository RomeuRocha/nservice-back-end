package com.unirios.gspi.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
public class ItemService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ordemServico_id")
	private OrdemServico orderService;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Servico service;

	@Getter
	@Setter
	private Float value;

	public ItemService(Servico service, OrdemServico orderService, Float value) {

		this.orderService = orderService;
		this.service = service;
		this.value = value;

	}

}
