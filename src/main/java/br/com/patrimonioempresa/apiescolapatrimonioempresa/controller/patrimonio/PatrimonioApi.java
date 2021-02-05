package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.PatrimonioDetailDto;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.PatrimonioDto;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.form.PatrimonioForm;



public interface PatrimonioApi {
	
	ResponseEntity<List<PatrimonioDto>> findAll();

	ResponseEntity<PatrimonioDetailDto> findById(@PathVariable Integer patrimonioId);

	ResponseEntity<PatrimonioDetailDto> save(@PathVariable Integer marcaId, @Validated @RequestBody PatrimonioForm patrimonioForm, UriComponentsBuilder uriBuilder);

	ResponseEntity<PatrimonioDto> update( @PathVariable Integer patrimonioId, @RequestBody @Validated PatrimonioForm patrimonioForm);

	ResponseEntity<Void> delete( @PathVariable Integer patrimonioId);

}
