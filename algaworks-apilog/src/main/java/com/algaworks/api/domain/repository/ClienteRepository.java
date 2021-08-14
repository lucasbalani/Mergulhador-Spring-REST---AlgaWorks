package com.algaworks.api.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.api.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	List<Cliente> findByNome(String nome);
	
	/*
	 * 			Metodo Containing
	 * 
	 * Este metodo procura no repositório
	 * um objeto pelo atributo nome, mas
	 * diferente do metodo acima, este metodo
	 * pode encontrar um objeto com apenas
	 * uma parte do atributo nome
	 * 
	 * 	EX: Ao inves de procurar pelo nome completo,
	 * 		"Claudia Mirelli" para localizar o objeto
	 * 		posso pesquisar somente "Mire", que com isso
	 * 		ja me trará o objeto com o nome inteiro
	 */
	List<Cliente> findByNomeContaining(String nome);
	Optional<Cliente> findByEmail(String email);
}
