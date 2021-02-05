package br.com.patrimonioempresa.apiescolapatrimonioempresa.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
