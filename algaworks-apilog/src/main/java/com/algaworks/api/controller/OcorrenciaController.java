package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.assembler.OcorrenciaAssembler;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.Ocorrencia;
import com.algaworks.api.model.OcorrenciaModel;
import com.algaworks.api.model.input.OcorrenciaInput;
import com.algaworks.api.service.BuscarEntregaService;
import com.algaworks.api.service.RegistraOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{idEntrega}/ocorrencias")
public class OcorrenciaController {
	
	private BuscarEntregaService buscarEntregaService;
	private RegistraOcorrenciaService registraOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrarOcorrencia(@PathVariable Long idEntrega,
											   @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		
		//Esta linha retorna um objeto do tipo ocorrencia, mas queremos um ocorrenciaModel, entao chamamos um assembler
		Ocorrencia ocorrencia = registraOcorrenciaService.registrar(ocorrenciaInput.getDescricao(), idEntrega);
		
		//Aqui chamamos o assembler para converter ocorrencia em ocorrenciaModel
		return ocorrenciaAssembler.toModel(ocorrencia);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listarOcorrencias(@PathVariable Long idEntrega){
		Entrega entrega = buscarEntregaService.buscar(idEntrega);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias()); 
	}
	
}
