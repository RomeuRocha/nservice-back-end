package com.unirios.gspi.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
public class OrderServiceItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Getter @Setter
	@EmbeddedId
	private OrderServiceItemPK id = new OrderServiceItemPK();
	
	@Getter @Setter
	private Float value;
	
	public OrderServiceItem(Service service, OrderOfService orderService, Float value) {
		
		this.id.setOrderService(orderService);
		this.id.setService(service);
		this.value = value;
		
		
	}
	
}
