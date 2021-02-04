package br.com.patrimonioempresa.apiescolapatrimonioempresa.service.patrimonio;

import java.util.List;
import java.util.Random;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.exception.BusinessException;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.model.Patrimonio;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.repository.patrimonio.PatrimonioRepository;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatrimonioSpringDataJPAServiceImpl implements PatrimonioService {

	private PatrimonioRepository patrimonioRepository;

	public PatrimonioSpringDataJPAServiceImpl(PatrimonioRepository patrimonioRepository) {
		this.patrimonioRepository = patrimonioRepository;
	}

	@Override
	public List<Patrimonio> findAll() {
		log.info("Starting Method findAll in PatrimonioRepository");
		List<Patrimonio> listPatrimonio = patrimonioRepository.findAll();
		log.info("finishing Method findAll in PatrimonioRepository");
		return listPatrimonio;
	}

	@Override
	public Patrimonio findById(Integer patrimonioId) {
		log.info("Starting Method findById in PatrimonioSpringDataJPAService");
		log.info("Parameter:{}", patrimonioId);
		log.info("Finding patrimonio by id on PatrimonioRepository");
		Patrimonio patrimonio = null;
		try {
			patrimonio = this.patrimonioRepository.findById(patrimonioId)
					.orElseThrow(() -> new BusinessException("O patrimonio não existe"));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Finishing Method findById in PatrimonioSpringDataJPAService");
		return patrimonio;
	}

	@Override
	public Patrimonio save(Patrimonio patrimonio) {
		log.info("Starting Method save in PatrimonioSpringDataJPAService");
		log.info("Generating the number of the tumble in PatrimonioSpringDataJPAService");
		numeroDoTombo(patrimonio);
		log.info("The number of the generated tumble is:", patrimonio.getNumeroDoTombo());
		log.info("Finishing Method save in PatrimonioSpringDataJPAService");
		return patrimonioRepository.save(patrimonio);
	}

	private void numeroDoTombo(Patrimonio patrimonio) {
		Random random = new Random();
		Long numeroDoTombo = Long.valueOf(random.nextInt(1000000000));
		patrimonio.setNumeroDoTombo(numeroDoTombo);
	}

	@Override
	public Patrimonio update(Integer patrimonioId, Patrimonio patrimonioForm) {
		log.info("Starting Method update in PatrimonioSpringDataJPAService");
		Patrimonio patrimonioById = findById(patrimonioId);
		patrimonioById.updatePatrimonio(patrimonioForm);
		log.info("Save in PatrimonioRepository");
		this.patrimonioRepository.save(patrimonioById);
		log.info("Finishing Method save in PatrimonioSpringDataJPAService");
		return patrimonioById;
	}

	@Override
	public void delete(Integer patrimonioId) {
		log.info("Starting Method Delete in PatrimonioSpringDataJPAService");
		log.info("Parameter:Marca Id = {},", patrimonioId);
		findById(patrimonioId);
		log.info("Deleting patrimonio by id on PatrimonioRepository");
		try {
			patrimonioRepository.deleteById(patrimonioId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir");
		}
		log.info("Finishing Method deleteById in PatrimonioSpringDataJPAService");
	}

}
