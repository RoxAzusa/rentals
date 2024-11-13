package com.dto;

import lombok.Data;

@Data
public class SendMessageSuccess {
	private String message;
	
	public SendMessageSuccess(String message) {
		this.message = message;
	}
}
