package com.algaworks.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModel {
	
	private Long id;
	private OffsetDateTime dataOcorrencia;
	private String descricao;
	
}
