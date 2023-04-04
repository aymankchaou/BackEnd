package tn.esps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esps.entities.Employe;

public interface IEmployeRepo extends JpaRepository<Employe, Long> {

}
