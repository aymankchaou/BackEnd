package tn.esps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esps.entities.Image;

public interface IImageRepository extends JpaRepository<Image, Long> {
	
}
