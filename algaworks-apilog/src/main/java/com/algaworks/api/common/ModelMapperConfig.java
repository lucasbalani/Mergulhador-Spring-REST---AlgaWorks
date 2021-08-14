package com.algaworks.api.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//esta classe irá configurar o model mapper para o spring reconhece-lo como um componente.
//Pelo fato do Model Mapper ser uma biblioteca independente, precisamos dizer ao spring que,
//esta classe ira configurar o model mapper como um bean, ai o spring irá reconhece-lo e
//vai conseguir injetar esta classe

@Configuration //Digo ao spring que esta será uma classe de configuração
public class ModelMapperConfig {

	@Bean//esta anotação diz que este metodo é um bean do spring ou seja, vira um componente
	public ModelMapper configurar() {
		return new ModelMapper();
	}
}
