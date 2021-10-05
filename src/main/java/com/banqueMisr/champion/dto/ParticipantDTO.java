package com.banqueMisr.champion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ParticipantDTO {
	private Long id;
	private String englishName;
	private String email;
	private ChampionGroupDTO championGroupDTO;



}
