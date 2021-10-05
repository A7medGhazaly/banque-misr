package com.banqueMisr.champion.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banqueMisr.champion.dto.ChampionLeagueDTO;
import com.banqueMisr.champion.dto.ChampionLeagueMatchDTO;
import com.banqueMisr.champion.dto.SubmitLeagueWinnerRequestDTO;
import com.banqueMisr.champion.dto.SubmitMatchWinnerRequestDTO;
import com.banqueMisr.champion.exception.BusinessException;
import com.banqueMisr.champion.exception.DataNotFoundException;
import com.banqueMisr.champion.service.ChampionMatchService;

@RestController
public class ChampionController {
	@Autowired
	ChampionMatchService championMatchService;

	@PostMapping("/start-champion")
	public ChampionLeagueDTO startChampionLeague() throws DataNotFoundException, BusinessException {
		return championMatchService.startLeagueMatch();
	}
	
	@PutMapping("/match-winner")
	public ChampionLeagueMatchDTO submitMatchWinner(@RequestBody @Valid SubmitMatchWinnerRequestDTO dto) throws DataNotFoundException, BusinessException {
		return championMatchService.submitMatchWinner(dto);
	}
	
	@PostMapping("/close-round")
	public void closeRound() throws BusinessException {
		championMatchService.closeRound();
	}
	@PutMapping("/league-winner")
	public ChampionLeagueDTO submitLeagueWinner(@RequestBody @Valid SubmitLeagueWinnerRequestDTO dto) throws DataNotFoundException, BusinessException {
		return championMatchService.submitLeagueWinner(dto);
	}
	
}
