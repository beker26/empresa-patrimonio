package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.marca.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Marca;

public class MarcaForm {

	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String nome;

	public MarcaForm() {
	}

	public MarcaForm(Marca marca) {
		this.nome = marca.getNome();
	}

	public String getNome() {
		return nome;
	}

	public Marca toMarca() {

		return new Marca(this.getNome());
	}

}
