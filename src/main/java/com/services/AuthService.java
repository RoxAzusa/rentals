package com.services;

import org.springframework.stereotype.Service;

import com.models.UserModel;
import com.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public UserDto getMe() {
		UserModel user = userRepository.findById(1).get();
		return modelMapper.map(user, UserDto.class);
	}
	
	}
}