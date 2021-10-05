package com.banqueMisr.champion.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banqueMisr.champion.dto.ParticipantDTO;
import com.banqueMisr.champion.dto.ParticipantRequestDTO;
import com.banqueMisr.champion.exception.DataNotFoundException;
import com.banqueMisr.champion.service.ParticipantService;

@RestController
public class ParticipantController {
	@Autowired
	ParticipantService participantService;

	@PostMapping("/participant")
	public ParticipantDTO addNewPartiDto(@RequestBody @Valid ParticipantRequestDTO requestDTO)
			throws DataNotFoundException {
		return participantService.addNewParticipant(requestDTO);
	}

	@GetMapping("/participants")
	public List<ParticipantDTO> getAllParticipants() throws DataNotFoundException {
		return participantService.getAllParticipants();
	}
}
