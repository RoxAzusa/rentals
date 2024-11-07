package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Integer> {
	Optional<RentalModel> findByPicture(String picture);
}
