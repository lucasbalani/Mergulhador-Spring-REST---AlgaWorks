package com.algaworks.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.api.domain.exception.ExceptionNegocio;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	private ClienteRepository clienteRepository;
	
	//Este metodo serve para buscar um cliente no repositorio e se nao tiver, lança uma Exception com a mensagem "Cliente nao encontrado"
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ExceptionNegocio("[ERRO] Cliente nao encontrado [!]"));
	}
	
	//Aqui iremos implementar todos os metodos CRUD
	//a diferença é que nesta classe iremos tratar as regras de negocio
	//para depois passar para o Controller.
	//O Transactional serve para que se alguma regra for quebrada dentro do metodo,
	//ele desfaz todas as alterações feita durante todo o metodo. OBS: Transactional é do spring
	@Transactional
	public Cliente salvarCliente(Cliente cliente) {
		//[...]Todas as regras de negocio aqui
		
							 //Este comando procura no repositório um cliente por email
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
							 //Este comando conseguimos trazer a API stream que é para controle de fluxo, ele vai pegar o container do optional e vai jogar o conteúdo para dentro do anyMatch.
							 .stream()
							 //Este comando irá retornar true ou false, se o clienteExistente for igual o objeto cliente que esta sendo passado como parametro, isso significa que é o mesmo cliente e so está sendo alterado o email.
							 .anyMatch(clienteExistente -> !clienteExistente.equals(cliente)/*clienteExistente é igual cliente?*/);
		
		
		if(emailEmUso) {
			throw new ExceptionNegocio("[ERRO] E-mail ja cadastrado [!]");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluirCliente(Long idCliente) {
		//[...]Todas as regras de negocio aqui
		clienteRepository.deleteById(idCliente);
	}
}
