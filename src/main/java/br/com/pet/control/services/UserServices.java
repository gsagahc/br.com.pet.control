package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.controller.dto.RegisterDTO;
import br.com.pet.control.model.UserEntity;
import br.com.pet.control.repository.UserRepository;
import com.google.common.util.concurrent.ExecutionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
    UserRepository userRepository;
    
   public Boolean findByLogin(String login) {
		if (userRepository.findByLogin(login)== null) {
			
			return false;
		}else
			logger.info("Login "+login+" Já cadastrado na base");
			return true;
     	
   }
   public Boolean save(RegisterDTO data) {
	   try {
		  String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
	      UserEntity newUser = new UserEntity(data, encryptedPassword );
	      userRepository.save(newUser);
	      return true;
	   }catch (ExecutionError e){
		 logger.info(e.toString());
		  return false;  
	   }
	   
	  
	   
   }
   
  
	
   
}
