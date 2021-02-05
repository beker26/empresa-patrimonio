package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto;


import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;

public class PatrimonioDetailDto {

	private String nome;
	private String descricao;
	private Long numeroDoTombo;
	private String marca;

	public PatrimonioDetailDto(Patrimonio patrimonio) {
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
		this.numeroDoTombo = patrimonio.getNumeroDoTombo();
		this.marca = patrimonio.getMarca().getNome();
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getNumeroDoTombo() {
		return numeroDoTombo;
	}

	

}
