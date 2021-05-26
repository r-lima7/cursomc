package com.rlima.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rlima.cursomc.domain.Cliente;
import com.rlima.cursomc.services.ClienteService;

// Controlador REST - acessa a classe service - recebe as requisiçoes de aplicações - comunica dados por Json ou Xml
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // O value define que sera recebido um parametro, aqui é um id
	public ResponseEntity<?> find(@PathVariable Integer id) { // PathVariable declara que o id recebido sera inserido na variavel
		// poderia ser usado aqui um bloco try catch, porem isso sujaria o codigo, a ideia é que o controlador tenha clean code
		Cliente obj = service.buscar(id); //acessando a classe service e usar o metodo criado na classe ClienteService
		return ResponseEntity.ok().body(obj); // verifica se a operação foi feita com sucesso.		
			
	}
	
	
}
