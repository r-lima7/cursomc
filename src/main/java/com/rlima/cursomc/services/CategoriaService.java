package com.rlima.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlima.cursomc.domain.Categoria;
import com.rlima.cursomc.repositories.CategoriaRepository;

//Camada de serviço p/ consultas de categorias
@Service
public class CategoriaService {
	
	@Autowired // Para instanciar automaticamente (injeção de dependencia, inversao de controle)
	private CategoriaRepository repo;
	
	public Categoria buscar (Integer id) { // Busca a categoria pelo Id indicado pelo controlador
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
	}
	
	

}
