package com.algaworks.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.api.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Essa annotation traz pra classe os metodos Equals e os HashCodes apenas 
//dos atributos que possuem "EqualsAndHashCode.Include",
//outra solução é tambem gerar os equals e os Hashcodes através da IDE
//mas utilizamos a anottation para deixar um codigo mais limpo
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	
	@NotNull(groups = ValidationGroups.ClienteId.class)//Aqui marcamos que este atributo faz parte do grupo de validação "ValidationGroups.ClienteId", ou seja, so vai ser validado quando o bean for validar o grupo "ValidationGroups.ClienteId"
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "fone")
	private String telefone;
	

	
	
}
