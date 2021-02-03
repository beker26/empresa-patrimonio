package br.com.patrimonioempresa.apiescolapatrimonioempresa.repository.marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
