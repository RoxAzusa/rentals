package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Integer> {

}
