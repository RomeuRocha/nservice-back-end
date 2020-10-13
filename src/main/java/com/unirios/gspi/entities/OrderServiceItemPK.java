package com.unirios.gspi.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
public class OrderServiceItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="ordemServico_id")
	private OrderOfService orderService;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	

	
	
}
