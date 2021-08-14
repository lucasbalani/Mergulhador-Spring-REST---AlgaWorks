package com.algaworks.api.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.StatusEntrega;
import com.algaworks.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

//Classe para executar regras de negocio ao realizar operações no repositorio
@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private ClienteService clienteService;
	private EntregaRepository entregaRepository;
	
	//Classe que solicita o registro da entrega no repositorio de entregas
	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());//busca um metodo la na classe ClienteService que chama "BUSCAR", la verificamos se o usuario existe no banco
		
		//Carrego as informações do cliente para nao ficar "null" no banco de dados
		entrega.setCliente(cliente);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setStatus(StatusEntrega.PENDENTE);
		
		return entregaRepository.save(entrega);
	}
}
