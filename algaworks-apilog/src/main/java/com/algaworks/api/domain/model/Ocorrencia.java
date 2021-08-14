package com.algaworks.api.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Ocorrencia {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@EqualsAndHashCode.Include
		private Long id;
		
		
		private String descricao;
		
		@ManyToOne//varias ocorrencias para uma entrega
		private Entrega entrega;
		
		
		private OffsetDateTime dataOcorrencia;
}
