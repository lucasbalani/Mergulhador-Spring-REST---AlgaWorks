package com.algaworks.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {
	
	@Valid
	@NotNull
	private ClienteIdInput idCliente;
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario;
	
	@NotBlank
	private BigDecimal frete;
}
