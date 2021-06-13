package com.rlima.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rlima.cursomc.domain.Cliente;
import com.rlima.cursomc.dto.ClienteDTO;
import com.rlima.cursomc.repositories.ClienteRepository;
import com.rlima.cursomc.services.exceptions.DataIntegrityException;
import com.rlima.cursomc.services.exceptions.ObjectNotFoundException;

//Camada de serviço p/ consultas de categorias - utiliza camada de acesso a dados(repository) para realizar regras de negocio
@Service
public class ClienteService {
	
	@Autowired // Para instanciar automaticamente (injeção de dependencia ou inversao de controle)
	private ClienteRepository repo; // objeto da camada de acesso a dados
	
	public Cliente find (Integer id) { // Busca a categoria pelo Id indicado pelo controlador
		Optional<Cliente> obj = repo.findById(id); //Optional, objeto container
			//return obj.orElse(null); // impede a instancia de obj nulo - se existe ele retorna obj se não retorna o VALOR nulo		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	/*public Cliente insert(Cliente obj) {
		obj.setId(null); // garantia
		return repo.save(obj);
	}*/
	
	public Cliente update (Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete (Integer id) {
		find(id); //verifica se existe		
		try {
			repo.deleteById(id); // metodo interno do spring data
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
			
		}
	}
	
	public List <Cliente> findAll() {
		return repo.findAll();
		
	}
	
	// Recurso de paginacao do spring data
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome() , objDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	

}
