package com.algaworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.api.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

/*
 * Utilizamos esta classe para mostrar somente estes atributos para o cliente no json, ocultando por exemplo as informações completas do cliente
 * onde poderia conter a senha do mesmo por exemplo, ai para nao aparecer todos os atributos do objeto cliente, representamos apenas o nome do cliente
 * com uma String e setamos esse nomeCliente la no controller no bloco da função lambda
 */
@Getter
@Setter
public class EntregaModel {

	private Long idEntrega;
	private ClienteResumoModel cliente;
	private DestinatarioModel destinatario;
	private StatusEntrega status;
	private BigDecimal frete;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
}
