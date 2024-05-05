package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.exception.ResourceNotFoundException;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PetServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetRepository petRepository;
    
   public List<PetEntity> findAll() {
	    logger.info("Showing all pets!");
		return petRepository.findAll();

   	
   }
  
	public PetEntity create(PetEntity pet) {
    	logger.info("Creating one pet!"+" name:"+pet.getPetName());
    	return petRepository.save(pet);
    	
    }
    public PetEntity update(PetEntity pet) {
    	logger.info("Updating one pet! id:"+pet.getId()+" Name:"+pet.getPetName());
    	PetEntity entity = petRepository.findById(pet.getId())
     			.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+pet.getId()));
    	entity.setPetName(pet.getPetName());
    	entity.setPetBreed(pet.getPetBreed());
    	entity.setPetKind(pet.getPetKind());
    	entity.setPetOwner(pet.getPetOwner());
    	entity.setAddress(pet.getAddress());
    	entity.setEmail(pet.getEmail());
    	entity.setPhoneNumber(pet.getPhoneNumber());
    	entity.setGender(pet.getGender());
    	return petRepository.save(entity);
    	
    }
  
	public PetEntity findByid(Long id) {
    	logger.info("Finding one pet!"+id);
     	//return petRepository.findById(id);
		return petRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+id));

    }
	public void delete(Long id) {
		logger.info("Deleting one pet, id:" + id);
		PetEntity entity = petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		 petRepository.delete(entity);

	}
}
