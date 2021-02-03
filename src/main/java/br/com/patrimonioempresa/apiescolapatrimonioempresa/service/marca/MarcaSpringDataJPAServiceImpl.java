package br.com.patrimonioempresa.apiescolapatrimonioempresa.service.marca;

import java.util.List;

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
	
	public List<Marca> findAll(){
		log.info("Starting Method findAll in MarcaSpringDataJPAServiceImpl");
		List<Marca> listMarca = this.marcaRepository.findAll();
		log.info("finishing Method findAll in  ALunoSpringDataJPAService");
		return listMarca;
	}

	@Override
	public Marca findById(Integer marcaID) {
		log.info("Starting Method findById in MarcaSpringDataJPAService");
		log.info("Parameter:{}", marcaID);
		log.info("Finding Escola by id on MarcaRepository");
		Marca marca = null;
		try {
			 marca = this.marcaRepository.findById(marcaID).orElseThrow(() -> new BusinessException(" A marca não existe"));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return marca;
	}

	@Override
	public Marca save(Marca marca) {
		log.info("Starting Method save in ALunoSpringDataJPAService");
		marca = this.marcaRepository.save(marca);
		log.info("Finishing Method save in ALunoSpringDataJPAService");
		return marca;
	}

	
}
