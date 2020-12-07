package com.unirios.gspi.Servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.unirios.gspi.dto.AdesaoDTO;
import com.unirios.gspi.dto.Grafico1DTO;
import com.unirios.gspi.dto.Grafico2DTO;
import com.unirios.gspi.entidades.Adesao;
import com.unirios.gspi.entidades.Endereco;
import com.unirios.gspi.entidades.OrdemServico;
import com.unirios.gspi.repositorios.RepositorioAdesao;
import com.unirios.gspi.repositorios.RepositorioEndereco;
import com.unirios.gspi.services.exceptions.DataIntegrityException;
import com.unirios.gspi.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoAdesao {

	@Autowired
	private RepositorioAdesao repo;
	
	@Autowired
	private ServicoEndereco serviceEndereco;

	
	
	public List<Adesao> findAll(){
		return repo.findAll();
	}
	
	public Adesao findById(Long id) {
		Optional<Adesao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " +Adesao.class.getName()));	
	}
	
	public Adesao insert(Adesao obj) {
		obj.setId(null);//garantir o insert, pois se tiver id, ele faz update
		return repo.save(obj);
	}
	
	public Adesao update(Adesao obj) {
		Adesao newObj = findById(obj.getId());
		Endereco newEnd = serviceEndereco.findById(obj.getEndereco().getId());
		
		updateDataEndereco(newEnd,obj.getEndereco());
		serviceEndereco.update(newEnd);
		
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	private void updateDataEndereco(Endereco newEnd, Endereco endereco) {
		newEnd.setBairro(endereco.getBairro());
		newEnd.setCep(endereco.getCep());
		newEnd.setCidade(endereco.getCidade());
		newEnd.setNumero(endereco.getNumero());
		newEnd.setRua(endereco.getRua());
	}

	public Adesao delete(Long id) {
		Adesao a = findById(id);//ou existe, ou irá gerar exception
		try {
			repo.deleteById(id);
			return a;
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir umAdesao que possui relação com Ordem de serviço");
		}
	}
	
	public Adesao fromDto(AdesaoDTO subjectDTO) {
		return new Adesao(subjectDTO.getId(), subjectDTO.getData().toInstant(),subjectDTO.getPlano(), 
				subjectDTO.getCancelamento(), subjectDTO.getEndereco(), subjectDTO.getCliente());
	}
	
	private void updateData(Adesao newObj,Adesao obj) {
		newObj.setData(obj.getData());
		newObj.setPlano(obj.getPlano());
		newObj.setCancelamento(obj.getCancelamento());
		newObj.setEndereco(obj.getEndereco());
		newObj.setCliente(obj.getCliente());
	}
	
	public Page<Adesao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String field) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.listarAdesoesPaginados(field.toLowerCase(),pageRequest);
	}

	public Long findActive() {
		return repo.adesoesAtivas();
	}
	
	public List<Grafico2DTO> findGrafico2(){
		List<Adesao> ad = repo.findAll();
		List<Grafico2DTO> list = new ArrayList<Grafico2DTO>();
		
		Adesao adesao = ad.get(0);
		int mes = Date.from(adesao.getData()).getMonth();
		
		for(int x=0; x < 12; x++) {
		
			Grafico2DTO g1 = new Grafico2DTO(0,x+1, null);		
			for(Adesao oi: ad) {
				int mesInt = Date.from(oi.getData()).getMonth();
				
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
	
}
