package com.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.UserDto;
import com.dto.UserDtoWithoutPassword;
import com.models.UserModel;
import com.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final JWTService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	public UserDtoWithoutPassword getMe(int id) {
		UserModel user = userRepository.findById(id).get();
		return modelMapper.map(user, UserDtoWithoutPassword.class);
	}
	
	public String login(String email, String password) {
		Optional<UserModel> user = userRepository.findUserByEmail(email);
		
		if (!user.isEmpty() && passwordEncoder.matches(password, user.get().getPassword())) {
			String token = jwtService.generateToken(user.get());
			return token;
		}
		
		return null;
	}
	
	public String register(UserDto userDto) {
		UserModel userModel = modelMapper.map(userDto, UserModel.class);
		
		userModel.setCreatedAt(LocalDateTime.now());
		userModel.setUpdatedAt(LocalDateTime.now());
		userModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		
		UserModel response = userRepository.save(userModel);

		if (response != null) {
			String token = jwtService.generateToken(userModel);
			return token;			
		}
		
		return null;
	}
}