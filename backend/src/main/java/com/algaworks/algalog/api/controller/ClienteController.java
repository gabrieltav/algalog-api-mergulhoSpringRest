package com.algaworks.algalog.api.controller;

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

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClientesService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalogoClientesService catalogoClientesService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente))
//ou				
				.map(ResponseEntity::ok) //cod 200
				.orElse(ResponseEntity.notFound().build()); //Retorna o cod 404
		
//ou
		
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClientesService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
			@Valid @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {//Não existe?
			return ResponseEntity.notFound().build();//Retorna o cod 404
		}
		
		cliente.setId(clienteId);
		cliente = catalogoClientesService.salvar(cliente);
		return ResponseEntity.ok(cliente);//cod 200
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) { //Não existe?
			return ResponseEntity.notFound().build();	//Retorna o cod 404
		}
		
		catalogoClientesService.excluir(clienteId);
		
		return ResponseEntity.noContent().build(); //cod 204 sucesso
	}
	
}
