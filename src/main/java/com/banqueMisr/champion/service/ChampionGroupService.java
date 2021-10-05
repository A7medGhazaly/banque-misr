package com.banqueMisr.champion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banqueMisr.champion.dto.ChampionGroupDTO;
import com.banqueMisr.champion.dto.ChampionGroupParticipantCountDTO;
import com.banqueMisr.champion.exception.DataNotFoundException;

@Service
public interface ChampionGroupService {

	List<ChampionGroupDTO> findChampionGroups() throws DataNotFoundException;

	List<ChampionGroupParticipantCountDTO> getGroupParticipantsCount() throws DataNotFoundException;

}
