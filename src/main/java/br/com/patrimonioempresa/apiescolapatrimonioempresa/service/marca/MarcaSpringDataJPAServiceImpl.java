package br.com.patrimonioempresa.apiescolapatrimonioempresa.service.marca;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.exception.BusinessException;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Marca;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.repository.marca.MarcaRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MarcaSpringDataJPAServiceImpl implements MarcaService {

	private MarcaRepository marcaRepository;

	public MarcaSpringDataJPAServiceImpl(MarcaRepository marcaRepository) {
		this.marcaRepository = marcaRepository;
	}

	public List<Marca> findAll() {
		log.info("Starting Method findAll in MarcaSpringDataJPAServiceImpl");
		List<Marca> listMarca = this.marcaRepository.findAll();
		log.info("finishing Method findAll in  ALunoSpringDataJPAService");
		return listMarca;
	}

	@Override
	public Marca findById(Integer marcaId) {
		log.info("Starting Method findById in MarcaSpringDataJPAService");
		log.info("Parameter:{}", marcaId);
		log.info("Finding Marca by id on MarcaRepository");
		Marca marca = null;
		try {
			marca = this.marcaRepository.findById(marcaId)
					.orElseThrow(() -> new BusinessException(" A marca não existe"));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Finishing Method findById in MarcaSpringDataJPAService");
		return marca;
	}

	@Override
	public Marca save(Marca marca) {
		log.info("Starting Method save in MarcaSpringDataJPAService");
		log.info("Finishing Method save in MarcaSpringDataJPAService");
		return this.marcaRepository.save(marca);
	}

	@Override
	public Marca update(Integer marcaId, Marca marcaByForm) {

		log.info("Starting Method update in MarcaSpringDataJPAService");
		Marca marcaById = findById(marcaId);
		marcaById.updateMarca(marcaByForm);
		log.info("Save in MarcaRepository");
		this.marcaRepository.save(marcaById);
		log.info("Finishing Method save in MarcaSpringDataJPAService");
		return marcaById;
	}

	@Override
	public void delete(Integer marcaId) {
		log.info("Starting Method Delete in MarcaSpringDataJPAService");
		log.info("Parameter:Marca Id = {},", marcaId);
		findById(marcaId);
		log.info("Deleting marca by id on MarcaRepository");
		try {
			marcaRepository.deleteById(marcaId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir");
		}
		log.info("Finishing Method deleteById in MarcaSpringDataJPAService");
	}

}
