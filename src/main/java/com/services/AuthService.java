package com.services;

import org.springframework.stereotype.Service;

import com.models.UserModel;
import com.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	
	public UserModel getMe() {
		return userRepository.findById(1).get();
	}
}