package com.algaworks.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.api.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid//Essa annotation serve para dizer que o objeto Cliente sera validado
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)//Essa annotation serve para converter o grupo de validação Default para o grupo que criei "ValidationGroups.ClienteId"
	@NotNull
	@ManyToOne//essa anotação faz um relacionamento com a entidade cliente, ou seja "Muitos para um", podem ocorrer Muitas entregas para Um cliente
	private Cliente cliente;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)//Aqui fazemos o inverso do que fizemos na entidade ocorrencia
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Valid
	@NotNull
	@Embedded//essa anotação significa que as colunas do destinatario estarão presentes na classe Entrega, fazemos isso para nao ter que deixar a classe Entrega cheia de atributos
	private Destinatario destinatario;
	
	@JsonProperty(access = Access.READ_ONLY)//esta anotação significa que o usuário nao poderá passar essa propriedade no json, apenas fazer a leiura dela
	@Enumerated(EnumType.STRING)//esta anotação significa que iremos armazenar na coluna do banco o proprio texto da classe enum, "PENDENTE, FINALIZADA, CANCELADA"
	private StatusEntrega status;
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	//metodo que adiciona uma ocorrencia a entrega
	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDataOcorrencia(OffsetDateTime.now());
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		
		//adiciona a ocorrencia criada na lista ocorrencias desta entidade
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}
}
