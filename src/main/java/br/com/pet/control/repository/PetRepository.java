package br.com.pet.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pet.control.model.PetEntity;

import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long>{


}
