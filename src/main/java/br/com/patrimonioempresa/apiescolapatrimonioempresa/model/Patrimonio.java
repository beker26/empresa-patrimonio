package br.com.patrimonioempresa.apiescolapatrimonioempresa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patrimonio")
public class Patrimonio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private Long numeroDoTombo;
	@ManyToOne
	@JoinColumn(name = "idMarca")
	private Marca marca;

	public Patrimonio() {
	}

	public Patrimonio(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getNumeroDoTombo() {
		return numeroDoTombo;
	}

	public void setNumeroDoTombo(Long numeroDoTombo) {
		this.numeroDoTombo = numeroDoTombo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patrimonio other = (Patrimonio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void updatePatrimonio(Patrimonio patrimonioForm) {
		this.nome = patrimonioForm.getNome();
		this.descricao = patrimonioForm.getDescricao();
	}

	public Object getMarcaId() {
		return this.getMarca().getId();
	}

	

}
