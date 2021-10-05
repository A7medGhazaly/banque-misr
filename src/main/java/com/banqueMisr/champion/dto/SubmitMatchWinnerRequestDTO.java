package com.banqueMisr.champion.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SubmitMatchWinnerRequestDTO {
	@NotNull( message="winner id cannot be empty")
	private Long winnerId;
	@NotNull( message="matchId cannot be empty")
	private Long matchId;
}
