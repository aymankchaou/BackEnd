package tn.esps.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esps.entities.ERole;
import tn.esps.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole name);
}
