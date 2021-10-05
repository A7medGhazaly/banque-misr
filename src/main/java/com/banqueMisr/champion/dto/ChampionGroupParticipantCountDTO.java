package com.banqueMisr.champion.dto;

import lombok.Data;

@Data
public class ChampionGroupParticipantCountDTO {
	private Long groupId;
	private String groupName;
	private Long participantNumber;
}
