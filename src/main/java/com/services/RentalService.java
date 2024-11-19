package com.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dto.RentalDisplayDto;
import com.dto.RentalDto;
import com.models.RentalModel;
import com.repositories.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	private final ModelMapper modelMapper;
	private static String chars = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ123456789";
	private static int charLength = chars.length();
	
	@Value("${rentals.image.base-url}")
	private String imageBaseUrl;
	
	public String generateRandomCode(final int size) {
		StringBuilder pass = new StringBuilder(7);
		
		for (int x = 0; x < size; x++) {
			int i = new Random().nextInt(charLength);
			pass.append(chars.charAt(i));
		}
		
		return pass.toString();
	}
	
	public Map<String, List<RentalDisplayDto>> getRentals() {
		List<RentalModel> rentals = rentalRepository.findAll();
		List<RentalDisplayDto> rentalsDto = rentals.stream()
				.map(rental -> modelMapper.map(rental, RentalDisplayDto.class))
				.collect(Collectors.toList());
		rentalsDto.stream().forEach(rental -> rental.setPicture(imageBaseUrl + rental.getPicture()));
		Map<String, List<RentalDisplayDto>> response = new HashMap<>();
		response.put("rentals", rentalsDto);
		return response;
	}
	
	public RentalDisplayDto getRentalById(int idRental) {
		Optional<RentalModel> rental = rentalRepository.findById(idRental);
		RentalDisplayDto rentalDto = modelMapper.map(rental.get(), RentalDisplayDto.class);
		rentalDto.setPicture(imageBaseUrl + rentalDto.getPicture());
		return rentalDto;
	}
	
	public RentalDto createRental(RentalDto rentalDto, int id) throws IOException {
		RentalModel rentalModel = modelMapper.map(rentalDto, RentalModel.class);
		rentalModel.setPictureData(rentalDto.getPicture().getBytes());
		rentalModel.setPicture(generateRandomCode(10));
		rentalModel.setCreatedAt(LocalDateTime.now());
		rentalModel.setUpdatedAt(LocalDateTime.now());		
		rentalModel.setOwner_id(id);
				
		RentalModel rentalResult = rentalRepository.save(rentalModel);
		return modelMapper.map(rentalResult, RentalDto.class);
	}
	
	public RentalDto updateRental(int idRental, RentalDto rentalDto) {
		RentalModel newRental = modelMapper.map(rentalDto, RentalModel.class);
		RentalModel updatedRental = rentalRepository.findById(idRental).get();
		
		if (updatedRental == null ) {
			return null;
		}
		
		updatedRental.setName(newRental.getName());
		updatedRental.setSurface(newRental.getSurface());
		updatedRental.setPrice(newRental.getPrice());
		updatedRental.setDescription(newRental.getDescription());
		updatedRental.setUpdatedAt(LocalDateTime.now());
		
		RentalModel result = rentalRepository.save(updatedRental);
		return modelMapper.map(result, RentalDto.class);
	}
	
	public RentalDto findByPicture(String picture) {
		Optional<RentalModel> rental = rentalRepository.findByPicture(picture);
		return modelMapper.map(rental.get(), RentalDto.class);
	}
}
