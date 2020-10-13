package com.unirios.gspi.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter

@Embeddable
public class ItemServicePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="ordemServico_id")
	private OrderOfService orderService;
	
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderService == null) ? 0 : orderService.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemServicePK other = (ItemServicePK) obj;
		if (orderService == null) {
			if (other.orderService != null)
				return false;
		} else if (!orderService.equals(other.orderService))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}
	

	
	
}
