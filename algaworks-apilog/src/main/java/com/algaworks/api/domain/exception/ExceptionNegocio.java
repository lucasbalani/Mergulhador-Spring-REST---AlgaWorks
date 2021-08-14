package com.algaworks.api.domain.exception;


public class ExceptionNegocio extends RuntimeException{

	//precisa gerar este serialVersionUID que a IDE recomenda
	private static final long serialVersionUID = 1L;
	
	//crio um construtor desta classe e passo a mensagem do erro como parametro
	public ExceptionNegocio(String message) {
		super(message);
	}
	
	
	
}
