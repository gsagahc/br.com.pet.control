package br.com.pet.control.controller;

import br.com.pet.control.Application;
import br.com.pet.control.logger.LogExecutionTime;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.services.PetServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Pets", description="End points for managing pets")
public class PetController {

	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
    @Autowired	
	private PetServices service;
    @LogExecutionTime
	@GetMapping( produces= MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaPets", key ="#id", condition="#p0!=null")
	@Operation(summary = "Show all pets on this API", description= "Show all pets on this API",

	responses = {
			@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
					mediaType = "application/json",
					array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
			)

					}),
			@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
			@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
	})
	public List<PetEntity> findAll()	 {
    	return service.findAll();
	}
	@GetMapping(value = "/{id}",
			produces= MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Find one pet by id", description= "Find one pet by id",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetEntity findByid(@PathVariable(value = "id") Long id)
	{

		return service.findByid(id);
	}
	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE,
			     consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Create new pet", description= "Create new pet",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetEntity create(@RequestBody PetEntity pet)
	{

		  return service.create(pet);
	}
	@PutMapping(
			   produces= MediaType.APPLICATION_JSON_VALUE,
			   consumes =MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Update a pet information", description= "Update a pet information",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public PetEntity update(@RequestBody PetEntity pet)
	{

		return service.update(pet);

	}
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete a pet by id", description= "Delete a pet by id",

			responses = {
					@ApiResponse(description ="Success", responseCode = "200", content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema (schema = @Schema( implementation = PetEntity.class))
					)

					}),
					@ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
					@ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
			})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)
	{
		 service.delete(id);
		 return ResponseEntity.noContent().build();

	}
}
