package com.rlima.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlima.cursomc.domain.Pedido;
import com.rlima.cursomc.repositories.PedidoRepository;
import com.rlima.cursomc.services.exceptions.ObjectNotFoundException;

//Camada de serviço p/ consultas de categorias - utiliza camada de acesso a dados(repository) para realizar regras de negocio
//Define endPoint
@Service
public class PedidoService {
	
	@Autowired // Para instanciar automaticamente (injeção de dependencia ou inversao de controle)
	private PedidoRepository repo; // objeto da camada de acesso a dados
	
	public Pedido buscar (Integer id) { // Busca a categoria pelo Id indicado pelo controlador
		Optional<Pedido> obj = repo.findById(id); //Optional, objeto container
			//return obj.orElse(null); // impede a instancia de obj nulo - se existe ele retorna obj se não retorna o VALOR nulo		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	

}
