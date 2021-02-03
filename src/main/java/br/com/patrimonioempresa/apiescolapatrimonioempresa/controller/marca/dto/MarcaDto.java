package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.marca.dto;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Marca;

public class MarcaDto {

	private Integer id;
	private String nome;

	public MarcaDto(Marca marca) {
		this.id = marca.getId();
		this.nome = marca.getNome();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	

}
