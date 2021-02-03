package br.com.patrimonioempresa.apiescolapatrimonioempresa.repository.patrimonio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer>  {

}
