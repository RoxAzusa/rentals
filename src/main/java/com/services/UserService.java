package com.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dto.UserDtoWithoutPassword;
import com.models.UserModel;
import com.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public UserDtoWithoutPassword getUserById(int idUser) {
		UserModel user = userRepository.findById(idUser).get();
		return modelMapper.map(user, UserDtoWithoutPassword.class);
	}
}
