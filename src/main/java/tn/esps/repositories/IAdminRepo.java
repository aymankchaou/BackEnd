package tn.esps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esps.entities.Administrateur;

public interface IAdminRepo extends JpaRepository<Administrateur, Long> {

}
