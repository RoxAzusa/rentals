package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dto.UserDto;
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
	
	public String login(String email, String password) {
		Optional<UserModel> user = userRepository.findUserByEmailAndPassword(email, password);
		
		if (!user.isEmpty()) {
			String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlRhdGEiLCJpYXQiOjE1MTYyMzkwMjJ9.4iyAH-1x4gDpnY0HySORM_YNlTLk2Ra2iGxU_b33Qbo";
			return token;
		}
		
		return null;
	}
	
	public String register(UserDto userDto) {
		UserModel userModel = modelMapper.map(userDto, UserModel.class);	
		userModel.setCreatedAt(LocalDateTime.now());
		userModel.setUpdatedAt(LocalDateTime.now());
		userRepository.save(userModel);
		
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlRhdGEiLCJpYXQiOjE1MTYyMzkwMjJ9.4iyAH-1x4gDpnY0HySORM_YNlTLk2Ra2iGxU_b33Qbo";
		return token;
	}
}