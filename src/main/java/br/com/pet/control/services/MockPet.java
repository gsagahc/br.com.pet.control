package br.com.pet.control.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pet.control.model.PetEntity;



@Service
public class  MockPet {
	public PetEntity mockEntity() {
        return mockEntity(0);
    }
	 public List<PetEntity> mockEntityList() {
	        List<PetEntity> pets = new ArrayList<PetEntity>();
	        for (int i = 0; i < 14; i++) {
	            pets.add(mockEntity(i));
	        }
	        return pets;
	    }
	 
	  public PetEntity mockEntity(Integer number) {
		  PetEntity pet = new PetEntity();
	        pet.setAddress("Addres Test" + number);
	        pet.setPetName("Name Pet Test" + number);
	        pet.setGender(((number % 2)==0) ? "Male" : "Female");
	        pet.setId(number.longValue());
	        pet.setPetBreed("Breed Test" + number);
	        pet.setPetOwner("Owner Test" + number);
	        pet.setPetKind("Kind Test" + number);
	        return pet;
	    }
		

}
