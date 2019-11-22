package fr.gtm.hello;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientDao extends JpaRepository<User, Long> {
	
	
	Optional<User> findByNomAndPassword(String nom, String password);
	
	

}
