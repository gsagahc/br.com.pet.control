package br.com.pet.control.services;


import br.com.pet.control.exceptions.UserNotFoundException;
import br.com.pet.control.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pet.control.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        UserDetails userDetails = repository.findByLogin(username);
        if (userDetails!=null){
            return  userDetails;
        }else
            throw new UserNotFoundException("No records matches for this login: " +username);

    }
}
