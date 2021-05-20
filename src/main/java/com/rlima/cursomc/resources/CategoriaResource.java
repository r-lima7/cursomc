package com.rlima.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rlima.cursomc.domain.Categoria;
import com.rlima.cursomc.services.CategoriaService;

// Controlador REST - acessa a classe service - recebe as requisiçoes de aplicações - comunica dados por Json ou Xml
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // O value define que sera recebido um parametro, aqui é um id
	public ResponseEntity<?> find(@PathVariable Integer id) { // PathVariable declara que o id recebido sera inserido na variavel
		Categoria obj = service.buscar(id); //acessando a classe service e usar o metodo criado na classe CategoriaService
		return ResponseEntity.ok().body(obj); // verifica se a operação foi feita com sucesso.
		
			
	}
	
	
}
