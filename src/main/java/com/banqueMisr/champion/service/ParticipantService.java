package com.banqueMisr.champion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banqueMisr.champion.dto.ParticipantDTO;
import com.banqueMisr.champion.dto.ParticipantRequestDTO;
import com.banqueMisr.champion.exception.DataNotFoundException;

@Service
public interface ParticipantService {

	ParticipantDTO addNewParticipant(ParticipantRequestDTO participantRequestDTO) throws DataNotFoundException;

	List<ParticipantDTO> getAllParticipants() throws DataNotFoundException;

}
