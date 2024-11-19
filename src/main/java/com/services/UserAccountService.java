package com.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.models.UserModel;
import com.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByName(name)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		 return User.builder()
	                .username(userModel.getName())
	                .password(userModel.getPassword())
	                .disabled(false)
	                .build();
    }
}