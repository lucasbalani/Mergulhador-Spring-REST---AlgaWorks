package com.algaworks.api.service;

import org.springframework.stereotype.Service;

import com.algaworks.api.domain.exception.ExceptionEntidadeNaoEncontrada;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscarEntregaService {
	
	private EntregaRepository entregaRepository;
	
	//Como vamos ter que chamar varias vezes o metodo para buscar uma entrega no repositorio,
	//ao inves de implementarmos em cada vez que teremos que buscar, delegamos a responsabilidade
	//de busca para este service, ou seja, criamos um metodo aqui que ja vamos retornar se existe 
	//a entrega no repositorio, se nao existir vai lançar a exception
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
			   .orElseThrow(() -> new ExceptionEntidadeNaoEncontrada("Entrega nao encontrada"));
	}
	
}
