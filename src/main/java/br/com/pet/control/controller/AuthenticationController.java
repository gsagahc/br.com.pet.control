package br.com.pet.control.controller;


import br.com.pet.control.controller.dto.AuthenticationDTO;
import br.com.pet.control.controller.dto.LoginResponseDTO;
import br.com.pet.control.controller.dto.RegisterDTO;
import br.com.pet.control.logger.LogExecutionTime;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.model.UserEntity;
import br.com.pet.control.repository.UserRepository;
import br.com.pet.control.security.TokenService;
import br.com.pet.control.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name="Security", description="End points for managing security")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    UserServices service;
	
    @LogExecutionTime
    @PostMapping("/login")
    @Operation(summary = "Loggin on this application", description= "Loggin on this application",

            responses = {
                    @ApiResponse(description ="Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema( implementation = PetEntity.class))
                    )

                    }),
                    @ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
                    @ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody  AuthenticationDTO data){
    	
    	  var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
       	  var auth = this.authenticationManager.authenticate(usernamePassword);
    	  var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
          return ResponseEntity.ok(new LoginResponseDTO(token));
    	}
    
		
    @LogExecutionTime
    @PostMapping("/register")
    @Operation(summary = "Register a new user for this application", description= "Register a new user for this application",

            responses = {
                    @ApiResponse(description ="Success", responseCode = "200", content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema( implementation = PetEntity.class))
                    )

                    }),
                    @ApiResponse(description ="Forbiden", responseCode = "403", content = @Content),
                    @ApiResponse(description ="Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description ="Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data){
       if (service.findByLogin(data.login())) {
    		
    	   	   return  ResponseEntity.status(HttpStatus.FOUND).body("Usuário já cadastrado na base");
       }else {
    
    	if (service.save(data)) {
           //return ResponseEntity.ok().build();
           return  ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso");
    	}else {
    	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar novo login");
    	}
       }
       
	   
    }       
}      
    

