package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.models.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Integer>{

}