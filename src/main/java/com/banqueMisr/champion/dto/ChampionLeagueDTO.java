package com.banqueMisr.champion.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
@JsonInclude(Include.NON_NULL)
public class ChampionLeagueDTO {
	private Long id;
	private Date startAt;
	private Date endsAt;
	private ParticipantDTO winner;
	private List<ChampionLeagueMatchDTO> matches;
}
