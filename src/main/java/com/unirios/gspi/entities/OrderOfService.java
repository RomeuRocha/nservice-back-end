package com.unirios.gspi.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.unirios.gspi.entities.Enuns.Status;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Ordem_de_servico")
public class OrderOfService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	private List<Service> services = new ArrayList<Service>();

	@Getter @Setter
	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Collaborator collaborator;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "assunto_id")
	private Subject subject;
	
	@Getter @Setter
	private Instant saveMoment;//momento salvo;//deverá ser feito em trigger
	
	@Getter @Setter
	private Instant dateSchedule;//data e hora do agendamento
	
	@Getter @Setter
	private Instant attendance;//data e hora do atendimento
	
	private Integer situation;

	public OrderOfService(Long id, Collaborator collaborator, Subject subject,
			Instant saveMoment, Instant dateSchedule, Instant attendance, Status situation) {
		super();
		this.id = id;
		this.collaborator = collaborator;
		this.subject = subject;
		this.saveMoment = saveMoment;
		this.dateSchedule = dateSchedule;
		this.attendance = attendance;
		this.situation = situation.getCod();
	}
	
	public Status getSituation() {
		return Status.toEnum(situation);
	}
	
	public void setSituation(Status status) {
		situation = status.getCod();
	}
	
	public void setService(Service service) {
		services.add(service);
	}
	

}
