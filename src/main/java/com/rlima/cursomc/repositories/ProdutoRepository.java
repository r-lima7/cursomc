package com.rlima.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rlima.cursomc.domain.Produto;

//Camada de acesso a dados (DAO)
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	

}
