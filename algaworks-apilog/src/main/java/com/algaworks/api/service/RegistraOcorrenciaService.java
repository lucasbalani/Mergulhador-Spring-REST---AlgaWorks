package com.algaworks.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

//classe responsavel por solicitar um registro de ocorrencia
@AllArgsConstructor
@Service
public class RegistraOcorrenciaService {
	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public Ocorrencia registrar(String descricao, Long entregaId) {
		Entrega entrega = buscarEntregaService.buscar(entregaId);
		
		//chama o metodo adicionarOcorrencia no objeto entrega para adicionar a ocorrencia a lista de ocorrencias de uma entrega
		return entrega.adicionarOcorrencia(descricao);	
	}
	
}
