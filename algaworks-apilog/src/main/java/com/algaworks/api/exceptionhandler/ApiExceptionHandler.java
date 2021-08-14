package com.algaworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.api.domain.exception.ExceptionEntidadeNaoEncontrada;
import com.algaworks.api.domain.exception.ExceptionNegocio;
import com.algaworks.api.exceptionhandler.Problema.Campo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Este atributo serve para puxarmos a mensagem customizada do messages.properties
	private MessageSource messageSource;

	//tratando a exception MethodArgumentNotValid() e customizando nossa resposta ao erro
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatus status, 
																  WebRequest request) {
		
		//Aqui instância-se um objeto Problema, que foi criado com a funcionalidade
		//de deixar o Erro mais compreensivel ao cliente
		Problema problema = new Problema();
		
		//Aqui criamos uma nova lista de campos,
		//a classe campo foi definida dentro da classe Problema
		//e aqui iremos criar uma lista do objeto Campo, que
		//tem como atributos nome e mensagem
		List<Problema.Campo> campos = new ArrayList<>();
		
		//Aqui eu atribuo os valores Status, DataHora, Titulo e Campos para o
		//objeto Problema
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(" [ERRO] Um ou mais campos foram preenchidos incorretamente, favor verificar e tentar novamente [!]");
		problema.setCampos(campos);
		
		//este for vai criar uma lista com os campos que estao com erro
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();//pegando o nome do campo
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());//pegando a mensagem do erro customizada no messages.properties /src/main/resources/messages.properties
			campos.add(new Campo(nome, mensagem));//Adicionando o campo com erro na lista
		}
		
		//Retorno o objeto no body do erro >> handleExceptionInternal(ex, problema, [Object], status, request) <<
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	//Tratando Exception para retornar 404 // Not found
	@ExceptionHandler(ExceptionEntidadeNaoEncontrada.class)//Mostra para o codigo que o erro ExceptionEmail tem que ser tratado desta maneira quando for lançado
	public ResponseEntity<Object> handleExceptionEntidadeNaoEncontrada(ExceptionNegocio ex, WebRequest request){
		Problema problema = new Problema();
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema,new HttpHeaders(), status, request);
	}
	
	//Tratando Exception para retornar 400 // Bad Request
	@ExceptionHandler(ExceptionNegocio.class)//Mostra para o codigo que o erro ExceptionEmail tem que ser tratado desta maneira quando for lançado
	public ResponseEntity<Object> handleExceptionEmail(ExceptionNegocio ex, WebRequest request){
		Problema problema = new Problema();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema,new HttpHeaders(), status, request);
	}
}
