package tn.esps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esps.entities.Archive;

public interface IArchiveRepo extends JpaRepository<Archive, Long> {

}
