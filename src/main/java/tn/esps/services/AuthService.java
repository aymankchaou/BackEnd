package tn.esps.services;

import org.springframework.stereotype.Service;

import tn.esps.dto.RegisterRequest;
import tn.esps.entities.UserInformation;
import tn.esps.repositories.IUtilidateurRepo;

@Service
public class AuthService {
	
	private IUtilidateurRepo userRepo ; 
	
	public void signup (RegisterRequest registerRequest) {
		UserInformation user = new UserInformation();
		user.setNom(registerRequest.getUserName());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());
		userRepo.save(user);
	}
	
}
