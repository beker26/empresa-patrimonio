package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.PatrimonioDetailDto;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.PatrimonioDto;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.patrimonio.dto.form.PatrimonioForm;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.service.patrimonio.PatrimonioService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/v1/marca/{marcaId}/patrimonio")
@Log4j2
public class PatrimonioController implements PatrimonioApi {

	private PatrimonioService patrimonioService;

	public PatrimonioController(PatrimonioService patrimonioService) {
		this.patrimonioService = patrimonioService;
	}
	

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<PatrimonioDto>> findAll() {
		log.info("Starting Method FindAll in PatrimonioController!");
		List<Patrimonio> listPatrimonio = this.patrimonioService.findAll();
		List<PatrimonioDto> patrimonioDto = listPatrimonio.stream().map(p -> new PatrimonioDto(p)).collect(Collectors.toList());
		log.info("Finishing Method Findall in PatrimonioController!");
		return ResponseEntity.ok().body(patrimonioDto);
	}

	@GetMapping(value = "/{patrimonioId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<PatrimonioDetailDto> findById(Integer patrimonioId) {
		log.info("Starting Method findById in PatrimonioController!");
		log.info("Parameter id = {}", patrimonioId);
		Patrimonio patrimonio = patrimonioService.findById(patrimonioId);
		log.info("Converting Patrimonio to PatrimonioController");
		return ResponseEntity.ok().body(new PatrimonioDetailDto(patrimonio));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PatrimonioDetailDto> save(@PathVariable Integer marcaId, @Validated @RequestBody PatrimonioForm patrimonioForm, UriComponentsBuilder uriBuilder) {
		log.info("Starting Method insert in PatrimonioController!");
		log.info("Form: {}", patrimonioForm);
		Patrimonio patrimonio = patrimonioService.save(marcaId, patrimonioForm.toPatrimonio());
		log.info("Finishing Method insert in PatrimonioController!");
		URI uri = uriBuilder.path("/marca/{marcaId}").buildAndExpand(patrimonio.getMarcaId(), patrimonio.getId()).toUri();
		return ResponseEntity.created(uri).body(new PatrimonioDetailDto(patrimonio));
	}

	@PutMapping("/{patrimonioId}")
	@Transactional
	public ResponseEntity<PatrimonioDto> update(@PathVariable Integer patrimonioId, @RequestBody @Validated PatrimonioForm patrimonioForm) {
		log.info("Starting Method Update in PatrimonioController!");
		log.info("Form: {}", patrimonioForm);
		patrimonioService.update(patrimonioId,patrimonioForm.toPatrimonio());
		log.info("Finishing Method Update in PatrimonioController!");
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{patrimonioId}")
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable Integer patrimonioId) {
		log.info("Starting Method Delete in PatrimonioController!");
		patrimonioService.delete(patrimonioId);
		log.info("Finishing Method Delete in PatrimonioController!");
		return ResponseEntity.noContent().build();
	}
	
}
