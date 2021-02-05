package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;

public class PatrimonioForm {

	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 1)
	private String descricao;

	public PatrimonioForm() {
	}

	public PatrimonioForm(Patrimonio patrimonio) {
		this.nome = patrimonio.getNome();
		this.descricao = patrimonio.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Patrimonio toPatrimonio() {

		return new Patrimonio(this.getNome(), this.descricao);
	}

}
