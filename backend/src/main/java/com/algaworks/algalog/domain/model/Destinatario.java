package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
	
	@Column(name = "Destinatario_nome")
	private String nome;
	
	@Column(name = "Destinatario_logradouro")
	private String logradouro;
	
	@Column(name = "Destinatario_numero")
	private String numero;
	
	@Column(name = "Destinatario_complemento")
	private String complemento;
	
	@Column(name = "Destinatario_bairro")
	private String bairro;

}
