package com.banqueMisr.champion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ChampionLeagueMatchDTO {
	private Long id;
	private ParticipantDTO firstParticipant;
	private ParticipantDTO secondParticipant;
	private ParticipantDTO matchWinner;
}
