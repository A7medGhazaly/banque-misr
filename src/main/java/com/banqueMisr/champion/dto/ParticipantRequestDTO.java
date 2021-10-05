package com.banqueMisr.champion.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ParticipantRequestDTO {
	@NotEmpty(message="participantName cannot be empty")
	private String participantName;
	@NotEmpty(message="email cannot be empty")
	@Email(message="incorrect email Format")
	private String email;
	
}
