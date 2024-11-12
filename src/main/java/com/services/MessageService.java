package com.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dto.MessageDto;
import com.models.MessageModel;
import com.repositories.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;
	private final ModelMapper modelMapper;
	
	public MessageDto addMessage(MessageDto messageDto) {
		MessageModel messageModel = modelMapper.map(messageDto, MessageModel.class);
		messageModel.setCreatedAt(LocalDateTime.now());
		messageModel.setUpdatedAt(LocalDateTime.now());
		
		MessageModel messageResult = messageRepository.save(messageModel);
		return modelMapper.map(messageResult, MessageDto.class);
	}
}