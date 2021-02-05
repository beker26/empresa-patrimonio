package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;

public class PatrimonioDto {

	private Integer id;
	private String nome;
	private String descricao;
	private Long numeroDoTombo;

	public PatrimonioDto(Patrimonio patrimonio) {
		this.id = patrimonio.getId();
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
		this.numeroDoTombo = patrimonio.getNumeroDoTombo();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getNumeroDoTombo() {
		return numeroDoTombo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
