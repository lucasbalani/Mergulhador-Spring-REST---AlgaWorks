package com.algaworks.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private BuscarEntregaService buscarEntregaService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar(Long idEntrega) {
		//[...] Implementar
	}
}
