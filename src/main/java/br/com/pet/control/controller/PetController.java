package br.com.pet.control.controller;

import br.com.pet.control.Application;
import br.com.pet.control.logger.LogExecutionTime;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.services.PetServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pet")
public class PetController {

	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
    @Autowired	
	private PetServices service;
    @LogExecutionTime
	@GetMapping( produces= MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaPets", key ="#id", condition="#p0!=null")
	public List<PetEntity> findAll()	 {
    	return service.findAll();
	}
	@GetMapping(value = "/{id}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	public PetEntity findByid(@PathVariable(value = "id") Long id)
	{

		return service.findByid(id);
	}
	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE,
			     consumes =MediaType.APPLICATION_JSON_VALUE)

	public PetEntity create(@RequestBody PetEntity pet)
	{

		  return service.create(pet);
	}
	@PutMapping(
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes =MediaType.APPLICATION_JSON_VALUE)

	public PetEntity update(@RequestBody PetEntity pet)
	{

		return service.update(pet);

	}
	@DeleteMapping(value = "/{id}")

	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)
	{
		 service.delete(id);
		 return ResponseEntity.noContent().build();

	}
}
