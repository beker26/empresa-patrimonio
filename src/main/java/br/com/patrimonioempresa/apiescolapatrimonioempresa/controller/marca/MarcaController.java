package br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.marca;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.marca.dto.MarcaDto;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.controller.marca.dto.MarcaForm;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Marca;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.service.marca.MarcaService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/v1/patrimonio/{patrimonioId}/marca")
@Log4j2
public class MarcaController implements MarcaApi {
	private MarcaService marcaService;

	public MarcaController(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<MarcaDto>> findAll() {
		log.info("Starting Method FindAll in MarcaController!");
		List<Marca> listMarca = this.marcaService.findAll();
		List<MarcaDto> listMarcaDto = listMarca.stream().map(m -> new MarcaDto(m)).collect(Collectors.toList());
		log.info("Finishing Method Findall in MarcaController!");
		return ResponseEntity.ok().body(listMarcaDto);
	}

	@GetMapping(value = "/{alunoId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<MarcaDto> findById(Integer marcaId) {
		log.info("Starting Method findById in MarcaController!");
		log.info("Parameter id = {}", marcaId);
		Marca marca = marcaService.findById(marcaId);
		log.info("Converting Marca to MarcaDto");
		return ResponseEntity.ok().body(new MarcaDto(marca));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<MarcaDto> save(MarcaForm marcaForm, UriComponentsBuilder uriBuilder) {
		log.info("Starting Method insert in MarcaController!");
		log.info("Form: {}", marcaForm);
		Marca marca = marcaService.save(marcaForm.toMarca());
		log.info("Finishing Method insert in MarcaController!");
		URI uri = uriBuilder.path("/marca/{marcaId}").buildAndExpand(marca.getId()).toUri();
		return ResponseEntity.created(uri).body(new MarcaDto(marca));
	}

	@PutMapping("/{marcaId}")
	@Transactional
	public ResponseEntity<MarcaDto> update(Integer marcaId, MarcaForm marcaForm) {
		log.info("Starting Method Update in MarcaController!");
		log.info("Form: {}", marcaForm);
		marcaService.update(marcaId, marcaForm.toMarca());
		log.info("Finishing Method Update in MarcaController!");
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{marcaId}")
	@Transactional
	public ResponseEntity<Void> delete(Integer marcaId) {
		log.info("Starting Method Delete in MarcaController!");
		marcaService.delete(marcaId);
		log.info("Finishing Method Delete in MarcaController!");
		return ResponseEntity.noContent().build();
	}

}
