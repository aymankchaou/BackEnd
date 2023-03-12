package tn.esps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esps.entities.UserInformation;

import java.util.List;
import java.util.Optional;

public interface IUtilidateurRepo extends JpaRepository<UserInformation, Long> {

	Optional<UserInformation> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<UserInformation> findByEmail(String email);

	@Query("select u from UserInformation u where u.archived = 1")
	List<UserInformation> getEmployes();

	@Query("select u from UserInformation u where u.archived = 2")
	List<UserInformation> getArchivedEmployes();

}
