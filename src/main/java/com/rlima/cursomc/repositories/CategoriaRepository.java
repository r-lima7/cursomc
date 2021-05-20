package com.rlima.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rlima.cursomc.domain.Categoria;

//Camada de acesso a dados (DAO)
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	

}
