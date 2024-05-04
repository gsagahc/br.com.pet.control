package br.com.pet.test.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pet.control.model.PetEntity;
import br.com.pet.control.repository.PetRepository;
import br.com.pet.control.services.MockPet;
import br.com.pet.control.services.PetServices;




@TestInstance(Lifecycle.PER_CLASS) 
@ExtendWith(MockitoExtension.class)
class PetServicesTest {

	MockPet input;
	@InjectMocks
	private PetServices services;
	
	@Mock
	PetRepository petRepository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPet();
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	void testCreate() {
		PetEntity pet =  input.mockEntity(1);
		PetEntity petpersisted = pet;
		pet.setId(1L);
		when(petRepository.save(pet)).thenReturn(petpersisted);
		var result = services.create(pet);
		assertNotNull(result);
		assertEquals("Name Pet Test1", result.getPetName());
		assertEquals("Breed Test1", result.getPetBreed());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Owner Test1", result.getPetOwner());
		assertEquals("Kind Test1", result.getPetKind());
	}

	@Test
	void testUpdate() {
		PetEntity pet =  input.mockEntity(1);
		PetEntity petpersisted = pet;
		pet.setId(1L);
		petpersisted.setId(1L);
		when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
		when(petRepository.save(pet)).thenReturn(petpersisted);
		
		var result = services.update(pet);
		assertNotNull(result);
		assertEquals("Name Pet Test1", result.getPetName());
		assertEquals("Breed Test1", result.getPetBreed());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Owner Test1", result.getPetOwner());
		assertEquals("Kind Test1", result.getPetKind());
	}

	@Test
	void testFindByid() {
		PetEntity pet =  input.mockEntity(1);
		pet.setId(1L);
		when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
		var result = services.findByid(1L);
		assertNotNull(result);
		assertEquals("Name Pet Test1", result.getPetName());
		assertEquals("Breed Test1", result.getPetBreed());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Owner Test1", result.getPetOwner());
		assertEquals("Kind Test1", result.getPetKind());
		
	}

	@Test
	void testDelete() {	
		PetEntity pet =  input.mockEntity(1);
		pet.setId(1L);
		when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
		services.delete(1L);
	}

}
