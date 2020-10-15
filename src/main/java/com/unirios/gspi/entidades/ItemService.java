package com.unirios.gspi.entidades;

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
public class ItemService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Getter @Setter
	@EmbeddedId
	private ItemServicePK id = new ItemServicePK();
	
	@Getter @Setter
	private Float value;
	
	public ItemService(Service service, OrdemServico orderService, Float value) {
		
		this.id.setOrderService(orderService);
		this.id.setService(service);
		this.value = value;
			
	}
	
	
	public Service getService() {
		return id.getService();
	}
	
	public void setService(Service service) {
		id.setService(service);
	}
	
	@JsonIgnore
	public OrdemServico getOrderOfService() {
		return id.getOrderService();
	}
	
	public void setOrderOfService(OrdemServico os) {
		id.setOrderService(os);
	}
	
}
