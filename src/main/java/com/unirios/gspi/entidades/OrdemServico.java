package com.unirios.gspi.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unirios.gspi.entities.Enuns.Status;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor @EqualsAndHashCode(of = "id")

@Entity
@Table(name = "Ordem_de_servico")
public class OrdemServico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@OneToMany(mappedBy = "id.orderService")
	private Set<ItemService> servicesItens = new HashSet<ItemService>();

	@Getter @Setter
	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario collaborator;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "assunto_id")
	private Assunto subject;
	
	@Getter @Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant saveMoment;//momento salvo;//deverá ser feito em trigger
	
	@Getter @Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant dateSchedule;//data e hora do agendamento
	
	@Getter @Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant attendance;//data e hora do atendimento
	
	private Integer situation;

	public OrdemServico(Long id, Funcionario collaborator, Assunto subject,
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

	public void AddItemService(ItemService itemService) {
		servicesItens.add(itemService);
	}
	
}
