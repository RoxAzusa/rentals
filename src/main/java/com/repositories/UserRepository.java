package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}