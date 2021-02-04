package br.com.patrimonioempresa.apiescolapatrimonioempresa.service.patrimonio;

import java.util.List;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;



public interface PatrimonioService {
	
	List<Patrimonio> findAll();

	Patrimonio findById(Integer patrimonioId);

	Patrimonio save(Patrimonio patrimonio);

	Patrimonio update(Integer patrimonioId, Patrimonio patrimonioForm);

	void delete(Integer patrimonioId);
	
}
