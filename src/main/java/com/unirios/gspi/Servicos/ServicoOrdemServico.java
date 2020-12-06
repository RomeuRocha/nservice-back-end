package com.unirios.gspi.Servicos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.Grafico1DTO;
import com.unirios.gspi.dto.OrdemServicoDTO;
import com.unirios.gspi.dto.ServicoDTO;
import com.unirios.gspi.entidades.ItemService;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.entidades.Servico;
import com.unirios.gspi.repositorios.RepositorioItemServico;
import com.unirios.gspi.repositorios.RepositorioOrdemServico;
import com.unirios.gspi.repositorios.RepositorioServico;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoOrdemServico {

	@Autowired
	private RepositorioOrdemServico repo;

	@Autowired
	private RepositorioItemServico itemRepo;

	@Autowired
	private RepositorioServico serviceRepository;

	public List<OrdemServico> findAll() {
		return repo.findAll();
	}

	public OrdemServico findById(Long id) {
		Optional<OrdemServico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}

	public OrdemServico insert(OrdemServico obj) {
		obj.setId(null);// garantir o insert, pois se tiver id, ele faz update

		obj = repo.save(obj);
		for (ItemService is : obj.getServicesItens()) {

			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderService(obj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(obj.getServicesItens());

		return obj;
	}

	public OrdemServico update(OrdemServico obj) {
		
		OrdemServico newObj = findById(obj.getId());

		for (ItemService is : newObj.getServicesItens()) {
			itemRepo.delete(is);// apaga os antigos itens
		}
		updateData(newObj, obj);

		for (ItemService is : newObj.getServicesItens()) {

			is.setValue(serviceRepository.findById(is.getService().getId()).get().getValue());
			is.setOrderService(newObj);
			is.setService(is.getService());
		}
		itemRepo.saveAll(newObj.getServicesItens());// salva os novos itens
		newObj = repo.save(newObj);

		return newObj;
	}

	public OrdemServico delete(Long id) {
		OrdemServico obj = findById(id);// ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return obj;
		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
			throw new DataIntegrityException(
					"Não é possível excluir uma ordem de serviço que possui relação com outras entidades");
		}
	}

	private void updateData(OrdemServico newObj, OrdemServico obj) {
		newObj.setAttendance(obj.getAttendance());
		newObj.setCollaborator(obj.getCollaborator());
		newObj.setDateSchedule(obj.getDateSchedule());
		newObj.setServicesItens(obj.getServicesItens());
		newObj.setSaveMoment(obj.getSaveMoment());
		newObj.setSituation(obj.getSituation());
		newObj.setAssunto(obj.getAssunto());
		newObj.setCliente(obj.getCliente());
	}
	
	
	public Page<OrdemServico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String cliente,String assunto,Integer situacao,String dataInicial,String dataFinal) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		repo.findByServicesItensJoin();
		Page<OrdemServico> pages = null;
		Instant dtInicial = convertInstantFromString(dataInicial);
		Instant dtFinal = convertInstantFromString(dataFinal);
		
		if(situacao == 0 && dtInicial == null && dtFinal == null) {
			
			Instant datFinal =	Instant.now();
			
			Date now = new Date();
			now.setDate(1);
			now.setHours(-3);
			now.setMinutes(0);
			now.setSeconds(0);
			
			
			Instant inicial = now.toInstant();
			
			Instant agora = Instant.now();
			
			//filtro padrão (apenas por assunto e/ou cliente)
			pages = repo.findOSByClienteAndAssunto(cliente.toLowerCase(),assunto.toLowerCase(),inicial, datFinal,pageRequest);
			System.out.println("final = "+datFinal);
			System.out.println("inicial = "+inicial);
			System.out.println("agora = "+agora);
		}else if(situacao == 0 && dtInicial == null && dtFinal != null) {
			//filtro padrão + datafinal
			pages = repo.findOSByClienteAndAssuntoAndSituacaoAndDataFinal(cliente.toLowerCase(),assunto.toLowerCase(),dtFinal,pageRequest);
		}else if(situacao == 0 && dtFinal == null && dtInicial != null){
			//filtro padrão + dataInicial 
			pages = repo.findOSByClienteAndAssuntoAndDataInicial(cliente.toLowerCase(),assunto.toLowerCase(),dtInicial,new Date().toInstant(),pageRequest);
		}else if(situacao == 0 && dtInicial != null && dtFinal != null) {
			//filtro padrão + dataInicial + dataFinal
			pages = repo.findOSByClienteAndAssuntoAndDataInicialAndDataFinal(cliente.toLowerCase(),assunto.toLowerCase(),dtInicial,dtFinal,pageRequest);
		}else if(situacao != 0 && dtFinal == null && dtInicial != null ) {
			//filtro padrão  + situação + dataInicial
			pages = repo.findOSByClienteAndAssuntoAndSituacaoAndDataInicial(cliente.toLowerCase(),assunto.toLowerCase(),situacao,dtInicial,new Date().toInstant(),pageRequest);
		}else if(situacao != 0 && dtFinal != null && dtInicial == null ) {
			//filtro padrão + situação + dataFinal
			pages = repo.findOSByClienteAndAssuntoAndSituacaoAndDataFinal(cliente.toLowerCase(),assunto.toLowerCase(),situacao,dtFinal,pageRequest);

		}else if(situacao != 0 && dtFinal != null && dtInicial != null) {
			//filtro padrão + situação + dataInicial + dataFinal
			pages = repo.findOSByClienteAndAssuntoAndSituacaoAndDataInicial(cliente.toLowerCase(),assunto.toLowerCase(),situacao,dtInicial,dtFinal,pageRequest);
		}
		else {
			pages = repo.findOSByClienteAndAssuntoAndSituacao(cliente.toLowerCase(),assunto.toLowerCase(),situacao,pageRequest);
		}
		
		return pages;
	}
	
	public Page<OrdemServico> findPageAgenda(Integer page, Integer linesPerPage, String orderBy, String direction, String cliente) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		repo.findByServicesItensJoin();
		Page<OrdemServico> pages = null;
		pages = repo.findAll(pageRequest);
		
		return pages;
	}

	public OrdemServico fromDTO(OrdemServicoDTO dto) {
		OrdemServico os = new OrdemServico(dto.getId(), dto.getFuncionario(),dto.getCliente(), dto.getAssunto(), dto.getSaveMoment(),
				dto.getDateSchedule(), dto.getAttendance(), dto.getSituation());
		for(ServicoDTO servDTO : dto.getServicos()) {
			Servico s = new Servico(servDTO.getId(), servDTO.getDescription(), servDTO.getValue());
			os.getServicesItens().add(new ItemService(s, os, s.getValue()));
		}
		return os;
	}
	
	public Instant convertInstantFromString(String value) {
        Instant instant = null;
        if(value.equals("")) {
        	return null;
        }
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dte = sdf.parse(value);
			instant = dte.toInstant();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return instant;
	}
	public Integer osAbertas() {
		
		return null;
	}
	
	public List<Grafico1DTO> findGrafico1(Integer valor){
		List<OrdemServico> os = repo.osPorMes(valor);
		List<Grafico1DTO> list = new ArrayList<Grafico1DTO>();
		
		OrdemServico ordem = os.get(0);
		int mes = Date.from(ordem.getSaveMoment()).getMonth();
		
		for(int x=0; x <= 12; x++) {
		
			Grafico1DTO g1 = new Grafico1DTO(0,x+1, null);		
			for(OrdemServico oi: os) {
				int mesInt = Date.from(oi.getSaveMoment()).getMonth();
				
				if(x == mesInt) {
					g1.setLineValue(g1.getLineValue()+1);
					
				}
			
			}
			switch (g1.getArgument()) {
			case 1:
				g1.setMes("JAN");
				break;
			case 2:
				g1.setMes("FEV");
				break;
				
			case 3:
				g1.setMes("MAR");
				break;

			case 4:
				g1.setMes("ABR");
				break;

			case 5:
				g1.setMes("MAI");
				break;

			case 6:
				g1.setMes("JUN");
				break;

			case 7:
				g1.setMes("JUL");
				break;
			case 8:
				g1.setMes("AGO");
				break;

			case 9:
				g1.setMes("SET");
				break;

			case 10:
				g1.setMes("OUT");
				break;

			case 11:
				g1.setMes("NOV");
				break;

			case 12:
				g1.setMes("DEZ");
				break;


			default:
				break;
			}
			list.add(g1);
		
		}
		
		return list;
	}
	public List<OrdemServico> findAbertas(Integer valor){
		return repo.osPorMes(valor);
	}

}
