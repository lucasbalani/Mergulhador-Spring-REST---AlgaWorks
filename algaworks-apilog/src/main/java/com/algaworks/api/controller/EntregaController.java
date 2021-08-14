package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.assembler.EntregaAssembler;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.repository.EntregaRepository;
import com.algaworks.api.model.EntregaModel;
import com.algaworks.api.model.input.EntregaInput;
import com.algaworks.api.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaAssembler entregaAssembler;//montador do modelo de representação
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);//Estou convertendo a EntregaInput em uma entidade Entrega
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitarEntrega(novaEntrega);//Estou solicitando uma entrega
		return entregaAssembler.montarModelo(entregaSolicitada);//estou convertendo a entidade Entrega para EntregaModel
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long idCliente){
		return entregaRepository.findById(idCliente)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.montarModelo(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}
