package integration;


import br.com.pet.control.controller.dto.RegisterDTO;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.model.UserEntity;
import br.com.pet.control.repository.PetRepository;
import br.com.pet.control.repository.UserRepository;
import br.com.pet.control.security.TokenService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


class PetControlerIT extends PetApplicationTestes{



	@MockBean
	PetRepository petRepository;
	@MockBean
	UserRepository userRepository;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	TokenService tokenService;
	private HttpEntity<Void> protectedHeader;

	public void configProtectedHeaders() {
		UserEntity user = new UserEntity("gui22","Guilherme","P4o3l8l1",true, true, true, true);

		String token = tokenService.generateToken(user);
		System.out.println("--------------------------------------------------------------");
		System.out.println(token);
		System.out.println("--------------------------------------------------------------");

		HttpHeaders header = new HttpHeaders();
		header.add("Authorization","Bearer "+token);
		header.add("Content-Type","application/json");
		this.protectedHeader = new HttpEntity<>(header);
	}

   public PetEntity setupPet() {
		PetEntity pet = new PetEntity(null, "bob", "PitBull","dog",
				                         "male","Guilherme","Rua Bacharel",
				              "9999999","gsagahc@gmail.com");
		return pet;
	}
	@Test
	@WithMockUser(username = "gui22", roles = { "ADMIN", "USER" })
	public void listPetsWhenTokenIsCorrectShouldReturnStatusCode200() {
		configProtectedHeaders();
		ResponseEntity<PetEntity> response = restTemplate
				.exchange("/pet", GET, protectedHeader, PetEntity.class);
		assertEquals (200,response.getStatusCode().value());
	}
	@Test

	public void createPet() {
		PetEntity lpet = setupPet();

		ResponseEntity<PetEntity> pets =
				restTemplate.exchange("/pet",POST, protectedHeader, PetEntity.class);

		assertEquals(200, pets.getStatusCode().value());

	}


}
