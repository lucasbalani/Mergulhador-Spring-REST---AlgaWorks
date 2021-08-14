package com.algaworks.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.model.EntregaModel;
import com.algaworks.api.model.input.EntregaInput;

import lombok.AllArgsConstructor;

/*
 * Esta classe será responsavel por montar o modelo de representação "Representation Model"
 * ou seja, passar os atributos de um objeto para um model
 */

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper modelMapper;
	
	//metodo que irá construir o model, ou seja, pegar os dados do objeto "entrega" e representar em um "EntregaModel"
	public EntregaModel montarModelo(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	//Metodo que transforma a lista de Entrega em uma lista de EntregaModel
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map(this::montarModelo)//Aqui estou referenciando o metodo da propria classe
				.collect(Collectors.toList());//[ESTUDAR] Collectors
	}
	
	//Metodo que transforma uma EntregaInput em uma entidade Entrega para ser salva no repositorio
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}

}
