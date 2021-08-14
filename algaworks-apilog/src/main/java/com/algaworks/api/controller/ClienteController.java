package com.algaworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.repository.ClienteRepository;
import com.algaworks.api.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long idCliente){
		
		return clienteRepository.findById(idCliente).map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());

				//>> Podemos fazer tambem desta maneira <<
		
//		//O optional retorna um container com o conteudo que foi buscado,
//		//se nao tiver nenhum conteudo o container fica vazio
//		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
//		
//		/*
//		 * Aqui verificamos se o container do optional declarado acima
//		 * esta vazio ou nao, se tiver vazio retorna false, se tiver
//		 * conteudo retorna true
//		 */
//		if(cliente.isPresent()) {
//			
//			/*
//			 * O response Entity vai ser responsavel por retornar os status
//			 * http, neste caso "ResponseEntity.ok(cliente.get())" gera o codigo
//			 * 200 ao receber a requisição e retorna o cliente que foi filtrado
//			 * pelo Id 
//			 */
//			return ResponseEntity.ok(cliente.get());
//		} else {
//			
//			/*
//			 * Aqui construimos uma ResponseEntity com o codigo 404,
//			 * ou seja, dizemos ao cliente que ao cair neste bloco de codigo,
//			 * quer dizer que nao tem nenhum conteudo no container do optional,
//			 * ele irá gerar um erro de Not Found 404.
//			 * 
//			 * O metodo "build" precisa ser chamado neste caso
//			 */
//			return ResponseEntity.notFound().build();
//		}
		
							// >> FIM <<
	
		
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		
		//aqui estamos delegando a responsabilidade de salvar no banco de dados para
		//a classe service, pois ela possui as regras de negocio implementadas
		return clienteService.salvarCliente(cliente);
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long idCliente, 
											 @Valid @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(idCliente);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> excluir(@PathVariable Long idCliente){
		if(!clienteRepository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(idCliente);
		return ResponseEntity.noContent().build();
		
	}
}
